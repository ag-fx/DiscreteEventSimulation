package sk.ikim23.carrental.view

import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.chart.NumberAxis
import sk.ikim23.carrental.controller.MainController
import tornadofx.*

class MainView : View() {
    override val root = borderpane()
    val cWidth = 70.0
    val cPadding = Insets(5.0)
    val cSpacing = 5.0
    val cAlignment = Pos.CENTER_LEFT
    val controller: MainController by inject()

    init {
        title = "Shop Queue"
        root.top {
            vbox {
                hbox {
                    padding = cPadding
                    spacing = cSpacing
                    alignment = cAlignment
                    label("Replications:")
                    textfield {
                        prefWidth = cWidth
                        textProperty().bindBidirectional(controller.model.replicationsProperty, IntConverter())
                        disableProperty().bind(controller.model.disableControlsProperty)
                    }
                    label("Customer arrival [min]:")
                    textfield {
                        prefWidth = cWidth
                        textProperty().bindBidirectional(controller.model.arrivalTimeProperty, IntConverter())
                        disableProperty().bind(controller.model.disableControlsProperty)
                    }
                    label("Service duration [min]:")
                    textfield {
                        prefWidth = cWidth
                        textProperty().bindBidirectional(controller.model.serviceTimeProperty, IntConverter())
                        disableProperty().bind(controller.model.disableControlsProperty)
                    }
                    button("Start") {
                        prefWidth = cWidth
                        disableProperty().bind(controller.model.disableControlsProperty)
                        setOnAction { controller.start() }
                    }
                }
                separator()
            }
        }
        root.center {
            linechart("", NumberAxis(), NumberAxis()) {
                xAxis.animated = false
                yAxis.animated = false
                animated = false
                data = controller.chartData
            }
        }
        root.bottom {
            vbox {
                separator()
                hbox {
                    padding = cPadding
                    spacing = cSpacing
                    alignment = cAlignment
                    val valueWidth = 120.0
                    label("Avg. queue length:")
                    label {
                        prefWidth = valueWidth
                        textProperty().bind(controller.avgQueueLengthProperty)
                    }
                    separator(Orientation.VERTICAL)
                    label("Avg. execTime in system:")
                    label {
                        prefWidth = valueWidth
                        textProperty().bind(controller.avgSystemTimeProperty)
                    }
                }
            }
        }
    }
}