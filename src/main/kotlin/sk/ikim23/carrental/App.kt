package sk.ikim23.carrental

import sk.ikim23.carrental.core.SimCore
import sk.ikim23.carrental.random.ExpRandom
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

fun main(args: Array<String>) {
//    val rand = ExpRandom(1.0/6)
//    val bw = BufferedWriter(FileWriter(File("input.txt")))
//    for (i in 1..1_00_000) {
//        bw.append(rand.nextDouble().toString())
//        bw.newLine()
//    }
//    bw.close()
    val avgTime = LinkedList<Double>()
    val avgQueue = LinkedList<Double>()
    for (rep in 1..1_000) {
        val core = SimCore()
        core.start(30 * 24 * 60 * 1.0)
//        core.stats.printStats()
        avgTime.add(core.stats.avgTime())
        avgQueue.add(core.stats.avgQueue())
    }
    val time = avgTime.average()
    val queue = avgQueue.average()
    println("Average system time: ${time}")
    println("Average queue size: ${queue}")
}
