fun countStep1(input: List<Int>): Int {
    return input.windowed(2).count { (a, b) -> a < b }
}

// A + B + C <= B + C + D
fun countStep2(input: List<Int>): Int {
    return input.windowed(4).count { it[0] < it[3] }
}

fun main() {
    val input = readInputAsInt("day1/input")
    println(countStep2(input))

//    input.forEach { println(it) }


}

