package sk.ikim23.carrental.core

import java.util.*

open class Core : Pauseable(), ITime {
    var log = false
        private set
    override val currentTime get() = curTime
    private val eventQueue = PriorityQueue<Event>()
    private var endTime = 0.0
    private var curTime = 0.0
    private var slowMo = false

    override fun canTick(): Boolean {
        val canTick = hasTime() || eventQueue.isNotEmpty()
        if (!canTick && status == Status.RUNNING) stop()
        return canTick
    }

    override fun tick() {
        val event = eventQueue.poll()
        curTime = event.execTime
        event.exec()
    }

    override fun beforeStart() = reset()

    fun hasTime(): Boolean = curTime <= endTime

    fun addEvent(event: Event) = eventQueue.add(event)

    fun init(endTime: Double, slowMo: Boolean, log: Boolean) {
        this.endTime = endTime
        this.log = log
    }

    open fun reset() {
        curTime = 0.0
        eventQueue.clear()
        if (slowMo) addEvent(SlowMoEvent(this))
    }
}