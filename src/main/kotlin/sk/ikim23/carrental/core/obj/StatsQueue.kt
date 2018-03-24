package sk.ikim23.carrental.core.obj

import sk.ikim23.carrental.core.ITimeManager
import java.util.*

class StatsQueue<T>(val manager: ITimeManager) {
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

    fun averageSize() = sumSize / lastTime

    private fun calcStats() {
        sumSize += queue.size * (manager.currentTime() - lastTime)
        lastTime = manager.currentTime()
    }
}