import java.io.File

fun main() {
    val commands = File("input.txt").readLines().map {
        val split = it.split(" ")
        Command(split[0], split[1].toInt())
    }
    val position = Position(0, 0, 0)
    for (i in commands) {
        when (i.command) {
            "forward" -> {
                position.x+= i.value
                position.y+= i.value * position.aim
            }
            "down" -> position.aim+= i.value
            "up" -> position.aim-= i.value
        }
    }
    println(position)
    println(position.x * position.y)
}

data class Command(val command: String, val value: Int)
data class Position(var x: Int, var y: Int, var aim: Int)