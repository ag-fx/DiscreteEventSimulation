package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Event

class SlowMoEvent(val core: SimCore, val listener: IStatsListener) : Event(core, core.currentTime + listener.timeStep.value) {
    override fun exec() {
        if (listener.timeStep != IStatsListener.Step.NONE) {
            listener.onUpdate(core.stats)
            core.sleep(100)
        }
        if (core.customersAreWaiting()) {
            core.addEvent(SlowMoEvent(core, listener))
        }
    }
}