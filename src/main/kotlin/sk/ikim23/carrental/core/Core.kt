package sk.ikim23.carrental.core

import java.util.*

abstract class Core(val endTime: Double, val log: Boolean = false) {
    private val eventQueue = PriorityQueue<Event>()
    var currentTime = 0.0
        private set

    abstract fun init()

    abstract fun reset()

    fun hasTime() = currentTime <= endTime

    fun addEvent(event: Event) = eventQueue.add(event)

    fun start() {
        resetAll()
        init()
        while (hasTime() || eventQueue.isNotEmpty()) {
            val event = eventQueue.poll()
            currentTime = event.execTime
            event.exec()
        }
    }

    private fun resetAll() {
        currentTime = 0.0
        eventQueue.clear()
        reset()
    }
}