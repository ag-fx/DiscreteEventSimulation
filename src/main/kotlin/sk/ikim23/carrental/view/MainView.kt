package sk.ikim23.carrental.view

import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.chart.NumberAxis
import javafx.scene.control.Label
import javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import sk.ikim23.carrental.controller.MainController
import sk.ikim23.carrental.core.impl.ISimListener
import sk.ikim23.carrental.model.TableModel
import tornadofx.*

class MainView : View() {
    override val root = borderpane()
    val cWidth = 70.0
    val sWidth = 50.0
    val cPadding = Insets(5.0)
    val cSpacing = 5.0
    val cAlignment = Pos.CENTER_LEFT
    val controller: MainController by inject()
    val replicationView = StatsView(controller.replicationModel)
    val summaryView = StatsView(controller.summaryModel)
    val tableRows = listOf(TableModel()).observable()

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
                    }
                    label("Bus:")
                    textfield {
                        prefWidth = sWidth
                        textProperty().bindBidirectional(controller.inputModel.busFrom, IntConverter())
                    }
                    label("to")
                    textfield {
                        prefWidth = sWidth
                        textProperty().bindBidirectional(controller.inputModel.busTo, IntConverter())
                    }
                    label("Employees:")
                    textfield {
                        prefWidth = sWidth
                        textProperty().bindBidirectional(controller.inputModel.employeesFrom, IntConverter())
                    }
                    label("to")
                    textfield {
                        prefWidth = sWidth
                        textProperty().bindBidirectional(controller.inputModel.employeesTo, IntConverter())
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
                    combobox<ISimListener.Step> {
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
                    padding = cPadding
                    spacing = cSpacing
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
                    textarea {
                        vgrow = Priority.ALWAYS
                    }
                }
                separator(Orientation.VERTICAL)
                vbox {
                    padding = cPadding
                    spacing = cSpacing
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
                    hbox {
                        linechart("g1", NumberAxis(), NumberAxis()) {

                        }
                        linechart("g2", NumberAxis(), NumberAxis()) {

                        }
                    }
                    tableview(tableRows) {
                        column("Bus", TableModel::nBus)
                        column("Employees", TableModel::nEmployees)
                        column("Lower bound", TableModel::lowerBound)
                        column("Average", TableModel::average)
                        column("Upper bound", TableModel::upperBound)
                        columnResizePolicy = CONSTRAINED_RESIZE_POLICY
                    }
                }
            }
        }
    }
}