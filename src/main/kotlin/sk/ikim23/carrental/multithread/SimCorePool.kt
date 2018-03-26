package sk.ikim23.carrental.multithread

import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.IStatsListener
import sk.ikim23.carrental.core.impl.SimCore
import sk.ikim23.carrental.daysToSec

class SimCorePool : IStatsListener {
    override val timeStep = IStatsListener.Step.NONE
    var sumSysTime = 0.0
    var reps = 0

    override fun onUpdate(stats: IStats) {}

    @Synchronized
    override fun onDone(stats: IStats) {
        sumSysTime += stats.averageSystemTime()
        reps++
        println("$reps: ${sumSysTime / reps}")
    }

    fun start(nReps: Int, nBuses: Int, nDesks: Int) {
        sumSysTime = 0.0
        reps = 0
        startCore(nReps / 4, nBuses, nDesks)
        startCore(nReps / 4, nBuses, nDesks)
        startCore(nReps / 4, nBuses, nDesks)
        startCore(nReps / 4, nBuses, nDesks)
    }

    private fun startCore(nReps: Int, nBuses: Int, nDesks: Int) {
        val core = SimCore()
        core.init(daysToSec(30), nBuses, nDesks, this)
        core.start(nReps)
    }
}