package connectfour

private const val COLUMN_FULL_INDEX = -1
private const val COLUMN_DEFAULT = "nil"
private const val LOWER_BOUND = 5
private const val UPPER_BOUND = 9
private const val NUMBER_OF_DISC_TO_WIN = 3

private const val DEFAULT_DIMENSIONS = "6 x 7"
private const val FIRST_PLAYER_NAME = "First player's name:"
private const val BOARD_PROMPT_TEXT = "Set the board dimensions (Rows x Columns)"
private const val SECOND_PLAYER_NAME = "Second player's name:"
private const val BOARD_PROMPT_DEFAULT_VALUE_HITS = "Press Enter for default (6 x 7)"

data class Dimension (val numRows: Int, val numCols: Int) {
    fun isValid(): Boolean {
        return isRowValid() && isColumnValid()
    }

    fun isRowValid(): Boolean {
        return numRows in LOWER_BOUND .. UPPER_BOUND
    }

    private fun isColumnValid(): Boolean {
        return numCols in LOWER_BOUND .. UPPER_BOUND
    }
}

data class Player (val name: String, val color: String) {
    var score = 0
}


fun main() {
    val players = mutableListOf<Player>()
    println("Connect Four")
    println(FIRST_PLAYER_NAME)
    players.add(Player(readln(), "o"))

    println(SECOND_PLAYER_NAME)
    players.add(Player(readln(), "*"))

    val dimension = getDimension()

    var numberOfGames: Int?
    while (true){
        numberOfGames = numOfGamesPrompt()
        if (numberOfGames != null && numberOfGames > 0) {
            break
        }
        println("Invalid input")
    }

    println("${players.first().name} VS ${players.last().name}")
    println("${dimension.numRows} X ${dimension.numCols} board")
    when(numberOfGames) {
        1 -> println("Single game")
        else -> {
            println("Total $numberOfGames games")
            println("Game #1")
        }
    }

    var matrix = getBoard(dimension)
    drawBoard(dimension, matrix)

    var gamePlayed = 0
    var currentPlayer = 0
    while (true) {
       for (i in players.indices) {
           var selectedColumnIndex: String
           val player = players[currentPlayer]

           while (true) {
               println("${player.name}'s turn:")
               selectedColumnIndex = readln()
               if (selectedColumnIndex.lowercase() == "end") {
                   println("Game over!")
                   return
               }

               if (isSelectedColumnValid(selectedColumnIndex, dimension.numCols, matrix)) {
                   break
               }
           }

           val columnIndex = selectedColumnIndex.toInt() - 1
           val rowIndex = getNextRowIndex(matrix, columnIndex)
           matrix[rowIndex][columnIndex] = player.color
           drawBoard(dimension, matrix)

           val hasWon = won(player.color, matrix)
           val boardIsFull = isBoardFull(dimension, matrix)

           if (boardIsFull && !hasWon) {
               gamePlayed++
               println("It is a draw")
               when(numberOfGames) {
                   1 -> {
                       println("Game over!")
                       return
                   }
                   else -> {
                       players.first().score += 1
                       players.last().score += 1
                       printScore(players)

                       if (gamePlayed == numberOfGames) {
                           println("Game over!")
                           return
                       }

                       currentPlayer = next(currentPlayer)
                       matrix = getBoard(dimension)
                       println("Game #${gamePlayed + 1}")
                       drawBoard(dimension, matrix)

                       break
                   }
               }
           }

           if(hasWon) {
               gamePlayed++
               println("Player ${player.name} won")
               player.score += 2

               when(numberOfGames) {
                   1 -> {
                       println("Game over!")
                       return
                   }
                   else -> {

                       printScore(players)

                       if (gamePlayed == numberOfGames) {
                           println("Game over!")
                           return
                       }

                       // reset board and player
                       matrix = getBoard(dimension)
                       println("Game #${gamePlayed + 1}")
                       currentPlayer = next(currentPlayer)
                       drawBoard(dimension, matrix)

                       break
                   }
               }
           }

           currentPlayer = next(currentPlayer)
       }
    }
}

private fun next(currentPlayer: Int) = if (currentPlayer == 0) 1 else 0

private fun printScore(players: MutableList<Player>) {
    println("Score")
    val current = "${players.first().name}: ${players.first().score} "
    println(current + "${players.last().name}: ${players.last().score}")
}

private fun getBoard(dimension: Dimension) =
    MutableList(dimension.numRows) { MutableList(dimension.numCols) { COLUMN_DEFAULT } }

private fun getDimension(): Dimension {
    var grid = boardDimensionPrompt()
    val pattern = """(\d+)\s*x\s*(\d+)""".toRegex(RegexOption.IGNORE_CASE)

    while (true){
        if (pattern.matches(grid)) {
            break
        }
        println("Invalid input")
        grid = boardDimensionPrompt()
    }

    var dimension = Dimension(6, 7)
    var matchResult = pattern.find(grid)

    while (matchResult != null) {
        val numRows = matchResult.groupValues[1].toInt()
        val numCols = matchResult.groupValues[2].toInt()

        dimension = Dimension(numRows, numCols)
        if (dimension.isValid()) {
            break
        }

        val line = if (!dimension.isRowValid()) {
            "Board rows should be from 5 to 9"
        } else {
            "Board columns should be from 5 to 9"
        }

        println(line)
        grid = boardDimensionPrompt()
        matchResult = pattern.find(grid)
    }

    return dimension
}

private fun drawBoard(dimension: Dimension, matrix: MutableList<MutableList<String>>) {
    repeat(dimension.numCols) {
        print(" ${it + 1}")
    }
    println()

    for (i in 1..dimension.numRows) {
        // print the row content
        for (j in 1..dimension.numCols) {
            if (matrix[i-1][j-1] != COLUMN_DEFAULT) {
                print("║${matrix[i-1][j-1]}")
            } else {
                print("║ ")
            }
        }
        println("║")
    }

    print("╚")
    repeat(dimension.numCols - 1) {
        print("═╩")
    }
    print("═╝")
    println()
}

private fun boardDimensionPrompt(): String {
    println(BOARD_PROMPT_TEXT)
    println(BOARD_PROMPT_DEFAULT_VALUE_HITS)

    val input = readlnOrNull()?.trim()

    val userInput = if (input.isNullOrEmpty()) {
        DEFAULT_DIMENSIONS
    } else {
        input
    }

    return userInput
}

private fun numOfGamesPrompt(): Int? {
    println("Do you want to play single or multiple games?")
    println("For a single game, input 1 or press Enter")
    println("Input a number of games:")

    val input = readlnOrNull()?.trim()

    val numOfGames = if (input.isNullOrEmpty()) {
        1
    } else {
        input.toIntOrNull()
    }

    return numOfGames
}

private fun getNextRowIndex(matrix: MutableList<MutableList<String>>, colIndex: Int): Int {
    for (i in matrix.lastIndex downTo 0) {
        if (matrix[i][colIndex] == COLUMN_DEFAULT) {
            return i
        }
    }
    return COLUMN_FULL_INDEX
}

private fun isSelectedColumnValid(selectedColumnIndex: String, numCols: Int, matrix: MutableList<MutableList<String>>): Boolean {
    return if (selectedColumnIndex.toIntOrNull() == null) {
        println("Incorrect column number")
        false
    } else {
        if (selectedColumnIndex.toInt() !in 1..numCols) {
            println("The column number is out of range (1 - $numCols)")
            false
        } else if (getNextRowIndex(matrix, selectedColumnIndex.toInt() - 1) == COLUMN_FULL_INDEX) {
            println("Column ${selectedColumnIndex.toInt()} is full")
            false
        }
        else {
            return true
        }
    }
}

private fun isBoardFull(dimension: Dimension, matrix: MutableList<MutableList<String>>):Boolean {
    for (i in 1..dimension.numRows) {
        for (j in 1..dimension.numCols) {
            if (matrix[i-1][j-1] == COLUMN_DEFAULT) {
                return false
            }
        }
    }

    return true
}

private fun won(color: String, board: MutableList<MutableList<String>>): Boolean {
    // Check rows
    if (hasHorizontalLine(board, color)) {
        return true
    }

    // Check columns
    if (hasVerticalLine(board, color)) {
        return true
    }

    // Check diagonal (top left to bottom right)
    return hasDiagonalLine(board, color)
}

fun hasHorizontalLine(board: MutableList<MutableList<String>>, color: String): Boolean {
    val nRows = board.size
    val nCols = board[0].size

    for (i in 0 until nRows) {
        for (j in 0 until nCols - NUMBER_OF_DISC_TO_WIN) {
            if (board[i][j] == color &&
                board[i][j+1] == color &&
                board[i][j+2] == color &&
                board[i][j+3] == color) {
                return true
            }
            if (board[i][j+3] == color &&
                board[i][j+2] == color &&
                board[i][j+1] == color &&
                board[i][j] == color) {
                return true
            }
        }
    }

    return false
}

fun hasVerticalLine(board: MutableList<MutableList<String>>, color: String): Boolean {
    val n = board.size

    for (i in 0 until n - NUMBER_OF_DISC_TO_WIN) {
        for (j in 0 until n) {
            if (board[i][j] == color &&
                board[i+1][j] == color &&
                board[i+2][j] == color &&
                board[i+3][j] == color) {
                return true
            }
        }
    }

    return false
}


fun hasDiagonalLine(board: MutableList<MutableList<String>>, color: String): Boolean {
    val n = board.size

    // Check for top-left to bottom-right diagonal lines
    for (i in 0 until n - NUMBER_OF_DISC_TO_WIN) {
        for (j in 0 until n - NUMBER_OF_DISC_TO_WIN) {
            if (board[i][j] == color &&
                board[i+1][j+1] == color &&
                board[i+2][j+2] == color &&
                board[i+3][j+3] == color) {
                return true
            }
        }
    }

    // Check for bottom-left to top-right diagonal lines
    for (i in 3 until n) {
        for (j in 0 until n - NUMBER_OF_DISC_TO_WIN) {
            if (board[i][j] == color &&
                board[i-1][j+1] == color &&
                board[i-2][j+2] == color &&
                board[i-3][j+3] == color) {
                return true
            }
        }
    }

    return false
}