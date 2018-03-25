package sk.ikim23.carrental.multithread

import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.SimCore
import sk.ikim23.carrental.times
import kotlin.concurrent.thread

class SimCorePool(val listener: ISimCorePoolListener, val nReps: Int, val config: SimConfig, nThreads: Int? = null) {
    interface ISimCorePoolListener {
        fun onStatsUpdate(stats: IStats)
        fun onDone(stats: IStats)
    }

    private val lock = Object()
    private var nRunningThreads = nThreads ?: Math.max(1, Runtime.getRuntime().availableProcessors() / 2)
    private val stats = ConcurrentStats(nReps)

    fun start() {
//        nRunningThreads.times {
//            thread {
//                while (stats.needNext()) {
//                    val core = SimCore(config.endTime, config.nBuses, config.nDesks)
//                    core.start()
//                    stats.take(core.stats)
//                    listener.onStatsUpdate(stats)
//                }
//                synchronized(lock) {
//                    nRunningThreads--
//                    if (nRunningThreads <= 0) {
//                        listener.onDone(stats)
//                    }
//                }
//            }
//        }
    }
}