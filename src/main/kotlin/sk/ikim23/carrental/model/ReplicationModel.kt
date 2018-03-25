package sk.ikim23.carrental.model

import javafx.beans.property.SimpleStringProperty

class ReplicationModel {
    private val initValue = 0.0.toString()
    val averageSystemTime = SimpleStringProperty(initValue)
    val averageRoundTime = SimpleStringProperty(initValue)
    val averageBusUsage = SimpleStringProperty(initValue)
    val averageT1QueueSize = SimpleStringProperty(initValue)
    val averageT2QueueSize = SimpleStringProperty(initValue)
    val averageServiceDeskQueueSize = SimpleStringProperty(initValue)
    val averageServiceDeskUsage = SimpleStringProperty(initValue)
}