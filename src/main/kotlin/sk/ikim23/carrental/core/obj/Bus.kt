package sk.ikim23.carrental.core.obj

import java.util.*

class Bus(val capacity: Int = 12) {
    companion object {
        var ID = 1
    }

    private val passengers: Queue<Customer> = LinkedList()
    val id = ID++
    var departure = Double.MAX_VALUE
    var arrival = 0.0

    fun isFull() = passengers.size >= capacity

    fun isEmpty() = passengers.isEmpty()

    fun size() = passengers.size

    fun add(customer: Customer) {
        if (isFull()) throw IllegalStateException("$this is already full")
        passengers.add(customer)
    }

    fun remove(): Customer = passengers.remove()

    override fun toString() = "Bus(id=$id, size=${passengers.size})"
}