package sk.ikim23.carrental.core.obj

import sk.ikim23.carrental.core.ITime
import sk.ikim23.carrental.safeDiv

class ServiceDesk(val time: ITime, val capacity: Int) {
    private val servedCustomers = StatsQueue<Customer>(time)

    fun isFull() = servedCustomers.size() >= capacity

    fun add(customer: Customer) {
        if (isFull()) throw IllegalArgumentException("${javaClass.simpleName} is already full")
        servedCustomers.add(customer)
    }

    fun remove(customer: Customer) {
        if (!servedCustomers.remove(customer)) throw IllegalStateException("$customer was not found")
        customer.serviceTime = time.currentTime
    }

    fun clear() = servedCustomers.clear()

    fun averageSize() = servedCustomers.averageSize()

    fun averageUsage() = averageSize() safeDiv capacity
}