package sk.ikim23.carrental.multithread

import sk.ikim23.carrental.core.impl.IStats

class ConcurrentStats(val nReps: Int) : IStats {
    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var reps = 0
    private var sumAvgSystemTime = 0.0
    private var sumAvgRoundTime = 0.0
    private var sumAvgBusUsage = 0.0
    private var sumAvgT1QueueSize = 0.0
    private var sumAvgT2QueueSize = 0.0
    private var sumAvgServiceDeskQueueSize = 0.0
    private var sumAvgServiceDeskUsage = 0.0

    override fun averageSystemTime() = sumAvgSystemTime / reps
    override fun averageRoundTime() = sumAvgRoundTime / reps
    override fun averageBusUsage() = sumAvgBusUsage / reps
    override fun averageT1QueueSize() = sumAvgT1QueueSize / reps
    override fun averageT2QueueSize() = sumAvgT2QueueSize / reps
    override fun averageServiceDeskQueueSize() = sumAvgServiceDeskQueueSize / reps
    override fun averageServiceDeskUsage() = sumAvgServiceDeskUsage / reps

    @Synchronized
    fun take(stats: IStats) {
        sumAvgSystemTime += stats.averageSystemTime()
        sumAvgRoundTime += stats.averageRoundTime()
        sumAvgBusUsage += stats.averageBusUsage()
        sumAvgT1QueueSize += stats.averageT1QueueSize()
        sumAvgT2QueueSize += stats.averageT2QueueSize()
        sumAvgServiceDeskQueueSize += stats.averageServiceDeskQueueSize()
        sumAvgServiceDeskUsage += stats.averageServiceDeskUsage()
    }

    @Synchronized
    fun needNext(): Boolean {
        val needNext = reps < nReps
        if (needNext) reps++
        return needNext
    }
}