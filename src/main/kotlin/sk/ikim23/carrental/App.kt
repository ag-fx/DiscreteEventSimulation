package sk.ikim23.carrental

import sk.ikim23.carrental.core.impl.SimCore

fun main(args: Array<String>) {
    val core = SimCore(daysToSec(30), 2, 2)
    core.start()
    println("Avg sys time: ${core.stats.avgSystemTime()}")
    println("Avg bus round time: ${core.stats.avgRoundTime()}")
    println("Bus capacity: ${core.stats.busUsedCapacity()}")
}
