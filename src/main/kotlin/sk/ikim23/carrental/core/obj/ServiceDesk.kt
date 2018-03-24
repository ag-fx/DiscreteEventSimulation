package sk.ikim23.carrental.core.obj

import sk.ikim23.carrental.core.ITimeManager

class ServiceDesk(val manager: ITimeManager, val capacity: Int) {
    private val servedCustomers = StatsQueue<Customer>(manager)

    fun isFull() = servedCustomers.size() >= capacity

    fun add(customer: Customer) {
        if (isFull()) throw IllegalArgumentException("${javaClass.simpleName} is already full")
        customer.serviceTime = manager.currentTime()
        servedCustomers.add(customer)
    }

    fun remove(customer: Customer) {
        if(!servedCustomers.remove(customer)) throw IllegalStateException("$customer was not found")
    }

    fun averageSize() = servedCustomers.averageSize()
}