package sk.ikim23.carrental

import org.junit.Test
import sk.ikim23.carrental.random.ExpRandom

class RandomTest {
    @Test
    fun customerArrivalsT1() {
        val expectedArrivalsPerHour = 43.0
        val rand = ExpRandom(expectedArrivalsPerHour / (60 * 60))
        var time = 0.0
        var count = 0
        100_000.times {
            time += rand.nextDouble()
            count++
        }
        val arrivalsPerHour = count / (time / (60 * 60))
        assert(Math.abs(expectedArrivalsPerHour - arrivalsPerHour) <= 0.3) {
            "$arrivalsPerHour should be $expectedArrivalsPerHour"
        }
    }

    @Test
    fun customerArrivalsT2() {
        val expectedArrivalsPerHour = 19.0
        val rand = ExpRandom(expectedArrivalsPerHour / (60 * 60))
        var time = 0.0
        var count = 0
        100_000.times {
            time += rand.nextDouble()
            count++
        }
        val arrivalsPerHour = count / (time / (60 * 60))
        assert(Math.abs(expectedArrivalsPerHour - arrivalsPerHour) <= 0.3) {
            "$arrivalsPerHour should be $expectedArrivalsPerHour"
        }
    }
}