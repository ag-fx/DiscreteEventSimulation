package sk.ikim23.carrental.core

import sk.ikim23.carrental.withTryCatch
import java.util.*
import java.util.concurrent.atomic.AtomicReference

open class Pauseable(private val log: Boolean = false) {
    enum class Status {
        RUNNING, PAUSED, STOPPED, SLEEPING, DESTROYED
    }

    companion object {
        var ID = 0
        private val threads = LinkedList<Pauseable>()

        fun destroyAll() {
            threads.forEach { it.destroy() }
            threads.clear()
        }
    }

    private val threadStatus = AtomicReference(Status.STOPPED)
    private val lock = Object()
    private var sleep = 0L
    private var nRepsToStop = 0
    val status: Status get() = threadStatus.get()

    init {
        val thread = Thread({
            while (true) {
                if (status == Status.SLEEPING) {
                    withTryCatch {
                        Thread.sleep(sleep)
                    }
                    if (status == Status.SLEEPING) {
                        threadStatus.set(Status.RUNNING)
                    }
                }
                if (status == Status.PAUSED || status == Status.STOPPED) {
                    synchronized(lock) {
                        withTryCatch {
                            lock.wait()
                        }
                    }
                }
                if (status == Status.DESTROYED) {
                    return@Thread
                }
                if (canTick()) {
                    tick()
                } else {
                    stop()
                    if (nRepsToStop-- > 0) {
                        threadStatus.set(Status.RUNNING)
                        beforeStart()
                    }
                }
            }
        })
        thread.name = "SimCoreWorker-${++ID}"
        thread.start()
        threads.add(this)
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

    fun start(nReps: Int = 1) {
        nRepsToStop = nReps
        val s = status
        if (s == Status.DESTROYED) {
            throw IllegalStateException("Destroyed instance can not be started")
        } else if (s == Status.PAUSED || s == Status.STOPPED) {
            if (s == Status.STOPPED) beforeStart()
            threadStatus.set(Status.RUNNING)
            synchronized(lock) {
                withTryCatch {
                    lock.notifyAll()
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
        if (s != Status.DESTROYED && s != Status.STOPPED) {
            threadStatus.set(Status.STOPPED)
            synchronized(lock) {
                withTryCatch {
                    lock.notifyAll()
                }
            }
            if (log) println(status)
            afterStop()
        }
    }

    fun destroy() {
        if (status != Status.DESTROYED) {
            threadStatus.set(Status.DESTROYED)
            synchronized(lock) {
                withTryCatch {
                    lock.notifyAll()
                }
            }
            if (log) println(status)
        }
    }
}