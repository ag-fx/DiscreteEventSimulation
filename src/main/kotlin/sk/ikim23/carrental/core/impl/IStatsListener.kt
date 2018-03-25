package sk.ikim23.carrental.core.impl

interface IStatsListener {
    val timeStep: Double
    val timeSpeed: Double
    fun onUpdate(stats: IStats)
    fun onDone(stats: IStats)
}