package sk.ikim23.carrental

import sk.ikim23.carrental.core.impl.SimCore

fun main(args: Array<String>) {
    val core = SimCore(daysToSec(30), 2, 8)
    core.start()
    println("Avg sys time: ${core.stats.averageSystemTime()}")
    println("Avg bus round time: ${core.stats.averageRoundTime()}")
    println("Bus capacity: ${core.stats.averageBusUsage()}")
    println("service usage: ${core.serviceDesk.averageSize()}")
    println("Avg T1 queue: ${core.t1Queue.averageSize()}")
    println("Avg T2 queue: ${core.t2Queue.averageSize()}")
    println("Avg desk queue: ${core.rentalQueue.averageSize()}")

}
