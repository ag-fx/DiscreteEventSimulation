package sk.ikim23.carrental.model

import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.IStatsListener
import sk.ikim23.carrental.formatTime
import tornadofx.*

class ReplicationModel(val model: StatsModel) : StatsModel(), IStatsListener {
    val systemTime = SimpleStringProperty(formatTime(0.0))
    val steps = IStatsListener.Step.values().asList().observable()
    val step = SimpleObjectProperty(steps.first())
    override val timeStep get() = step.get()

    override fun onUpdate(stats: IStats) = update(stats)
    override fun onDone(stats: IStats) {
        update(stats)
        model.update(stats)
    }

    override fun update(stats: IStats) {
        super.update(stats)
        Platform.runLater {
            systemTime.set(formatTime(stats.systemTime()))
        }
    }

    override fun clear() {
        super.clear()
        Platform.runLater {
            systemTime.set(formatTime(0.0))
        }
    }
}