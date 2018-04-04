package sk.ikim23.carrental.core.stats

import sk.ikim23.carrental.core.impl.IStats
import sk.ikim23.carrental.core.impl.Stats
import sk.ikim23.carrental.safeDiv

class BaseStats {
    private var nReps = 0
    private var nCustomers = 0
    private var sumTime = 0.0
    private var sumTimeSquare = 0.0

    fun take(stats: IStats) {
        nReps++
        if (stats is Stats) {
            nCustomers += stats.nCustomers
            sumTime += stats.sumTime
            sumTimeSquare += stats.sumTimeSquare
        }
    }

    fun calc(): List<Double> {
        val w = sumTime / nCustomers
        val s = Math.sqrt(sumTimeSquare / nCustomers - w * w)
        val avg = sumTime / nCustomers
        val left = avg - 1.96 * s / Math.sqrt((nCustomers - 1).toDouble())
        val right = avg + 1.96 * s / Math.sqrt((nCustomers - 1).toDouble())
        return arrayOf(left, avg, right).map { it safeDiv 60 }
    }

    fun reset() {
        nReps = 0
        nCustomers = 0
        sumTime = 0.0
        sumTimeSquare = 0.0
    }

    fun print() {
        val a = calc()
        println("Lower: ${a[0]}")
        println("Avg:   ${a[1]}")
        println("Upper: ${a[2]}")
    }
}