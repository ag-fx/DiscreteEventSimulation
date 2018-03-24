package sk.ikim23.carrental.problem

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.SimCore

class ExitEvent(core: SimCore, time: Double, val customer: Customer) : Event(core, time) {
    override fun exec() {
        val waitingCustomer = core.popCustomer()
        if (customer != waitingCustomer) throw IllegalStateException()
        if(core.print) println("$time $customer exit")
        core.stats.addSysTime(customer)
        if (core.hasCustomers()) {
            val customer = core.peekCustomer()
            val nextService = ServiceEvent(core, time, customer)
            core.addEvent(nextService)
        }
    }
}