package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event
import sk.ikim23.carrental.core.obj.Customer

class CstArrivedOnT1Event(val core: SimCore, execTime: Double) : Event(core, execTime) {
    override fun exec() {
        val customer = Customer(execTime)
        log("$customer arrived on T1")
        core.t1Queue.add(customer)
        if (core.hasTime()) {
            val nextArrival = CstArrivedOnT1Event(core, core.currentTime + core.rArrivalToT1.nextDouble())
            core.addEvent(nextArrival)
        }
    }
}