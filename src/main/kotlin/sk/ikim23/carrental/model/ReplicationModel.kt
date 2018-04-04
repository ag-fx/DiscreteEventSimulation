package sk.ikim23.carrental.model

import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import sk.ikim23.carrental.core.Core
import sk.ikim23.carrental.core.impl.ISimListener
import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.formatTime
import tornadofx.*

class ReplicationModel: StatsModel(), ISimListener {
    override fun onDone(core: Core, stats: IStats) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val systemTime = SimpleStringProperty(formatTime(0.0))
    val steps = ISimListener.Step.values().asList().observable()
    val step = SimpleObjectProperty(steps.first())
    override val timeStep get() = step.get()

    override fun onStep(stats: IStats) {
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