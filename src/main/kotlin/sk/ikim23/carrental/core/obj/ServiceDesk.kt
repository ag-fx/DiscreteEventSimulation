package sk.ikim23.carrental.core.obj

import java.util.*

class ServiceDesk(val capacity: Int) {
    private val servedCustomers = LinkedList<Customer>()

    fun isFull() = servedCustomers.size >= capacity

    fun isEmpty() = servedCustomers.isEmpty()

    fun add(customer: Customer) {
        if (isFull()) throw IllegalArgumentException()
        servedCustomers.add(customer)
    }

    fun remove(customer: Customer) = servedCustomers.remove(customer)
}