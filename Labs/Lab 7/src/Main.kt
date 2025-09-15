fun main() {
    val queenBoard = Queens(8)
    val KingsBoard = Kings(8)
    println(queenBoard)
    println(KingsBoard)
}

// QUESTION 3
abstract class AllSameChessPiece(val n: Int) {

    // QUESTION 1
    // all elements are false by default
    val board = Array(n) { BooleanArray(n) }
    open val representation = " R "

    /**
     * Takes Int [row] and Int [col] as input and places a queen at that position
     * @throws IllegalArgumentException if the position is already occupied
     */
    fun place(row: Int, col: Int) {
        if (board[row][col] == true) {
            throw IllegalArgumentException("Position already occupied")
        } else {
            board[row][col] = true
        }
    }

    // QUESTION 2
    override fun toString(): String {
        var boardSB = StringBuilder()
        for (row in board) {
            var lineSB = StringBuilder()
            for (square in row) {
                if (square) lineSB.append(" $representation ") else lineSB.append(" - ")
            }
            boardSB.append("$lineSB\n")
        }
        return boardSB.toString()
    }

    // QUESTION 4
    abstract fun isSafe(row: Int, col: Int): Boolean
}

// QUESTION 3
class Queens(n: Int) : AllSameChessPiece(n) {
    override val representation = " Q "

    override fun isSafe(row: Int, col: Int): Boolean {
        // check row and column
        for (i in 0 until 8) {
            if (board[i][col] || board[row][i]) {
                return false
            }
        }

        // check front diag and check back diag
        for (x in 0 until n) {
            for (y in 0 until n) {
                // any space on the diagnal will have the same row - col or row + col, so that is
                // all we need to check for
                // this is way easier than checking all the spaces on the diagnal manually
                if ((x - y == row - col || x + y == row + col) && board[x][y] == true) {
                    return false
                }
            }
        }
        return true
    }
}

class Kings(n: Int) : AllSameChessPiece(n) {
    override val representation = " K "

    /**
     * Checks all 8 posistions arround the the given positon and returns false if any of them is
     * occupied
     */
    override fun isSafe(row: Int, col: Int): Boolean {
        // downleft, down, downright, left, right, upleft, up, upright
        val rowOffsets = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val colOffsets = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        for (i in 0 until 8) {
            if (row + rowOffsets[i] < 0 || row + rowOffsets[i] >= n) {
                if (board[row + rowOffsets[i]][col + colOffsets[i]] == true) return false
            }
            return true
        }
        return false
    }
}
