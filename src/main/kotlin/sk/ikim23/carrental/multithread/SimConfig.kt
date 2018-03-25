package sk.ikim23.carrental.multithread

import sk.ikim23.carrental.daysToSec

class SimConfig(nDays: Int, val nBuses: Int, val nDesks: Int) {
    val endTime = daysToSec(nDays)
}