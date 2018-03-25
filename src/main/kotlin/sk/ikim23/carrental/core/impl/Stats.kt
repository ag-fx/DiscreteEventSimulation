package sk.ikim23.carrental.core.impl

import sk.ikim23.carrental.core.obj.Bus
import sk.ikim23.carrental.core.obj.Customer

class Stats(val core: SimCore) : IStats {
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

    override fun averageSystemTime() = (sumTime / nCustomers) / 60

    override fun averageRoundTime() = (sumRoundTime / nBuses) / 60

    override fun averageBusUsage() = sumBusUsage / nBuses

    override fun averageT1QueueSize() = core.t1Queue.averageSize()

    override fun averageT2QueueSize() = core.t2Queue.averageSize()

    override fun averageServiceDeskQueueSize() = core.serviceDesk.averageSize()

    override fun averageServiceDeskUsage() = core.serviceDesk.averageUsage()

    override fun clear() {
        nBuses = 0
        nCustomers = 0
        sumTime = 0.0
        sumRoundTime = 0.0
        sumBusUsage = 0.0
    }
}