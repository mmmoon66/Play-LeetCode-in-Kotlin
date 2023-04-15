class Solution0279 {
    private var memo = intArrayOf()

    fun numSquares(n: Int): Int {
        memo = IntArray(n + 1)
        return recursive(n)
    }


    // 将n分成多个平方和，返回平方和的最少个数
    private fun recursive(n: Int): Int {
        if (n == 1) return 1
        if (memo[n] != 0) return memo[n]
        var i = 1
        var res = n
        while (i * i <= n) {
            res = minOf(res, 1 + recursive(n - i * i))
            ++i
        }
        memo[n] = res
        return res
    }
}

fun main() {
    val s = Solution0279()
    println(s.numSquares(12) == 3)
    println(s.numSquares(13) == 2)
}