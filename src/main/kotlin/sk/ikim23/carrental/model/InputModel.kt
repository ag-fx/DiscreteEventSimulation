package sk.ikim23.carrental.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class InputModel(val listener: InputChangeListener, nReps: Int, nBuses: Int, nEmployees: Int) {
    interface InputChangeListener {
        fun onChange(nReps: Int, nBuses: Int, nEmployees: Int)
    }

    val nReplications = SimpleIntegerProperty(nReps)
    val nBuses = SimpleIntegerProperty(nBuses)
    val nEmployees = SimpleIntegerProperty(nEmployees)
    val disableControls = SimpleBooleanProperty()

    init {
        nReplications.onChange { promoteChange() }
        this.nBuses.onChange { promoteChange() }
        this.nEmployees.onChange { promoteChange() }
    }

    private fun promoteChange() {
        listener.onChange(nReplications.get(), nBuses.get(), nEmployees.get())
    }
}