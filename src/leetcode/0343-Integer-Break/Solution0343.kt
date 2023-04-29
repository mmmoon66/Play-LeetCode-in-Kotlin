class Solution0343 {
    private var memo: IntArray = intArrayOf()

    fun integerBreak(n: Int): Int {
        memo = IntArray(n + 1)
        return breakInteger(n)
    }

    // 将n分成多个正整数(至少2个)得到的最大值
    private fun breakInteger(n: Int): Int {
        if (n == 1) return 0
        if (memo[n] == 0) {
            var res = 0
            for (i in 1 until n) {
                res = maxOf(res, i * (n - i), i * breakInteger(n - i))
            }
            memo[n] = res
        }
        return memo[n]
    }
}

fun main() {
    val s = Solution0343()
    println(s.integerBreak(10) == 36)
    println(s.integerBreak(2) == 1)
}