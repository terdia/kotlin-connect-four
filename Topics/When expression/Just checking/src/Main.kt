private const val ONE = 1
private const val TWO = 2
private const val THREE = 3
private const val FOUR = 4

//    mat[1][3] = "*"
//    mat[1][4] = "*"
//    mat[1][5] = "*"
//    mat[1][6] = "*"
//    mat[3][2] = "o"

//    mat[0][4] = "*"
//    mat[1][0] = "o"
//    mat[1][4] = "*"
//    mat[2][4] = "*"
//    mat[3][4] = "*"

//    mat[0][1] = "*"
//    mat[1][2] = "*"
//    mat[2][3] = "*"
//    mat[3][4] = "*"
//    mat[3][4] = "*"
fun main() {
    // write your code here
    when (readln().toInt()) {
        ONE, THREE, FOUR -> println("No!")
        TWO -> println("Yes!")
        else -> println("Unknown number")
    }
}