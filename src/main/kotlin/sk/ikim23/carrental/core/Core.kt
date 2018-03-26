package sk.ikim23.carrental.core

import java.util.*

open class Core : Pauseable(), ITime {
    var log = false
        private set
    override val currentTime get() = curTime
    private val eventQueue = PriorityQueue<Event>()
    private var endTime = 0.0
    private var curTime = 0.0

    override fun canTick() = hasTime() || eventQueue.isNotEmpty()

    override fun tick() {
        val event = eventQueue.poll()
        curTime = event.execTime
        event.exec()
    }

    override fun beforeStart() {
        curTime = 0.0
        eventQueue.clear()
    }

    fun hasTime() = curTime <= endTime

    fun hasEvents() = eventQueue.isNotEmpty()

    fun addEvent(event: Event) = eventQueue.add(event)

    fun init(endTime: Double, log: Boolean) {
        this.endTime = endTime
        this.log = log
    }
}
