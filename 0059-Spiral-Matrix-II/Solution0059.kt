// https://leetcode.com/problems/spiral-matrix-ii/
// 59. Spiral Matrix II
/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:
Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
class Solution0059 {
    fun generateMatrix(n: Int): Array<IntArray> {
        val matrix = Array(n) { IntArray(n) }
        val directions = arrayOf(
                intArrayOf(0, 1),//right
                intArrayOf(1, 0),//down
                intArrayOf(0, -1),//left
                intArrayOf(-1, 0)//up
        )
        fun inArea(x: Int, y: Int) = x in 0 until n && y in 0 until n

        var x = 0
        var y = 0
        var d = 0
        var num = 1
        while(inArea(x, y) && matrix[x][y] == 0) {
            matrix[x][y] = num
            ++num

            var newX = x + directions[d][0]
            var newY = y + directions[d][1]
            if (inArea(newX, newY).not() || matrix[newX][newY] != 0) {
                d = (d + 1) % 4
                newX = x + directions[d][0]
                newY = y + directions[d][1]
            }
            x = newX
            y = newY
        }
        return matrix
    }
}
/*
Runtime: 152 ms, faster than 64.71% of Kotlin online submissions for Spiral Matrix II.
Memory Usage: 32.8 MB, less than 47.06% of Kotlin online submissions for Spiral Matrix II.
 */