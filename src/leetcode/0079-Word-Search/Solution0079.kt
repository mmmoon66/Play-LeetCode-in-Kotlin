// https://leetcode.com/problems/word-search/
// 79. Word Search
/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
class Solution0079 {
    private var m = 0
    private var n = 0
    private lateinit var visited: Array<BooleanArray>
    private val direction = arrayOf(
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1)
    )

    private fun inArea(x: Int, y: Int) = x in 0 until m && y in 0 until n

    private fun searchWord(
        board: Array<CharArray>,
        startX: Int,
        startY: Int,
        word: String,
        index: Int
    ): Boolean {
        if (index == word.length - 1) {
            return board[startX][startY] == word[index]
        }
        if (board[startX][startY] == word[index]) {
            visited[startX][startY] = true
            for (d in direction) {
                val newX = startX + d[0]
                val newY = startY + d[1]
                if (inArea(newX, newY) &&
                    !visited[newX][newY] &&
                    searchWord(board, newX, newY, word, index + 1)
                ) {
                    return true
                }
            }
            visited[startX][startY] = false
        }
        return false
    }


    fun exist(board: Array<CharArray>, word: String): Boolean {
        m = board.size
        if (m == 0) return false
        n = board[0].size
        if (n == 0) return false
        visited = Array(m) { BooleanArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (searchWord(board, i, j, word, 0)) {
                    return true
                }
            }
        }
        return false
    }
}

fun main() {
    val s = Solution0079()
    val board = arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E'))
    println(s.exist(board, "ABCCED"))
    println(s.exist(board, "SEE"))
    println(s.exist(board, "ABCB"))
    println(s.exist(arrayOf(charArrayOf('a')), "a"))
}
/*
Runtime: 208 ms, faster than 83.64% of Kotlin online submissions for Word Search.
Memory Usage: 36.3 MB, less than 100.00% of Kotlin online submissions for Word Search.
 */