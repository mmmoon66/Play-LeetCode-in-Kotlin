class Solution0120 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if (triangle.isEmpty()) return 0
        if (triangle.size == 1) return triangle.first().first()
        val dp = IntArray(triangle.size)
        dp[0] = triangle.first().first()
        for (i in 1 until triangle.size) {
            for (j in triangle[i].lastIndex downTo 0) {
                when (j) {
                    0 -> {
                        dp[j] = triangle[i][j] + dp[j]
                    }
                    triangle[i].lastIndex -> {
                        dp[j] = triangle[i][j] + dp[j - 1]
                    }
                    else -> {
                        dp[j] = triangle[i][j] + minOf(dp[j - 1], dp[j])
                    }
                }
            }
        }
        var ret = dp[0]
        for (i in 1 until dp.size) {
            ret = minOf(ret, dp[i])
        }
        return ret
    }
}

fun main() {
    val s = Solution0120()
    val triangle = listOf(
        listOf(2),
        listOf(3, 4),
        listOf(6, 5, 7),
        listOf(4, 1, 8, 3)
    )
    println(s.minimumTotal(triangle) == 11)
}