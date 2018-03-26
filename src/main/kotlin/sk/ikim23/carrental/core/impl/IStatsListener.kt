package sk.ikim23.carrental.core.impl

interface IStatsListener {
    enum class Step(val title: String, val value: Int) {
        MINUTE("minute", 60),
        HOUR("hour", 60 * 60),
        DAY("day", 24 * 60 * 60),
        NONE("none", 24 * 60 * 60)
    }

    val timeStep: Step
    fun onUpdate(stats: IStats)
    fun onDone(stats: IStats)
}