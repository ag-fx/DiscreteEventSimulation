package sk.ikim23.carrental.controller

import sk.ikim23.carrental.core.impl.SimCore
import sk.ikim23.carrental.daysToSec
import sk.ikim23.carrental.model.InputModel
import sk.ikim23.carrental.model.ReplicationModel
import tornadofx.*

class MainController : Controller() {
    val inputModel = InputModel(1_000_000, 5, 8)
    val repModel = ReplicationModel()
    val core = SimCore()
    var init = false

    fun start() {
        if (!init) {
            init = true
            core.init(daysToSec(30), inputModel.nBuses.get(), inputModel.nEmployees.get(), repModel, false)
        }
        core.start()
    }

    fun pause() {
        core.pause()
    }

    fun stop() {
        core.stop()
    }
}