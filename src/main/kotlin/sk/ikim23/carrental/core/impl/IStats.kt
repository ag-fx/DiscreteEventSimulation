package sk.ikim23.carrental.core.impl

interface IStats {
    fun systemTime(): Double
    fun customerCount(): Int
    fun averageSystemTime(): Double
    fun roundCount(): Int
    fun averageRoundTime(): Double
    fun averageBusUsage(): Double
    fun averageT1QueueSize(): Double
    fun averageT2QueueSize(): Double
    fun averageServiceDeskQueueSize(): Double
    fun averageServiceDeskUsage(): Double
    fun clear()

    fun print() {
        println("System time: ${systemTime()}")
        println("Average user time: ${averageSystemTime()}")
        println("Average bus round time: ${averageRoundTime()}")
        println("Average bus usage: ${averageBusUsage()}")
        println("Average T1 queue: ${averageT1QueueSize()}")
        println("Average T2 queue: ${averageT2QueueSize()}")
        println("Average service desk queue: ${averageServiceDeskQueueSize()}")
        println("Average service desk usage: ${averageServiceDeskUsage()}")
    }
}