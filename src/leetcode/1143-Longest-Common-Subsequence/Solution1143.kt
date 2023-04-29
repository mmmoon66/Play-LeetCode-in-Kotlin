class Solution1143 {
    private var memo: Array<IntArray> = emptyArray()
    fun longestCommonSubsequence(a: String, b: String): Int {
        val m = a.length
        if (m == 0) return 0
        val n = b.length
        if (n == 0) return 0
        memo = Array(m) { IntArray(n) { -1 } }
        return lcs(a, b, a.length - 1, b.length - 1)
    }

    // a[0..m]和b[0..n]的最长公共子序列的长度
    private fun lcs(a: String, b: String, m: Int, n: Int): Int {
        if (m < 0 || n < 0) return 0
        if (memo[m][n] != -1) return memo[m][n]
        return if (a[m] == b[n]) {
            1 + lcs(a, b, m - 1, n - 1)
        } else {
            maxOf(lcs(a, b, m - 1, n), lcs(a, b, m, n - 1))
        }.also { memo[m][n] = it }
    }
}

class Solution1143V2 {
    fun longestCommonSubsequence(a: String, b: String): Int {
        val m = a.length
        if (m == 0) return 0
        val n = b.length
        if (n == 0) return 0
        // memo[i][j]表示a[0..i)和b[0..j)的最长公共子序列的长度
        val memo = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            for (j in 1..n) {
                memo[i][j] = if (a[i - 1] == b[j - 1]) {
                    1 + memo[i - 1][j - 1]
                } else {
                    maxOf(memo[i - 1][j], memo[i][j - 1])
                }
            }
        }
        return memo[m][n]
    }
}

fun main() {
    val s = Solution1143V2()
    println(s.longestCommonSubsequence("abcde", "ace") == 3)
    println(s.longestCommonSubsequence("abc", "abc") == 3)
    println(s.longestCommonSubsequence("abc", "def") == 0)
}