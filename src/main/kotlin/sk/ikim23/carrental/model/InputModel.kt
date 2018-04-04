package sk.ikim23.carrental.model

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class InputModel(val listener: InputChangeListener, nReps: Int, nBuses: Int, nEmployees: Int) {
    interface InputChangeListener {
        fun onChange(nReps: Int, nBuses: Int, nEmployees: Int)
    }

    val nReplications = SimpleIntegerProperty(nReps)
    val busFrom = SimpleIntegerProperty(nBuses)
    val busTo = SimpleIntegerProperty(nBuses)
    val employeesFrom = SimpleIntegerProperty(nEmployees)
    val employeesTo = SimpleIntegerProperty(nEmployees)

    init {
        nReplications.onChange { promoteChange() }
        this.busFrom.onChange { promoteChange() }
        this.employeesFrom.onChange { promoteChange() }
    }

    private fun promoteChange() {
        listener.onChange(nReplications.get(), busFrom.get(), employeesFrom.get())
    }
}