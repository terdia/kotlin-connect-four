import com.sun.jdi.Value

const val MAX = 10
const val MIN = 0

fun main() {
    // put your code here
    val value = readln().toInt()
    println(value > MIN && value < MAX)
}