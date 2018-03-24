package sk.ikim23.carrental.controller

import javafx.beans.property.SimpleStringProperty
import javafx.scene.chart.XYChart
import sk.ikim23.carrental.core.SimCore
import sk.ikim23.carrental.model.InputModel
import sk.ikim23.carrental.random.ExpRandom
import tornadofx.*

class MainController : Controller() {
    private val core = SimCore()
    private val xSeries = XYChart.Series<Number, Number>()
    private val ySeries = XYChart.Series<Number, Number>()
    val chartData = listOf(xSeries, ySeries).observable()
    val model = InputModel(1_000_000)
    val avgQueueLengthProperty = SimpleStringProperty(0.toString())
    val avgSystemTimeProperty = SimpleStringProperty(0.toString())

    fun start() {
//        core.start(24.0 * 60)
//        println("Queue length: ${core.stats.avgLength()}")
//        println("System time: ${core.stats.avgSystemTime()}")
    }
}