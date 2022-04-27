import java.io.File

fun main() {
    //Part 1
    val lines = File("input.txt").readLines().map { it -> it.map { it.digitToInt() } }
    val mostCommon = mutableListOf<Int>()
    val leastCommon = mutableListOf<Int>()
    for (i in 0 until lines[0].size) {
        lines.groupingBy { it[i] }.eachCount().maxByOrNull { it.value }?.let { mostCommon.add(it.key) }
        lines.groupingBy { it[i] }.eachCount().minByOrNull { it.value }?.let { leastCommon.add(it.key) }
    }
    val gamma = binListToInt(mostCommon)
    val epsilon = binListToInt(leastCommon)
    println("Gamma: $gamma")
    println("Epsilon: $epsilon")
    println("Power consumption (Part 1 answer): " + gamma * epsilon)

    //Part 2
    val comparator = { x: Map.Entry<Int,Int>, y: Map.Entry<Int,Int> ->
        when {
            (x.value > y.value) -> 1
            (x.value == y.value && x.key == 1) -> 1
            else -> -1
        }
    }
    val oxygenCandidates = lines.toMutableList()
    for (i in 0 until oxygenCandidates[0].size) {
        val mostCommonDigit = oxygenCandidates.groupingBy { it[i] }.eachCount().maxWithOrNull(comparator)?.key
        oxygenCandidates.removeIf { it[i] != mostCommonDigit }
        if (oxygenCandidates.isEmpty()) break
    }
    val co2Candidates = lines.toMutableList()
    for (i in 0 until co2Candidates[0].size) {
        val leastCommonDigit = co2Candidates.groupingBy { it[i] }.eachCount().minWithOrNull(comparator)?.key
        co2Candidates.removeIf { it[i] != leastCommonDigit }
        if (co2Candidates.isEmpty()) break
    }

    if (oxygenCandidates.isNotEmpty() && co2Candidates.isNotEmpty()) {
        val oxygenRating = binListToInt(oxygenCandidates[0])
        val co2Rating = binListToInt(co2Candidates[0])
        println("Oxygen generator rating: $oxygenRating")
        println("CO2 scrubber rating: $co2Rating")
        println("Life support rating (Part 2 Answer): " + oxygenRating * co2Rating)
    }
    else
        println("One of the lists is empty!")
}

fun binListToInt(list: List<Int>):Int = Integer.parseInt(list.joinToString(separator = ""), 2)