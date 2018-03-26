package sk.ikim23.carrental.view

import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.text.Font
import sk.ikim23.carrental.controller.MainController
import sk.ikim23.carrental.core.impl.IStatsListener
import tornadofx.*

class MainView : View() {
    override val root = borderpane()
    val cWidth = 70.0
    val cPadding = Insets(5.0)
    val cSpacing = 5.0
    val cAlignment = Pos.CENTER_LEFT
    val controller: MainController by inject()

    init {
        title = "AirCar Rental"
        root.top {
            vbox {
                hbox {
                    padding = cPadding
                    spacing = cSpacing
                    alignment = cAlignment
                    label("Replications:")
                    textfield {
                        prefWidth = cWidth
                        textProperty().bindBidirectional(controller.inputModel.nReplications, IntConverter())
                        disableProperty().bind(controller.inputModel.disableControls)
                    }
                    label("Bus:")
                    textfield {
                        prefWidth = cWidth
                        textProperty().bindBidirectional(controller.inputModel.nBuses, IntConverter())
                        disableProperty().bind(controller.inputModel.disableControls)
                    }
                    label("Employees:")
                    textfield {
                        prefWidth = cWidth
                        textProperty().bindBidirectional(controller.inputModel.nEmployees, IntConverter())
                        disableProperty().bind(controller.inputModel.disableControls)
                    }
                    button("Start") {
                        prefWidth = cWidth
                        disableProperty().bind(controller.inputModel.disableControls)
                        setOnAction { controller.start() }
                    }
                    button("Pause") {
                        prefWidth = cWidth
                        setOnAction { controller.pause() }
                    }
                    button("Stop") {
                        prefWidth = cWidth
                        setOnAction { controller.stop() }
                    }
                    label("Step by:")
                    combobox<IStatsListener.Step> {
                        items = controller.repModel.steps
                        valueProperty().bindBidirectional(controller.repModel.step)
                        cellFormat { text = it.title }
                    }
                }
                separator()
            }
        }
        root.center {
            hbox {
                padding = cPadding
                spacing = cSpacing
                gridpane {
                    hgap = cSpacing
                    vgap = cSpacing
                    row {
                        label("Time:")
                        label { textProperty().bind(controller.repModel.systemTime) }
                    }
                    row {
                        label("Users:")
                        label { textProperty().bind(controller.repModel.nUsers.asString()) }
                    }
                    row {
                        label("User time:")
                        label { textProperty().bind(controller.repModel.averageSystemTime.asString()) }
                    }
                    row {
                        label("Bus rounds:")
                        label { textProperty().bind(controller.repModel.nBusRounds.asString()) }
                    }
                    row {
                        label("Bus round time:")
                        label { textProperty().bind(controller.repModel.averageRoundTime.asString()) }
                    }
                    row {
                        label("Bus usage:")
                        label { textProperty().bind(controller.repModel.averageBusUsage.asString()) }
                    }
                    row {
                        label("T1 queue length:")
                        label { textProperty().bind(controller.repModel.averageT1QueueSize.asString()) }
                    }
                    row {
                        label("T2 queue length:")
                        label { textProperty().bind(controller.repModel.averageT2QueueSize.asString()) }
                    }
                    row {
                        label("Service desk queue length:")
                        label { textProperty().bind(controller.repModel.averageServiceDeskQueueSize.asString()) }
                    }
                    row {
                        label("Service desk usage:")
                        label { textProperty().bind(controller.repModel.averageServiceDeskUsage.asString()) }
                    }
                    children.forEach { if (it is Label) it.font = Font("Arial", 16.0) }
                }
                separator(Orientation.VERTICAL)
            }
        }
    }
}