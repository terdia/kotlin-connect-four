fun main() {
    // put your code here
    val num = readln()

    val numOfTen = num.toFloat() / 10 % 10
    println(numOfTen.toInt())
}