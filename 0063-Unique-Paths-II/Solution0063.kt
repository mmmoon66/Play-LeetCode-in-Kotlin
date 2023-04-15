class Solution0063 {
    fun uniquePathsWithObstacles(grid: Array<IntArray>): Int {
        val m = grid.size
        if (m == 0) return 0
        val n = grid[0].size
        if (n == 0) return 0
        val dp = IntArray(n)
        //第一行特殊处理
        for (j in 0 until n) {
            if (grid[0][j] == 1) break
            dp[j] = 1
        }
        for (i in 1 until m) {
            // 第一列特殊处理
            if (grid[i][0] == 1 || grid[i - 1][0] == 1) {
                dp[0] = 0
            }
            for (j in 1 until n) {
                if (grid[i][j] == 1) {
                    dp[j] = 0
                } else {
                    dp[j] += dp[j - 1]
                }
            }
        }
        return dp[n - 1]
    }
}

fun main() {
    val s = Solution0063()
    println(s.uniquePathsWithObstacles(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))) == 2)
    println(s.uniquePathsWithObstacles(arrayOf(intArrayOf(1, 0))) == 0)
    println(s.uniquePathsWithObstacles(arrayOf(intArrayOf(1), intArrayOf(0))) == 0)
    println(s.uniquePathsWithObstacles(arrayOf(intArrayOf(0), intArrayOf(0))) == 1)
}