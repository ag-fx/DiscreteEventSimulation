package sk.ikim23.carrental

import sk.ikim23.carrental.core.impl.SimCore

fun main(args: Array<String>) {
    val core = SimCore(daysToSec(1), 20, 20)
    core.start()
    println("Avg sys time: ${core.stats.avgSystemTime()}")
    println(core.stats.avgRoundTime())
}
