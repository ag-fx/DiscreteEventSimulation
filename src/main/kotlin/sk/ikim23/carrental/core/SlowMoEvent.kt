package sk.ikim23.carrental.core

class SlowMoEvent(val core: Core) : Event(core, core.currentTime + 1.0) {
    override fun exec() {
        core.addEvent(SlowMoEvent(core))
    }
}