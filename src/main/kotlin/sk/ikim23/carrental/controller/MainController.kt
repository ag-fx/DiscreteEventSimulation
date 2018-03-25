package sk.ikim23.carrental.controller

import sk.ikim23.carrental.model.InputModel
import sk.ikim23.carrental.model.ReplicationModel
import tornadofx.*

class MainController : Controller() {
    val inputModel = InputModel(1_000_000, 5, 8)
    val repModel = ReplicationModel()
//    val core = SimCore()

    fun start() {
    }
}