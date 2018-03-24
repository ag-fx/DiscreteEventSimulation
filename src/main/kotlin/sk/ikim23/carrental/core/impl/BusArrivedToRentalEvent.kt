package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Bus

class BusArrivedToRentalEvent(val core: SimCore, val bus: Bus, execTime: Double) : Event(core, execTime) {
    override fun exec() {
        log("$bus arrived to Rental shop")
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