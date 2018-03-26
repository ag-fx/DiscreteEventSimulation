package sk.ikim23.carrental.model

import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.safeDiv

class SummaryStats : IStats {
    private var sumRounds = 0
    private var sumCustomers = 0
    private var sumSysTime = 0.0
    private var sumRoundTime = 0.0
    private var sumBusUsage = 0.0
    private var sumT1QueueSize = 0.0
    private var sumT2QueueSize = 0.0
    private var sumServiceDeskQueueSize = 0.0
    private var sumServiceDeskUsage = 0.0
    var nReps = 0
        private set

    override fun systemTime() = 0.0
    override fun customerCount() = sumCustomers
    override fun averageSystemTime() = sumSysTime safeDiv nReps
    override fun roundCount() = sumRounds
    override fun averageRoundTime() = sumRoundTime safeDiv nReps
    override fun averageBusUsage() = sumBusUsage safeDiv nReps
    override fun averageT1QueueSize() = sumT1QueueSize safeDiv nReps
    override fun averageT2QueueSize() = sumT2QueueSize safeDiv nReps
    override fun averageServiceDeskQueueSize() = sumServiceDeskQueueSize safeDiv nReps
    override fun averageServiceDeskUsage() = sumServiceDeskUsage safeDiv nReps

    override fun clear() {
        nReps = 0
        sumRounds = 0
        sumCustomers = 0
        sumSysTime = 0.0
        sumRoundTime = 0.0
        sumBusUsage = 0.0
        sumT1QueueSize = 0.0
        sumT2QueueSize = 0.0
        sumServiceDeskQueueSize = 0.0
        sumServiceDeskUsage = 0.0
    }

    fun update(stats: IStats) {
        nReps++
        sumRounds += stats.roundCount()
        sumCustomers += stats.customerCount()
        sumSysTime += stats.averageSystemTime()
        sumRoundTime += stats.averageRoundTime()
        sumBusUsage += stats.averageBusUsage()
        sumT1QueueSize += stats.averageT1QueueSize()
        sumT2QueueSize += stats.averageT2QueueSize()
        sumServiceDeskQueueSize += stats.averageServiceDeskQueueSize()
        sumServiceDeskUsage += stats.averageServiceDeskUsage()
    }
}