package sk.ikim23.carrental.model

import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.IStatsListener
import sk.ikim23.carrental.formatTime
import tornadofx.*

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
    val steps = IStatsListener.Step.values().asList().observable()
    val step = SimpleObjectProperty(steps.first())
    override val timeStep get() = step.get()

    override fun onUpdate(stats: IStats) = update(stats)
    override fun onDone(stats: IStats) {
        update(stats)
        stats.print()
    }

    private fun update(stats: IStats) {
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