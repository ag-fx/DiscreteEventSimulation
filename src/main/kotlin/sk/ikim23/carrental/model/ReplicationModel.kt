package sk.ikim23.carrental.model

import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.IStatsListener
import sk.ikim23.carrental.formatTime

class ReplicationModel : IStatsListener {
    private val initValue = 0.0.toString()
    val systemTime = SimpleStringProperty(formatTime(0.0))
    val averageSystemTime = SimpleStringProperty(initValue)
    val averageRoundTime = SimpleStringProperty(initValue)
    val averageBusUsage = SimpleStringProperty(initValue)
    val averageT1QueueSize = SimpleStringProperty(initValue)
    val averageT2QueueSize = SimpleStringProperty(initValue)
    val averageServiceDeskQueueSize = SimpleStringProperty(initValue)
    val averageServiceDeskUsage = SimpleStringProperty(initValue)
    val speed = SimpleDoubleProperty(50.0)
    override val timeStep = 1.0
    override val timeSpeed get() = 1.0 - (speed.get() / 100.0)

    override fun onUpdate(stats: IStats) = update(stats)
    override fun onDone(stats: IStats) {
        update(stats)
        stats.print()
    }

    fun update(stats: IStats) {
        Platform.runLater {
            systemTime.set(formatTime(stats.systemTime()))
            averageSystemTime.set(stats.averageSystemTime().toString())
            averageRoundTime.set(stats.averageRoundTime().toString())
            averageBusUsage.set(stats.averageBusUsage().toString())
            averageT1QueueSize.set(stats.averageT1QueueSize().toString())
            averageT2QueueSize.set(stats.averageT2QueueSize().toString())
            averageServiceDeskQueueSize.set(stats.averageServiceDeskQueueSize().toString())
            averageServiceDeskUsage.set(stats.averageServiceDeskUsage().toString())
        }
    }
}