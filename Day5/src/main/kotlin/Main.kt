import java.io.File

const val mapSize = 1000

fun main() {
    val lines = File("input.txt").readLines().map { line ->
        Line("\\d+".toRegex().findAll(line).map { it.value.toInt() }.toList())
    }
    val mapPart1 = IntArray(mapSize * mapSize)
    val mapPart2 = mapPart1.toTypedArray()

    //Part 1
    for (line in lines) {
        if (line.isVerticalOrHorizontal())
            for (point in line.startToEnd())
                mapPart1[point.x + mapSize * point.y]++
    }
    println("Vertical or horizontal lines: ${mapPart1.count { it >= 2 }}")

    //Part 2
    for (line in lines)
        for (point in line.startToEnd())
            mapPart2[point.x + mapSize * point.y]++
    println("All lines: ${mapPart2.count { it >= 2 }}")
}

class Line(private val start: Point, private val end: Point) {
    constructor(list: List<Int>) : this(Point(list[0], list[1]), Point(list[2], list[3]))

    fun isVerticalOrHorizontal() = start.x == end.x || start.y == end.y

    fun startToEnd(): List<Point> {
        val points = mutableListOf<Point>()
        if (start.y == end.y)
            for (i in start.x toward end.x) points.add(Point(i, start.y))
        else if (start.x == end.x)
            for (j in start.y toward end.y) points.add(Point(start.x, j))
        else for ((i, j) in (start.x toward end.x).zip(start.y toward end.y))
            points.add(Point(i, j))
        return points
    }
}

infix fun Int.toward(to: Int): IntProgression {
    val step = if (this < to) 1 else -1
    return IntProgression.fromClosedRange(this, to, step)
}

data class Point(val x: Int, val y: Int)