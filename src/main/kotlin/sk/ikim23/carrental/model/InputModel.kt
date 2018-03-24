package sk.ikim23.carrental.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty

class InputModel(reps: Int) {
    val replicationsProperty = SimpleIntegerProperty(reps)
    val disableControlsProperty = SimpleBooleanProperty()
    val arrivalTimeProperty = SimpleIntegerProperty()
    val serviceTimeProperty = SimpleIntegerProperty()
}