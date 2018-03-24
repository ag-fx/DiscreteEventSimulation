package sk.ikim23.carrental.core

import java.util.*

abstract class Core(val endTime: Double, val log: Boolean = true) {
    private val eventQueue = PriorityQueue<Event>()
    var currentTime = 0.0
        private set

    abstract fun init()

    fun hasTime() = currentTime <= endTime

    fun addEvent(event: Event) {
        if (hasTime()) eventQueue.add(event)
    }

    fun start() {
        reset()
        init()
        while (hasTime() && eventQueue.isNotEmpty()) {
            val event = eventQueue.poll()
            currentTime = event.execTime
            event.exec()
        }
    }

    private fun reset() {
        currentTime = 0.0
        eventQueue.clear()
    }
}