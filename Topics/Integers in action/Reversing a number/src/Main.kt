fun main() {
    // put your code here
    var input = readln().toInt()
    var output = 0

    while(input != 0) {
        val d = input % 10
        output = output * 10 + d
        input /= 10
    }

    println(output)

}