import java.io.File

fun main() {
    // Part 1
    val lines = File("input.txt").readLines()
    val outputs = lines.map { line -> line.split("|")[1].trim().split(" ") }
    val filteredOutputs = outputs.map { output ->
        output.filter { it.length in setOf(2, 3, 4, 7) }
    }
    println(filteredOutputs.sumOf { it.size })

    // Part 2
    val numValues = mutableListOf<Int>()
    for (line in lines) {
        val (digits, outputs) = line.split("|").map { it.trim().split(" ") }
        val connections = mutableMapOf<Int, Set<Char>>()
        connections[1] = digits.filter { it.length == 2 }[0].toSet()
        connections[7] = digits.filter { it.length == 3 }[0].toSet()
        connections[4] = digits.filter { it.length == 4 }[0].toSet()
        connections[8] = digits.filter { it.length == 7 }[0].toSet()
        connections[9] = digits.filter {
            it.length == 6 && connections[4]?.let { it1 ->
                it.toSet().containsAll(it1)
            } == true
        }[0].toSet()
        connections[5] = digits.filter {
            it.length == 5 && connections[9]?.let { con9 ->
                con9.subtract(it.toSet()).size == 1 && connections[7]?.let { con7 -> !it.toSet().containsAll(con7) } == true
            } == true
        }[0].toSet()
        connections[3] =
            digits.filter { it.length == 5 && connections[5]?.let { con5 -> con5.subtract(it.toSet()).size == 1 } == true }[0].toSet()
        connections[2] =
            digits.filter { it.length == 5 && connections[5]?.let { con5 -> con5.subtract(it.toSet()).size == 2 } == true }[0].toSet()
        connections[6] = digits.filter { it.length == 6 && connections[1]?.let { con1 -> !it.toSet().containsAll(con1) } == true }[0].toSet()
        connections[0] = digits.filter { it.length == 6 && connections[5]?.let { con5 -> !it.toSet().containsAll(con5) } == true }[0].toSet()

        val connectionsReversed = connections.entries.associateBy({ it.value }) { it.key }
        var num = 0
        for (output in outputs) {
            num = num * 10 + (connectionsReversed[output.toSet()] ?: 0)
        }
        numValues.add(num)
    }
    println(numValues.sum())
}