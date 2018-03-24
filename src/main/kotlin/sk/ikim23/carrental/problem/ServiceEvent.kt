package sk.ikim23.carrental.problem

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.SimCore

class ServiceEvent(core: SimCore, time: Double, val customer: Customer, val popCustomer: Boolean = true) : Event(core, time) {
    override fun exec() {
        customer.service = time
        if(core.print) println("$time $customer service ${customer.waitTime()}")
        val exitTime = core.currentTime + core.rService.nextDouble()
        val exit = ExitEvent(core, exitTime, customer)
        core.addEvent(exit)
    }
}