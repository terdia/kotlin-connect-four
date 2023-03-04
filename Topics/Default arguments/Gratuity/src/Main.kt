fun main() {

    checkEqual(s2 = "abc", s1 = "aBc")
    checkEqual(s1 = "abc", s2 = "aBc", true)
    checkEqual("abc", "ABC", ignoreCase = true)
}



fun checkEqual(s1: String, s2: String, ignoreCase: Boolean = false): Boolean {
    return false
}

fun tip(bill: Int, percentage: Int = 10): Int {
    return bill * percentage / 100
}