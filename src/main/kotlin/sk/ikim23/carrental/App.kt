package sk.ikim23.carrental

import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.multithread.SimConfig
import sk.ikim23.carrental.multithread.SimCorePool

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()
    val service = SimCorePool(object : SimCorePool.ISimCorePoolListener {
        override fun onStatsUpdate(stats: IStats) {
        }

        override fun onDone(stats: IStats) {
            stats.print()
            val time = System.currentTimeMillis() - start
            println("done, took: $time ms")
        }
    }, 1_000, SimConfig(30, 5, 8))
    service.start()
}
