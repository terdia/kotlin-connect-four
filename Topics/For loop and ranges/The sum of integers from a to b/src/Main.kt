fun main() {
    // put your code here
    val a = readln().toInt()
    val b = readln().toInt()

    var sum = 0
    for (num in a..b) {
        sum += num
    }

    println(sum)
}