package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Bus

class BusArrivedToT1Event(val core: SimCore, val bus: Bus, execTime: Double) : Event(core, execTime) {
    override fun exec() {
        log("$bus arrived to T1")
        if (!bus.isFull() && core.t1Queue.isNotEmpty()) {
            // load passengers
            val customer = core.t1Queue.remove()
            val loadCustomer = BusLoadsCstOnT1Event(core, bus, customer, core.currentTime + core.rLoadOnT1.nextDouble())
            core.addEvent(loadCustomer)
        } else {
            // leave empty
            val rideToT2 = BusArrivedToT2Event(core, bus, core.currentTime + core.tTimeToT2)
            core.addEvent(rideToT2)
        }
    }
}