fun main() {
    val x = readlnOrNull().toBoolean()
    val y = readlnOrNull().toBoolean()
    val z = readlnOrNull().toBoolean()

    // write your code here
    println(!(x && y) || z)
}