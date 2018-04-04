package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Core
import sk.ikim23.carrental.core.Pauseable
import sk.ikim23.carrental.core.stats.BaseStats
import sk.ikim23.carrental.daysToSec
import sk.ikim23.carrental.model.ReplicationModel
import sk.ikim23.carrental.model.SummaryModel
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class SimManager(val rm: ReplicationModel, val sm: SummaryModel, val nCores: Int = 4) : ISimListener {
    override val timeStep get() = rm.timeStep
    private val cores = Array(nCores, { SimCore(this) })
    private val nReps = AtomicInteger()
    private var nBuses = 0
    private var nEmployees = 0
    private val baseStats = BaseStats()
    private var nPrint = 0
    private var configs = LinkedList<Pair<Int, Int>>()

    override fun onDone(core: Core, stats: IStats) {
        baseStats.take(stats)
        if (nReps.decrementAndGet() >= 0) {
            core.start()
        } else {
            if (nPrint++ >= 3) {
                println("bus: $nBuses empl: $nEmployees")
                baseStats.print()
                nEmployees++
                if (nEmployees > 20) {
                    nEmployees = 8
                    nBuses++
                    if (nBuses > 10) return
                }
                init(nBuses, nEmployees)
                start(1000)
            }
        }
        rm.clear()
    }

    override fun onStep(stats: IStats) {
        rm.onStep(stats)
    }

    fun init(nBuses: Int, nEmployees: Int) {
        this.nBuses = nBuses
        this.nEmployees = nEmployees
        nPrint = 0
        cores.forEach {
            it.init(daysToSec(30), nBuses, nEmployees)
        }
    }

    fun start(reps: Int, confs: List<Pair<Int, Int>>) {
        configs.addAll(confs)
        val c = configs.poll()
        if (c == null) return
    }

    fun start(reps: Int) {
        if (nReps.get() > 0) return
        baseStats.reset()
        val min = Math.min(nCores, reps)
        nReps.set(reps - min)
        for (i in 0 until min) {
            cores[i].start()
        }
    }

    fun unpause() {
        cores.forEach {
            if (it.status == Pauseable.Status.PAUSED) {
                it.start()
            }
        }
    }

    fun pause() {
        cores.forEach { it.pause() }
    }

    fun stop() {
        nReps.set(0)
        cores.forEach { it.stop() }
    }
}