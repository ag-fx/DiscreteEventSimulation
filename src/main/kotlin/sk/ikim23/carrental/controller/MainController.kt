package sk.ikim23.carrental.controller

import sk.ikim23.carrental.core.impl.SimManager
import sk.ikim23.carrental.model.InputModel
import sk.ikim23.carrental.model.ReplicationModel
import sk.ikim23.carrental.model.SummaryModel
import tornadofx.*

class MainController : Controller(), InputModel.InputChangeListener {
    private var nReplications = 1_000
    val inputModel = InputModel(this, nReplications, 5, 8)
    val summaryModel = SummaryModel()
    val replicationModel = ReplicationModel()
    val manager = SimManager(replicationModel, summaryModel)

    init {
        manager.init(inputModel.busFrom.get(), inputModel.employeesFrom.get())
    }

    override fun onChange(nReps: Int, nBuses: Int, nEmployees: Int) {
        nReplications = nReps
        manager.init(inputModel.busFrom.get(), inputModel.employeesFrom.get())
    }

    fun start() {
        replicationModel.clear()
        summaryModel.clear()
//        manager.startSlowMo(nReplications)
    }

    fun pause() {
        manager.pause()
    }

    fun stop() {
        manager.stop()
    }
}