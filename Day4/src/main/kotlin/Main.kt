import java.io.File

fun main() {
    val lines = File("input.txt").readText().split("\n\n")
    val nums = lines[0].split(",").map { it.toInt() }

    val boardsPart1 = lines.drop(1).map { Board(it) }.withIndex()
    val boardsPart2 = boardsPart1.toMutableList()

    //Part 1
    var winningBoards = emptyList<IndexedValue<Board>>()
    for (num in nums) {
        boardsPart1.forEach { it.value.checkNumber(num) }
        winningBoards = boardsPart1.filter { it.value.checkWin() }
        if (winningBoards.isNotEmpty()) break
    }
    println("Winning boards: " + winningBoards.map { it.index })
    println("Score: " + winningBoards[0].value.calcScore())

    //Part 2
    var losingBoards = emptyList<IndexedValue<Board>>()
    for (num in nums) {
        boardsPart2.forEach { it.value.checkNumber(num) }
        losingBoards = boardsPart2.filter { it.value.checkWin() }
        boardsPart2.removeAll(losingBoards)
        if (boardsPart2.isEmpty()) break
    }
    println("Losing board: " + losingBoards.map { it.index })
    println("Score: " + losingBoards[0].value.calcScore())
}

data class Number(val number: Int, var isDrawn: Boolean = false)