class Solution0120_v3 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if (triangle.isEmpty()) return 0
        val n = triangle.size
        val dp = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            for (j in 0 until triangle[i].size) {
                dp[j] = minOf(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }
        return dp[0]
    }
}

fun main() {
    val s = Solution0120_v3()
    val triangle = listOf(
        listOf(2),
        listOf(3, 4),
        listOf(6, 5, 7),
        listOf(4, 1, 8, 3)
    )
    println(s.minimumTotal(triangle) == 11)
}