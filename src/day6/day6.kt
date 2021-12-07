import java.lang.Integer.max
import java.lang.Math.min

fun naiveSimulator(input: List<Int>, days: Int): List<Int> {
    var previousList = input
    for (i in 1..days) {
        val newGeneration = mutableListOf<Int>()
        var amountOfNew = 0
        previousList.forEach { v ->
            if (v == 0) {
                newGeneration.add(6)
                amountOfNew++
            } else {
                newGeneration.add(v - 1)
            }

        }
        for (newFish in 1..amountOfNew) {
            newGeneration.add(8)
        }
//        println(newGeneration)
        previousList = newGeneration
    }
    return previousList
}

fun naiveSimulator2(input: List<Int>, iteration: Int): List<Int> {
    var amountOfNew = 0
    val newGeneration = input.map { x ->
        val y = (x - iteration) % 7
        if (y >= 0) {
            if (y == 0) {
                amountOfNew++
            }
            y
        } else 7 + y
    }.toMutableList()
    for (i in 1..amountOfNew) {
        newGeneration.add(8)
    }
    return newGeneration
}

fun totalAmount(startPosition: Int, allIterations: Int): Long {
    val childrenNumber = countChild(startPosition, allIterations)
    var totalDescendant: Long = childrenNumber.toLong()
    for (j in 1..childrenNumber) {
        val positionFirstChild = startPosition + 9
        var startPosition = positionFirstChild
        if (j > 1) {
            startPosition = positionFirstChild + (j - 1) * 7
        }
        val amountOfChildren = totalAmount(startPosition, allIterations)
        totalDescendant += amountOfChildren
    }
    return totalDescendant
}

fun countChild(startPosition: Int, allIterations: Int): Int {
    val leftIteration = (allIterations - startPosition)
    return when (leftIteration) {
        in 0..8 -> 0
        in 9..15 -> 1
        else -> ((leftIteration - 9) / 7) + 1
    }
}


fun main() {
    var input = readInput("day6/input")[0].split(",").map(String::toInt)

    val amountOfGeneration = 256
    var total: ULong = 0u
    val inputLifecycleDay = 2
    val startPosition = inputLifecycleDay - 8
    input.groupBy { it }
        .forEach { inputLifecycleDay, multipy ->
            val startPosition = inputLifecycleDay - 8
            val totalDescendant = multipy.size.toULong()
                .times(totalAmount(startPosition, amountOfGeneration).toULong())
            total += totalDescendant
        }

    total += input.size.toULong()
    println("total : $total")

}