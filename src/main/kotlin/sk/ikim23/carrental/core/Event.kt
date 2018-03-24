package sk.ikim23.carrental.core

abstract class Event(val core: SimCore, val time: Double) : Comparable<Event> {
    abstract fun exec()

    override fun compareTo(other: Event): Int {
        return time.compareTo(other.time)
    }
}