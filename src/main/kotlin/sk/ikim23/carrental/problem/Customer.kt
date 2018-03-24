package sk.ikim23.carrental.problem

class Customer(val arrival: Double) {
    val id = ID++
    var service = Double.MIN_VALUE

    override fun toString() = "Customer($id)"

    fun waitTime() = service - arrival

    companion object {
        var ID = 0L
    }
}