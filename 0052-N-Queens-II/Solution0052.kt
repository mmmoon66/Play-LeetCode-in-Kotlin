// https://leetcode.com/problems/n-queens-ii/
// 52. N-Queens II
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */
class Solution0052 {
    private lateinit var col: BooleanArray
    private lateinit var dia1: BooleanArray
    private lateinit var dia2: BooleanArray
    private var res = 0

    private fun putQueue(n: Int, i: Int) {
        if (i == n) {
            ++res
            return
        }
        for (j in 0 until n) {
            if (!col[j] && !dia1[i + j] && !dia2[i - j + n - 1]) {
                col[j] = true
                dia1[i + j] = true
                dia2[i - j + n - 1] = true
                putQueue(n, i + 1)
                col[j] = false
                dia1[i + j] = false
                dia2[i - j + n - 1] = false
            }
        }
    }

    fun totalNQueens(n: Int): Int {
        res = 0
        if (n == 0) return res
        col = BooleanArray(n)
        dia1 = BooleanArray(2 * n - 1)
        dia2 = BooleanArray(2 * n - 1)
        putQueue(n, 0)
        return res
    }
}

fun main() {
    val s = Solution0052()
    println(s.totalNQueens(1))
    println(s.totalNQueens(2))
    println(s.totalNQueens(3))
    println(s.totalNQueens(4))
    println(s.totalNQueens(5))
}
/*
Runtime: 152 ms, faster than 64.29% of Kotlin online submissions for N-Queens II.
Memory Usage: 31.2 MB, less than 100.00% of Kotlin online submissions for N-Queens II.
 */