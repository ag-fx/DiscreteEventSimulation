package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer

class Stats {
    private var sumTime = 0.0
    private var nCustomers = 0
    private var sumRoundTime = 0.0
    private var nRounds = 0

    fun takeCustomer(customer: Customer) {
        sumTime += customer.serviceTime - customer.arrivalToTerminal
        nCustomers++
    }

    fun takeBus(bus: Bus) {
        val roundTime = bus.arrival - bus.departure
        if (roundTime > 0) {
            sumRoundTime += roundTime
            nRounds++
        }
    }

    fun avgSystemTime() = (sumTime / nCustomers) / 60

    fun avgRoundTime() = (sumRoundTime / nRounds) / 60

    fun reset() {
        sumTime = 0.0
        nCustomers = 0
        sumRoundTime = 0.0
        nRounds = 0
    }
}