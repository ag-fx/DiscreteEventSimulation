package sk.ikim23.carrental.view

import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.Priority
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
    val replicationView = StatsView(controller.replicationModel)
    val summaryView = StatsView(controller.summaryModel)

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
                        items = controller.replicationModel.steps
                        valueProperty().bindBidirectional(controller.replicationModel.step)
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
                vbox {
                    hbox {
                        label("Time:")
                        label {
                            maxWidth = Double.MAX_VALUE
                            hgrow = Priority.ALWAYS
                            alignment = Pos.CENTER_RIGHT
                            textProperty().bind(controller.replicationModel.systemTime)
                        }
                        children.forEach { if (it is Label) it.font = Font("Arial", 16.0) }
                    }
                    children.add(replicationView.root)
                }
                separator(Orientation.VERTICAL)
                vbox {
                    hbox {
                        label("Replications:")
                        label {
                            maxWidth = Double.MAX_VALUE
                            hgrow = Priority.ALWAYS
                            alignment = Pos.CENTER_RIGHT
                            textProperty().bind(controller.summaryModel.nReplications.asString())
                        }
                        children.forEach { if (it is Label) it.font = Font("Arial", 16.0) }
                    }
                    children.add(summaryView.root)
                }
            }
        }
    }
}