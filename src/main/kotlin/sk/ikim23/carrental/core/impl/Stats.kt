package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.ITimeManager
import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer

class Stats(val manager: ITimeManager) {
    private var nBuses = 0
    private var nCustomers = 0
    private var sumTime = 0.0
    private var sumRoundTime = 0.0
    private var sumBusUsage = 0.0

    fun take(customer: Customer) {
        sumTime += customer.serviceTime - customer.arrivalTime
        nCustomers++
    }

    fun take(bus: Bus) {
        val roundTime = manager.currentTime() - bus.departure
        bus.departure = manager.currentTime()
        if (roundTime > 0) {
            sumRoundTime += roundTime
            sumBusUsage += bus.usedCapacity()
            nBuses++
        }
    }

    fun averageSystemTime() = (sumTime / nCustomers) / 60

    fun averageRoundTime() = (sumRoundTime / nBuses) / 60

    fun averageBusUsage() = sumBusUsage / nBuses

    fun reset() {
        nBuses = 0
        nCustomers = 0
        sumTime = 0.0
        sumRoundTime = 0.0
        sumBusUsage = 0.0
    }
}