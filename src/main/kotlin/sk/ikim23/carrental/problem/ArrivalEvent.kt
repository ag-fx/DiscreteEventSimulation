package sk.ikim23.carrental.problem

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.SimCore

class ArrivalEvent(core: SimCore, time: Double) : Event(core, time) {
    override fun exec() {
        val customer = Customer(time)
        if(core.print) println("$time $customer arrived")
        if (!core.hasCustomers()) {
            val service = ServiceEvent(core, time, customer)
            core.addEvent(service)
        }
        core.pushCustomer(customer)
        val arrivalTime = core.currentTime + core.rArrival.nextDouble()
        val nextArrival = ArrivalEvent(core, arrivalTime)
        core.addEvent(nextArrival)
    }
}