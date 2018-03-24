package sk.ikim23.carrental.controller

import javafx.beans.property.SimpleStringProperty
import javafx.scene.chart.XYChart
import sk.ikim23.carrental.core.Core
import sk.ikim23.carrental.model.InputModel
import tornadofx.*

class MainController : Controller() {
//    private val manager = Core()
    private val xSeries = XYChart.Series<Number, Number>()
    private val ySeries = XYChart.Series<Number, Number>()
    val chartData = listOf(xSeries, ySeries).observable()
    val model = InputModel(1_000_000)
    val avgQueueLengthProperty = SimpleStringProperty(0.toString())
    val avgSystemTimeProperty = SimpleStringProperty(0.toString())

    fun start() {
//        manager.start(24.0 * 60)
//        println("StatsQueue length: ${manager.stats.avgLength()}")
//        println("System execTime: ${manager.stats.averageSystemTime()}")
    }
}