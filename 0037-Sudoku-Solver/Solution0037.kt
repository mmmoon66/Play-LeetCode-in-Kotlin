import java.util.*

// https://leetcode.com/problems/sudoku-solver/
// 37. Sudoku Solver
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:
Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

Note:
The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
 */
class Solution0037 {
    fun solveSudoku(board: Array<CharArray>) {
        val row = Array(9) { BooleanArray(10) }
        val col = Array(9) { BooleanArray(10) }
        val block = Array(9) { BooleanArray(10) }
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                val c = board[i][j]
                if (c != '.') {
                    row[i][c - '0'] = true
                    col[j][c - '0'] = true
                    block[i / 3 * 3 + j / 3][c - '0'] = true
                }
            }
        }
        for (i in 0 until 81) {
            if (board[i / 9][i % 9] == '.') {
                dfs(board, i, row, col, block)
                return // TODO why return here?
            }
        }
    }

    private fun dfs(
        board: Array<CharArray>,
        pos: Int,
        row: Array<BooleanArray>,
        col: Array<BooleanArray>,
        block: Array<BooleanArray>
    ): Boolean {

        // TODO why?
        if (pos == 81) return true

        var next = pos + 1
        while (next < 81 && board[next / 9][next % 9] != '.') ++next

        val x = pos / 9
        val y = pos % 9
        for (i in 1..9) {
            if (!row[x][i] && !col[y][i] && !block[x / 3 * 3 + y / 3][i]) {
                board[x][y] = ('0'.toInt() + i).toChar()
                row[x][i] = true
                col[y][i] = true
                block[x / 3 * 3 + y / 3][i] = true

                if (dfs(board, next, row, col, block)) return true

                board[x][y] = '.'
                row[x][i] = false
                col[y][i] = false
                block[x / 3 * 3 + y / 3][i] = false
            }
        }
        return false
    }
}

fun main() {
    val s = Solution0037()
    val board = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )
    s.solveSudoku(board)
    board.forEach { println(Arrays.toString(it)) }
}
/*
Runtime: 148 ms, faster than 100.00% of Kotlin online submissions for Sudoku Solver.
Memory Usage: 32.9 MB, less than 100.00% of Kotlin online submissions for Sudoku Solver.
 */