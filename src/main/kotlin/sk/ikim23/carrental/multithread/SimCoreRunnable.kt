package sk.ikim23.carrental.multithread

import sk.ikim23.carrental.core.impl.SimCore

class SimCoreRunnable(val stats: ConcurrentStats, val endTime: Double, val nBuses: Int, val nDesks: Int) : Runnable {
    override fun run() {
        while (!stats.needNext()) {
            val core = SimCore(endTime, nBuses, nDesks)
            core.start()
            stats.take(core.stats)
        }
    }
}