package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Customer

class CstGotKeysEvent(val core: SimCore, val customer: Customer, execTime: Double) : Event(core, execTime) {
    override fun exec() {
        log("$customer got car")
        core.serviceDesk.remove(customer)
        core.stats.takeCustomer(customer)
        if (core.rentalQueue.isNotEmpty()) {
            val servedCustomer = core.rentalQueue.remove()
            val serviceEvent = CstGotKeysEvent(core, servedCustomer, core.currentTime + core.rService.nextDouble())
            core.addEvent(serviceEvent)
        }
    }
}