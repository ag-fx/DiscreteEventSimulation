package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer

class Stats(val core: SimCore) {
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
        val roundTime = core.currentTime - bus.departure
        bus.departure = core.currentTime
        if (roundTime > 0) {
            sumRoundTime += roundTime
            sumBusUsage += bus.usedCapacity()
            nBuses++
        }
    }

    fun averageSystemTime() = (sumTime / nCustomers) / 60

    fun averageRoundTime() = (sumRoundTime / nBuses) / 60

    fun averageBusUsage() = sumBusUsage / nBuses

    fun print() {
        println("Average user time: ${averageSystemTime()}")
        println("Average bus round time: ${averageRoundTime()}")
        println("Average bus usage: ${averageBusUsage()}")
        println("Average T1 queue: ${core.t1Queue.averageSize()}")
        println("Average T2 queue: ${core.t2Queue.averageSize()}")
        println("Average service desk queue: ${core.rentalQueue.averageSize()}")
        println("Average service desk usage: ${core.serviceDesk.averageUsage()}")
    }

    fun reset() {
        nBuses = 0
        nCustomers = 0
        sumTime = 0.0
        sumRoundTime = 0.0
        sumBusUsage = 0.0
    }
}