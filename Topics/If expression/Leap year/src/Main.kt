const val ZERO = 0
const val FOUR = 4
const val ONE_HUNDRED = 100
const val FOUR_HUNDRED = 400

fun main() {
    // write your code here
    val year = readln().toInt()

    if (year % FOUR == ZERO && year % ONE_HUNDRED != ZERO || year % FOUR_HUNDRED == ZERO) {
        println("Leap")
    } else {
        println("Regular")
    }
}