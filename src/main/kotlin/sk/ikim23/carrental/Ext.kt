package sk.ikim23.carrental

fun Int.times(call: (i: Int) -> Unit) {
    for (index in 0 until this) call(index)
}

fun daysToSec(days: Int) = (days * 24 * 60 * 60).toDouble()