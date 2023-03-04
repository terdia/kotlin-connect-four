private const val ONE = 1
private const val TWO = 2
private const val THREE = 3
private const val FOUR = 4

fun main(args: Array<String>) {
    // write your code here
    when (readln().toInt()) {
        ONE -> println("You have chosen a square")
        TWO -> println("You have chosen a circle")
        THREE -> println("You have chosen a triangle")
        FOUR -> println("You have chosen a rhombus")
        else -> println("There is no such shape!")
    }
}