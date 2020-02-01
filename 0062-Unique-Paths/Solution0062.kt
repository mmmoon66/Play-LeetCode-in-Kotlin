// https://leetcode.com/problems/unique-paths/
// 62. Unique Paths
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:
Input: m = 7, n = 3
Output: 28
 */
class Solution0062 {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = IntArray(n) { 1 }
        for (i in 1 until m) {
            for (j in 0 until n) {
                if (j > 0) {
                    dp[j] += dp[j - 1]
                }
            }
        }
        return dp.last()
    }
}
/*
Runtime: 80 ms, faster than 89.83% of Kotlin online submissions for Unique Paths.
Memory Usage: 37.2 MB, less than 100.00% of Kotlin online submissions for Unique Paths.
 */