package sk.ikim23.carrental.core

import java.util.concurrent.atomic.AtomicReference

open class Pauseable(private val log: Boolean = false) {
    enum class Status {
        RUNNING, PAUSED, STOPPED, SLEEPING, DESTROYED
    }

    companion object {
        var ID = 0
    }

    private val threadStatus = AtomicReference(Status.STOPPED)
    private val lock = Object()
    private var sleep = 0L
    val status: Status get() = threadStatus.get()

    init {
        val thread = Thread({
            while (status != Status.DESTROYED) {
                if (status == Status.SLEEPING) {
                    try {
                        Thread.sleep(sleep)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    if (status == Status.SLEEPING) {
                        threadStatus.set(Status.RUNNING)
                    }
                }
                if (status == Status.PAUSED || status == Status.STOPPED) {
                    synchronized(lock) {
                        try {
                            lock.wait()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                if (canTick()) {
                    tick()
                } else {
                    stop()
                }
            }
        })
        thread.name = "SimCoreWorker-${++ID}"
        thread.start()
    }

    open fun canTick() = true

    open fun tick() {}

    open fun beforeStart() {}

    open fun afterStop() {}

    fun sleep(millis: Long) {
        if (millis > 0 && status == Status.RUNNING) {
            sleep = millis
            threadStatus.set(Status.SLEEPING)
        }
    }

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
        val s = status
        if (s != Status.DESTROYED && (s == Status.RUNNING || s == Status.SLEEPING)) {
            threadStatus.set(Status.PAUSED)
            if (log) println(status)
        }
    }

    fun stop() {
        val s = status
        if (s != Status.DESTROYED && (s == Status.RUNNING || s == Status.SLEEPING)) {
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