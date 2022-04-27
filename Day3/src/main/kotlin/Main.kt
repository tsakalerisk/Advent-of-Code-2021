import java.io.File

fun main() {
    //Part 1
    val lines = File("input.txt").readLines().map { it -> it.map { it.digitToInt() } }
    val mostCommon = mutableListOf<Int>()
    val leastCommon = mutableListOf<Int>()
    for (i in 0 until lines[0].size) {
        val freq = digitFrequencies(lines, i)
        freq.maxByOrNull { it.value }?.let { mostCommon.add(it.key) }
        freq.minByOrNull { it.value }?.let { leastCommon.add(it.key) }
    }
    val gamma = binListToInt(mostCommon)
    val epsilon = binListToInt(leastCommon)
    println("Gamma: $gamma")
    println("Epsilon: $epsilon")
    println("Power consumption (Part 1 answer): " + gamma * epsilon)

    //Part 2
    val comparator = { x: Map.Entry<Int, Int>, y: Map.Entry<Int, Int> ->
        when {
            (x.value > y.value) -> 1
            (x.value == y.value && x.key == 1) -> 1
            else -> -1
        }
    }
    val oxygenCandidates = lines.toMutableList()
    for (i in 0 until oxygenCandidates[0].size) {
        val mostCommonDigit = digitFrequencies(oxygenCandidates, i).maxWithOrNull(comparator)?.key
        oxygenCandidates.removeIf { it[i] != mostCommonDigit }
    }
    val co2Candidates = lines.toMutableList()
    for (i in 0 until co2Candidates[0].size) {
        val leastCommonDigit = digitFrequencies(co2Candidates, i).minWithOrNull(comparator)?.key
        co2Candidates.removeIf { it[i] != leastCommonDigit }
    }

    val oxygenRating = binListToInt(oxygenCandidates[0])
    val co2Rating = binListToInt(co2Candidates[0])
    println("Oxygen generator rating: $oxygenRating")
    println("CO2 scrubber rating: $co2Rating")
    println("Life support rating (Part 2 Answer): " + oxygenRating * co2Rating)
}

fun binListToInt(list: List<Int>): Int = Integer.parseInt(list.joinToString(separator = ""), 2)

fun digitFrequencies(list: List<List<Int>>, digit: Int): Map<Int, Int> = list.groupingBy { it[digit] }.eachCount()