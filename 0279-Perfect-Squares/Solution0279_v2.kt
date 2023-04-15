class Solution0279_v2 {
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1)
        for (i in 0..n) {
            dp[i] = i
            var j = 1
            while (j * j <= i) {
                dp[i] = minOf(dp[i], 1 + dp[i - j * j])
                ++j
            }
        }
        return dp[n]
    }
}

fun main() {
    val s = Solution0279_v2()
    println(s.numSquares(12) == 3)
    println(s.numSquares(13) == 2)
}