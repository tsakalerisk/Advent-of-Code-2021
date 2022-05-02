import java.io.File
import kotlin.math.abs

fun main() {
    val positions = File("input.txt").readText().trim().split(",").map { it.toInt() }.sorted()

    //Part 1
    val median = positions[positions.size / 2]
    println("Position: $median")
    val totalFuelPart1 = positions.sumOf { abs(it - median) }
    println("Total fuel (Part 1): $totalFuelPart1")

    //Part 2
    val average = positions.average().toInt()
    val totalFuelPart2 = positions.sumOf {
        val dist = abs(it - average)
        dist * (dist + 1) / 2
    }
    println("Total fuel (Part 2): $totalFuelPart2")
}