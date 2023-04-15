class Solution0343_v2 {
    fun integerBreak(n: Int): Int {
        val dp = IntArray(n + 1)//dp[i]表示将i分成多个整数的最大乘积
        for (i in 1..n) {
            for (j in 1 until i) {
                dp[i] = maxOf(dp[i], j * (i - j), j * dp[i - j])
            }
        }
        return dp[n]
    }
}

fun main() {
    val s = Solution0343_v2()
    println(s.integerBreak(10) == 36)
    println(s.integerBreak(2) == 1)
}