// Implement your functions here
fun subtractTwoNumbers (a: Long, b: Long) {
    println(a - b)
}


fun sumTwoNumbers (a: Long, b: Long) {
    println(a + b)
}


fun divideTwoNumbers (a: Long, b: Long) {
    when {
       a.toInt() == 0 || b.toInt() == 0 -> println("Division by 0!")
       else -> println(a / b)
    }
}


fun multiplyTwoNumbers (a: Long, b: Long) {
    println(a * b)
}