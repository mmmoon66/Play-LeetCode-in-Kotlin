// https://leetcode.com/problems/n-queens/
// 51. N-Queens
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
class Solution0051 {
    private lateinit var col: BooleanArray
    private lateinit var dia1: BooleanArray
    private lateinit var dia2: BooleanArray
    private val res = mutableListOf<List<String>>()

    private fun generateBoard(n: Int, row: List<Int>): List<String> =
        row.map { index -> String(CharArray(n) { if (it == index) 'Q' else '.' }) }

    private fun putQueen(n: Int, i: Int, solution: MutableList<Int>) {
        if (i == n) {
            res.add(generateBoard(n, solution))
            return
        }
        for (j in 0 until n) {
            if (!col[j] && !dia1[i + j] && !dia2[i - j + n - 1]) {
                col[j] = true
                dia1[i + j] = true
                dia2[i - j + n - 1] = true
                solution.add(j)
                putQueen(n, i + 1, solution)
                solution.removeAt(solution.size - 1)
                col[j] = false
                dia1[i + j] = false
                dia2[i - j + n - 1] = false
            }
        }
    }

    fun solveNQueens(n: Int): List<List<String>> {
        res.clear()
        if (n == 0) return res
        col = BooleanArray(n)
        dia1 = BooleanArray(2 * n - 1)
        dia2 = BooleanArray(2 * n - 1)
        putQueen(n, 0, mutableListOf())
        return res
    }
}

fun main() {
    val s = Solution0051()
    println(s.solveNQueens(4))
    println(s.solveNQueens(1))
}
/*
Runtime: 196 ms, faster than 84.21% of Kotlin online submissions for N-Queens.
Memory Usage: 38 MB, less than 100.00% of Kotlin online submissions for N-Queens.
 */