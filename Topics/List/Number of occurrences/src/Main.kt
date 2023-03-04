fun solution(strings: List<String>, str: String): Int {
    // put your code here
    var count = 0
    for (s in strings) {
        if (s == str) {
            count += 1
        }
    }

    return count
}