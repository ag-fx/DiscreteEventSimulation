package sk.ikim23.carrental

import sk.ikim23.carrental.core.impl.SimCore

fun main(args: Array<String>) {
    val core = SimCore(daysToSec(30), 2, 8)
    core.start()
    core.stats.print()
}
