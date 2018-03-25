package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event

class SlowMoEvent(val core: SimCore, val listener: IStatsListener) : Event(core, core.currentTime + listener.timeStep) {
    override fun exec() {
        listener.onUpdate(core.stats.copy())
        val millis = (listener.timeStep * listener.timeSpeed * 1000).toLong()
        core.sleep(millis)
        if (core.customersAreWaiting()) {
            core.addEvent(SlowMoEvent(core, listener))
        }
    }
}