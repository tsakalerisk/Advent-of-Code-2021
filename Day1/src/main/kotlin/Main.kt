import java.io.File

fun main() {
    val nums = File("input.txt").readLines().map { it.toInt() }
    //println(countIncreases(nums))
    print(countGroupIncreases(nums, 3))
}

fun countGroupIncreases(nums: List<Int>, groupSize: Int): Int {
    var lastSum = groupSum(nums, 0, groupSize)
    var increased = 0
    for (i in 1..nums.size - groupSize) {
        val newSum = groupSum(nums, i, groupSize)
        if (newSum > lastSum) increased++
        lastSum = newSum
    }
    return increased
}

fun groupSum(nums: List<Int>, start: Int, size: Int) = nums.subList(start, start + size).reduce { a, b -> a + b }

fun countIncreases(nums: List<Int>): Int {
    var lastNum = nums[0]
    var increased = 0
    for (i in 1 until nums.size) {
        if (nums[i] > lastNum) increased++
        lastNum = nums[i]
    }
    return increased
}