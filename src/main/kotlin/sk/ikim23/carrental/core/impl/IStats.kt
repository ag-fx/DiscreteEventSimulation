package sk.ikim23.carrental.core.impl

interface IStats {
    fun averageSystemTime(): Double
    fun averageRoundTime(): Double
    fun averageBusUsage(): Double
    fun averageT1QueueSize(): Double
    fun averageT2QueueSize(): Double
    fun averageServiceDeskQueueSize(): Double
    fun averageServiceDeskUsage(): Double
    fun print() {
        println("Average user time: ${averageSystemTime()}")
        println("Average bus round time: ${averageRoundTime()}")
        println("Average bus usage: ${averageBusUsage()}")
        println("Average T1 queue: ${averageT1QueueSize()}")
        println("Average T2 queue: ${averageT2QueueSize()}")
        println("Average service desk queue: ${averageServiceDeskQueueSize()}")
        println("Average service desk usage: ${averageServiceDeskUsage()}")
    }
}