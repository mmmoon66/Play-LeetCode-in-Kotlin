// https://leetcode.com/problems/minimum-path-sum/
// 64. Minimum Path Sum
/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
class Solution0064_v2 {
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        if (m == 0) return 0
        val n = grid[0].size
        if (n == 0) return 0
        val res = grid.clone()
        for (j in 1 until n) {
            res[0][j] += res[0][j - 1]
        }
        for (i in 1 until m) {
            res[i][0] += res[i - 1][0]
        }
        for (i in 1 until m) {
            for (j in 1 until n) {
                res[i][j] += minOf(res[i - 1][j], res[i][j - 1])
            }
        }
        return res[m - 1][n - 1]
    }
}

fun main() {
    val s = Solution0064_v2()
    println(s.minPathSum(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))))
}
/*
Runtime: 148 ms, faster than 96.23% of Kotlin online submissions for Minimum Path Sum.
Memory Usage: 50.2 MB, less than 100.00% of Kotlin online submissions for Minimum Path Sum.
 */