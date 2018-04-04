//package sk.ikim23.carrental.model
//
//import sk.ikim23.carrental.core.impl.IStats
//import sk.ikim23.carrental.safeDiv
//
//class SummaryStats : IStats {
//    private var sumRounds = 0
//    private var sumCustomers = 0
//    private var sumSysTime = 0.0
//    private var sumRoundTime = 0.0
//    private var sumBusUsage = 0.0
//    private var sumT1QueueSize = 0.0
//    private var sumT2QueueSize = 0.0
//    private var sumServiceDeskQueueSize = 0.0
//    private var sumServiceDeskUsage = 0.0
//    var nReps = 0
//        private set
//
//    override fun systemTime() = 0.0
//    override fun customerCount() = sumCustomers
//    override fun sumAvgSysTime() = sumSysTime safeDiv nReps
//    override fun roundCount() = sumRounds
//    override fun avgRoundTime() = sumRoundTime safeDiv nReps
//    override fun avgBusUsage() = sumBusUsage safeDiv nReps
//    override fun avgT1QueueSize() = sumT1QueueSize safeDiv nReps
//    override fun avgT2QueueSize() = sumT2QueueSize safeDiv nReps
//    override fun avgServiceDeskQueueSize() = sumServiceDeskQueueSize safeDiv nReps
//    override fun avgServiceDeskUsage() = sumServiceDeskUsage safeDiv nReps
//
//    override fun clear() {
//        nReps = 0
//        sumRounds = 0
//        sumCustomers = 0
//        sumSysTime = 0.0
//        sumRoundTime = 0.0
//        sumBusUsage = 0.0
//        sumT1QueueSize = 0.0
//        sumT2QueueSize = 0.0
//        sumServiceDeskQueueSize = 0.0
//        sumServiceDeskUsage = 0.0
//    }
//
//    fun update(stats: IStats) {
//        nReps++
//        sumRounds += stats.roundCount()
//        sumCustomers += stats.customerCount()
//        sumSysTime += stats.sumAvgSysTime()
//        sumRoundTime += stats.avgRoundTime()
//        sumBusUsage += stats.avgBusUsage()
//        sumT1QueueSize += stats.avgT1QueueSize()
//        sumT2QueueSize += stats.avgT2QueueSize()
//        sumServiceDeskQueueSize += stats.avgServiceDeskQueueSize()
//        sumServiceDeskUsage += stats.avgServiceDeskUsage()
//    }
//}