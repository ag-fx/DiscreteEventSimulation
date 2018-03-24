package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer

class BusLoadsCstOnT1Event(val core: SimCore, val bus: Bus, val customer: Customer, execTime: Double) : Event(core, execTime) {
    override fun exec() {
        bus.add(customer)
        log("$customer loaded on $bus")
        if (!bus.isFull() && core.t1Queue.isNotEmpty()) {
            // load passenger
            val nextCustomer = core.t1Queue.remove()
            val nextLoad = BusLoadsCstOnT1Event(core, bus, nextCustomer, core.currentTime + core.rLoadOnT1.nextDouble())
            core.addEvent(nextLoad)
        } else {
            // bus leaves T1
            val rideToT2 = BusArrivedToT2Event(core, bus, core.currentTime + core.tTimeToT2)
            core.addEvent(rideToT2)
        }
    }
}