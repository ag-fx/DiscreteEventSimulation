package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Core
import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer
import sk.ikim23.carrental.core.obj.ServiceDesk
import sk.ikim23.carrental.random.ExpRandom
import sk.ikim23.carrental.random.NormRandom
import sk.ikim23.carrental.times
import java.util.*

class SimCore(endTime: Double, val nBuses: Int, nDesks: Int) : Core(endTime, false) {
    val tTimeToT1 = calcTravelTime(6.4)
    val tTimeToT2 = calcTravelTime(0.5)
    val tTimeToRental = calcTravelTime(2.5)
    val rArrivalToT1 = ExpRandom(43.0 / (60 * 60))
    val rArrivalToT2 = ExpRandom(19.0 / (60 * 60))
    val rLoadOnT1 = NormRandom(10.0, 14.0)
    val rLoadOnT2 = NormRandom(10.0, 14.0)
    val rLeavedBus = NormRandom(4.0, 12.0)
    val rService = NormRandom(2.0 * 60, 10.0 * 60)
    val buses = LinkedList<Bus>()
    val t1Queue: Queue<Customer> = LinkedList()
    val t2Queue: Queue<Customer> = LinkedList()
    val rentalQueue: Queue<Customer> = LinkedList()
    val serviceDesk = ServiceDesk(nDesks)
    val stats = Stats()

    fun cstWaitingOnTerminal() = hasTime() || t1Queue.isNotEmpty() || t2Queue.isNotEmpty()

    override fun init() {
        nBuses.times {
            val bus = Bus()
            buses.add(bus)
            addEvent(randArrival(bus))
        }
        addEvent(CstArrivedOnT1Event(this, rArrivalToT1.nextDouble()))
//        addEvent(CstArrivedOnT2Event(this, rArrivalToT2.nextDouble()))
    }

    override fun reset() {
        buses.clear()
        stats.reset()
    }

    private fun randArrival(bus: Bus): Event {
        val maxDelay = 0.5
        val rand = Random()
        return when (rand.nextInt(3)) {
            0 -> BusArrivedToRentalEvent(this, bus, rand.nextDouble() * maxDelay)
            1 -> BusArrivedToT1Event(this, bus, rand.nextDouble() * maxDelay)
            2 -> BusArrivedToT2Event(this, bus, rand.nextDouble() * maxDelay)
            else -> throw IllegalArgumentException()
        }
    }

    private fun calcTravelTime(distance: Double) = (distance / 35) * (60 * 60)
}