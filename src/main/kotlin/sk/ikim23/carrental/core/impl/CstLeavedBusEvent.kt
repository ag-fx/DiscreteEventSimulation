package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Bus

class CstLeavedBusEvent(val core: SimCore, val bus: Bus, execTime: Double) : Event(core, execTime) {
    override fun exec() {
        val customer = bus.remove()
        log("$customer leaved $bus")
        core.rentalQueue.add(customer)
        if (!core.serviceDesk.isFull()) {
            val servedCustomer = core.rentalQueue.remove()
            servedCustomer.serviceTime = execTime
            val serviceEvent = CstGotKeysEvent(core, servedCustomer, core.currentTime + core.rService.nextDouble())
            core.addEvent(serviceEvent)
        }
        if (!bus.isEmpty()) {
            val nextLeave = CstLeavedBusEvent(core, bus, core.currentTime + core.rLeavedBus.nextDouble())
            core.addEvent(nextLeave)
        } else {
            if (core.cstWaitingOnTerminal()) {
                val rideToT1 = BusArrivedToT1Event(core, bus, core.currentTime + core.tTimeToT1)
                core.addEvent(rideToT1)
            }
        }
    }
}