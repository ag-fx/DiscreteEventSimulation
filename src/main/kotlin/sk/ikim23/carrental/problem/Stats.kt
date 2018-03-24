package sk.ikim23.carrental.problem

import java.util.*

class Stats {
    val sysTime = LinkedList<Double>()
    val queueSize = LinkedList<Pair<Int, Double>>()
    var lastQueueSize = 0
    var lastQueueTime = 0.0

    fun addSysTime(c: Customer) {
        val time = c.service - c.arrival
        sysTime.add(time)
    }

    fun addQueueSize(size: Int, time: Double) {
        val duration = time - lastQueueTime
        queueSize.add(Pair(lastQueueSize, duration))
        lastQueueSize = size
        lastQueueTime = time
    }

    fun avgTime() = sysTime.average()

    fun avgQueue(): Double {
        val up = queueSize.sumByDouble { (size, duration) -> size * duration }
        val down = queueSize.sumByDouble { it.second }
        val avgQueue = up / down
        if (avgQueue.isNaN()) return 0.0
        return avgQueue
    }

    fun printStats() {
        println("Average system time: ${avgTime()}")
        println("Average queue size: ${avgQueue()}")
    }
}