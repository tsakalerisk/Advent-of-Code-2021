class Board(s: String) {
    private val numbers: List<List<Number>>
    private var lastNum: Int? = null

    init {
        numbers = s.trim().split("\n").map { line ->
            line.trim().split("\\s+".toRegex()).map {
                Number(it.toInt())
            }
        }
    }

    fun checkNumber(num: Int) {
        lastNum = num
        numbers.forEach { i -> i.forEach { if (it.number == num) it.isDrawn = true } }
    }

    fun checkWin() = checkRows() || checkCols()

    fun calcScore(): Int? {
        val unmarkedSum = numbers.map { i -> i.filter { !it.isDrawn }.sumOf { it.number } }.sumOf { it }
        return unmarkedSum * (lastNum ?: return null)
    }

    private fun checkRows(): Boolean {
        for (i in numbers)
            if (i.all { it.isDrawn })
                return true
        return false
    }

    private fun checkCols(): Boolean {
        for (j in numbers[0].indices)
            if (numbers.all { it[j].isDrawn })
                return true
        return false
    }
}