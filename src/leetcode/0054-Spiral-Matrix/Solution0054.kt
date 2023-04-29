// https://leetcode.com/problems/spiral-matrix/
// 54. Spiral Matrix
/*

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
class Solution0054 {
    private var m = 0
    private var n = 0

    private fun inArea(x: Int, y: Int): Boolean {
        return x in 0 until m && y in 0 until n
    }

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val res = mutableListOf<Int>()
        m = matrix.size
        if (m == 0) return res
        n = matrix.first().size
        if (n == 0) return res
        val directions = arrayOf(
                intArrayOf(0, 1),//right
                intArrayOf(1, 0),//down
                intArrayOf(0, -1),//left
                intArrayOf(-1, 0)//up
        )

        val visited = Array(m) { BooleanArray(n) }
        var x = 0
        var y = 0
        var d = 0
        while (inArea(x, y) && visited[x][y].not()) {
            res.add(matrix[x][y])
            visited[x][y] = true
            var newX = x + directions[d][0]
            var newY = y + directions[d][1]
            if (inArea(newX, newY).not() || visited[newX][newY]) {
                d = (d + 1) % 4
                newX = x + directions[d][0]
                newY = y + directions[d][1]
            }
            x = newX
            y = newY
        }
        return res
    }
}

fun main() {
    val s = Solution0054()
    val matrix = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(12, 13, 14, 5),
            intArrayOf(11, 16, 15, 6),
            intArrayOf(10, 9, 8, 7)
    )
    val res = s.spiralOrder(matrix)
    println(res)
}
/*
Runtime: 168 ms, faster than 86.43% of Kotlin online submissions for Spiral Matrix.
Memory Usage: 34.7 MB, less than 47.86% of Kotlin online submissions for Spiral Matrix.
 */