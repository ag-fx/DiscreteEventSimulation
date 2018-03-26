package sk.ikim23.carrental.model

import javafx.application.Platform
import javafx.beans.property.SimpleIntegerProperty
import sk.ikim23.carrental.core.impl.IStats

class SummaryModel : StatsModel() {
    private val sumStats = SummaryStats()
    val nReplications = SimpleIntegerProperty()

    override fun update(stats: IStats) {
        sumStats.update(stats)
        super.update(sumStats)
        Platform.runLater {
            nReplications.set(sumStats.nReps)
        }
    }

    override fun clear() {
        super.clear()
        sumStats.clear()
        super.update(sumStats)
        Platform.runLater {
            nReplications.set(0)
        }
    }
}