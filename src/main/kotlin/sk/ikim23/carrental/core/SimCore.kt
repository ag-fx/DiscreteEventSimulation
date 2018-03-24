package sk.ikim23.carrental.core

import sk.ikim23.carrental.problem.ArrivalEvent
import sk.ikim23.carrental.problem.Customer
import sk.ikim23.carrental.problem.Stats
import sk.ikim23.carrental.random.ExpRandom
import java.util.*

class SimCore {
    val print = false
    private val eventQueue = PriorityQueue<Event>()
    private var endTime = Double.MIN_VALUE
    var currentTime = 0.0
        private set
    val rArrival = ExpRandom(1.0 / 6)
    val rService = ExpRandom(1.0 / 5)
    private val customerQueue = LinkedList<Customer>()
    val stats = Stats()

    fun hasCustomers() = customerQueue.isNotEmpty()

    fun peekCustomer(): Customer = customerQueue.peek()

    fun pushCustomer(customer: Customer) {
        customerQueue.add(customer)
        stats.addQueueSize(customerQueue.size, currentTime)
    }

    fun popCustomer(): Customer {
        val customer = customerQueue.poll()
        stats.addQueueSize(customerQueue.size, currentTime)
        return customer
    }

    fun hasTime() = currentTime <= endTime

    fun addEvent(event: Event) {
        if (hasTime())
            eventQueue.add(event)
    }

    fun start(endTime: Double) {
        init(endTime)
        while (hasTime() && eventQueue.isNotEmpty()) {
            val event = eventQueue.poll()
            currentTime = event.time
            event.exec()
        }
    }

    private fun init(end: Double) {
        endTime = end
        val firstArrival = ArrivalEvent(this, rArrival.nextDouble())
        addEvent(firstArrival)
    }
}