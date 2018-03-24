package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer

class Stats {
    private var nBuses = 0
    private var nCustomers = 0
    private var sumTime = 0.0
    private var sumRoundTime = 0.0
    private var sumBusUsedCapacity = 0.0

    fun take(customer: Customer) {
        sumTime += customer.serviceTime - customer.arrivalToTerminal
        nCustomers++
    }

    fun take(bus: Bus) {
        val roundTime = bus.arrival - bus.departure
        if (roundTime > 0) {
            sumRoundTime += roundTime
            sumBusUsedCapacity += bus.size().toDouble() / bus.capacity
            nBuses++
        }
    }

    fun avgSystemTime() = (sumTime / nCustomers) / 60

    fun avgRoundTime() = (sumRoundTime / nBuses) / 60

    fun busUsedCapacity() = sumBusUsedCapacity / nBuses

    fun reset() {
        nBuses = 0
        nCustomers = 0
        sumTime = 0.0
        sumRoundTime = 0.0
        sumBusUsedCapacity = 0.0
    }
}