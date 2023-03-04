
private const val ZERO = 0
private const val ONE = 1
private const val TWO = 2
private const val THREE = 3
private const val FOUR = 4

fun main() {
    // write your code here
    when (readln().toInt()) {
        ZERO -> println("do not move")
        ONE -> println("move up")
        TWO -> println("move down")
        THREE -> println("move left")
        FOUR -> println("move right")
        else -> println("error!")
    }
}