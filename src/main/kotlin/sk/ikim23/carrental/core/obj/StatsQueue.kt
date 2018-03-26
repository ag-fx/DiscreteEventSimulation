package sk.ikim23.carrental.core.obj

import sk.ikim23.carrental.core.ITime
import java.util.*

class StatsQueue<T>(val time: ITime) {
    private val queue: Queue<T> = LinkedList()
    private var sumSize = 0.0
    private var lastTime = 0.0

    fun isEmpty() = queue.isEmpty()

    fun size() = queue.size

    fun add(item: T) {
        calcStats()
        queue.add(item)
    }

    fun remove(): T {
        calcStats()
        return queue.remove()
    }

    fun remove(item: T): Boolean {
        calcStats()
        return queue.remove(item)
    }

    fun clear() {
        queue.clear()
        sumSize = 0.0
        lastTime = 0.0
    }

    fun averageSize() = sumSize / lastTime

    private fun calcStats() {
        sumSize += queue.size * (time.currentTime - lastTime)
        lastTime = time.currentTime
    }
}