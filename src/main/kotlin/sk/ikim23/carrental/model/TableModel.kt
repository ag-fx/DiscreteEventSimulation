package sk.ikim23.carrental.model

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty

class TableModel {
    val nBus = SimpleIntegerProperty()
    val nEmployees = SimpleIntegerProperty()
    val lowerBound = SimpleDoubleProperty()
    val upperBound = SimpleDoubleProperty()
    val average = SimpleDoubleProperty()
}