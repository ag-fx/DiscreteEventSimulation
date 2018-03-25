package sk.ikim23.carrental.core

import java.util.concurrent.atomic.AtomicReference

open class Pauseable {
    enum class Status {
        RUNNING, PAUSED, STOPPED, DESTROYED
    }

    private val log = false
    private val threadStatus = AtomicReference(Status.STOPPED)
    private val lock = Object()
    val status: Status get() = threadStatus.get()

    init {
        Thread({
            while (status != Status.DESTROYED) {
                if (status == Status.PAUSED || status == Status.STOPPED) {
                    synchronized(lock) {
                        try {
                            lock.wait()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                if (canTick()) tick()
            }
        }).start()
    }

    open fun canTick() = true

    open fun tick() {}

    open fun beforeStart() {}

    open fun afterStop() {}

    fun start() {
        val s = status
        if (s == Status.DESTROYED) {
            throw IllegalStateException("Destroyed instance can not be started")
        } else if (s == Status.PAUSED || s == Status.STOPPED) {
            if (s == Status.STOPPED) beforeStart()
            threadStatus.set(Status.RUNNING)
            synchronized(lock) {
                try {
                    lock.notifyAll()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (log) println(status)
        }
    }

    fun pause() {
        if (status != Status.DESTROYED && status == Status.RUNNING) {
            threadStatus.set(Status.PAUSED)
            if (log) println(status)
        }
    }

    fun stop() {
        if (status != Status.DESTROYED && status != Status.STOPPED) {
            threadStatus.set(Status.STOPPED)
            if (log) println(status)
            afterStop()
        }
    }

    fun destroy() {
        if (status != Status.DESTROYED) {
            threadStatus.set(Status.DESTROYED)
            if (log) println(status)
        }
    }
}