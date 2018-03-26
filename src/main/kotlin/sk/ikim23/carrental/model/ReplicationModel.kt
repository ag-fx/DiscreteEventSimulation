package sk.ikim23.carrental.model

import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.IStatsListener
import sk.ikim23.carrental.formatTime
import tornadofx.*

class ReplicationModel : IStatsListener {
    val systemTime = SimpleStringProperty(formatTime(0.0))
    val averageSystemTime = SimpleDoubleProperty()
    val nUsers = SimpleIntegerProperty()
    val averageRoundTime = SimpleDoubleProperty()
    val nBusRounds = SimpleIntegerProperty()
    val averageBusUsage = SimpleDoubleProperty()
    val averageT1QueueSize = SimpleDoubleProperty()
    val averageT2QueueSize = SimpleDoubleProperty()
    val averageServiceDeskQueueSize = SimpleDoubleProperty()
    val averageServiceDeskUsage = SimpleDoubleProperty()
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
            nUsers.set(stats.customerCount())
            averageSystemTime.set(stats.averageSystemTime())
            nBusRounds.set(stats.roundCount())
            averageRoundTime.set(stats.averageRoundTime())
            averageBusUsage.set(stats.averageBusUsage())
            averageT1QueueSize.set(stats.averageT1QueueSize())
            averageT2QueueSize.set(stats.averageT2QueueSize())
            averageServiceDeskQueueSize.set(stats.averageServiceDeskQueueSize())
            averageServiceDeskUsage.set(stats.averageServiceDeskUsage())
        }
    }
}