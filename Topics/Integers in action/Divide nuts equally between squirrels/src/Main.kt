const val MAX = 10_000

fun main() {
    // put your code here
    val n = readln().toInt()
    val k = readln().toInt()

    if (k in 1..MAX && n in 1..MAX) {
        println(k / n)
    }

}