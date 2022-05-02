import java.io.File

fun main() {
    val state = File("input.txt").readText().trim().split(",")
        .groupingBy { it.toInt() }.eachCount()
        .mapValues { it.value.toLong() }

    //Part 1
    println("Fish after 80 days: ${calculatePopulation(state, 80)}")

    //Part 2
    println("Fish after 256 days: ${calculatePopulation(state, 256)}")
}

private fun calculatePopulation(initialState: Map<Int, Long>, days: Int): Long {
    var currentState = initialState.toMap()

    for (i in 1..days) {
        val newState = mutableMapOf<Int, Long>()
        currentState.forEach {
            when (it.key) {
                0 -> {
                    newState[6] = newState[6]?.plus(it.value) ?: it.value
                    newState[8] = it.value
                }
                else -> newState[it.key - 1] = newState[it.key - 1]?.plus(it.value) ?: it.value
            }
        }
        currentState = newState
    }

    return currentState.values.sum()
}