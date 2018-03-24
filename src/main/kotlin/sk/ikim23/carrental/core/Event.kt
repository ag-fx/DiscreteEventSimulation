package sk.ikim23.carrental.core

abstract class Event(val core: Core, val execTime: Double) : Comparable<Event> {
    abstract fun exec()

    fun log(message: String) {
        if (core.log) println("$this: $message")
    }

    override fun compareTo(other: Event) = execTime.compareTo(other.execTime)

    override fun toString() = "${this.javaClass.simpleName}($execTime)"
}