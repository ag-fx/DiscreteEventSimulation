package sk.ikim23.carrental.controller

import sk.ikim23.carrental.core.impl.SimCore
import sk.ikim23.carrental.daysToSec
import sk.ikim23.carrental.model.InputModel
import sk.ikim23.carrental.model.ReplicationModel
import sk.ikim23.carrental.model.SummaryModel
import sk.ikim23.carrental.multithread.SimCorePool
import tornadofx.*

class MainController : Controller(), InputModel.InputChangeListener {
    private var nReplications = 1_000
    val inputModel = InputModel(this, nReplications, 5, 8)
    val summaryModel = SummaryModel()
    val replicationModel = ReplicationModel(summaryModel)
    val core = SimCore()

    init {
        core.init(daysToSec(30), inputModel.nBuses.get(), inputModel.nEmployees.get(), replicationModel)
    }

    override fun onChange(nReps: Int, nBuses: Int, nEmployees: Int) {
        nReplications = nReps
        core.init(daysToSec(30), nBuses, nEmployees, replicationModel)
    }

    fun start() {
//        replicationModel.clear()
//        summaryModel.clear()
//        core.start(nReplications)
        SimCorePool().start(nReplications, inputModel.nBuses.get(), inputModel.nEmployees.get())
    }

    fun pause() {
        core.pause()
    }

    fun stop() {
        core.stop()
    }
}