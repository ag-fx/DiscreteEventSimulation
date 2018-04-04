package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.Core

interface ISimListener {
    enum class Step(val title: String, val value: Int) {
        MINUTE("minute", 60),
        HOUR("hour", 60 * 60),
        DAY("day", 24 * 60 * 60),
        NONE("none", 24 * 60 * 60)
    }

    val timeStep: Step

    fun onDone(core: Core, stats: IStats)
    fun onStep(stats: IStats)
}