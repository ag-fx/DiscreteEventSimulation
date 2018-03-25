package sk.ikim23.carrental.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty

class InputModel(nReps: Int, nBuses: Int, nEmployees: Int) {
    val nReplications = SimpleIntegerProperty(nReps)
    val nBuses = SimpleIntegerProperty(nBuses)
    val nEmployees = SimpleIntegerProperty(nEmployees)
    val disableControls = SimpleBooleanProperty()
    val speed = SimpleDoubleProperty(50.0)
}