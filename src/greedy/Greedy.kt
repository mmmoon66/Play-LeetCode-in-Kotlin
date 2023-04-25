package greedy

// https://leetcode.cn/problems/assign-cookies/
class Solution455 {
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        val m = g.size
        if (m == 0) return 0
        val n = s.size
        if (n == 0) return 0
        g.sortDescending()
        s.sortDescending()
        var res = 0
        var gi = 0
        var si = 0
        while (gi < m && si < n) {
            if (s[si] >= g[gi]) {
                // 当前最大的饼干能够让当前最贪心的小朋友开心，则将这个饼干分给这个最贪心的小朋友
                ++res
                ++si
                ++gi
            } else {
                // 当前最大的饼干不能够让当前最贪心的小朋友开心，则不给这个小朋友饼干，尝试用这个饼干去分给次贪心的小朋友
                ++gi
            }
        }
        return res
    }
}

// https://leetcode.cn/problems/is-subsequence/
class Solution392 {
    fun isSubsequence(s: String, t: String): Boolean {
        return isSubsequence(s.toCharArray(), t.toCharArray(), 0, 0)
    }

    // s[m..]是否为t[n..]的子序列
    private fun isSubsequence(s: CharArray, t: CharArray, m: Int, n: Int): Boolean {
        if (m == s.size) return true
        if (n == t.size) return false
        for (i in n until t.size) {
            if (s[m] == t[i]) {
                return isSubsequence(s, t, m + 1, i + 1)
            }
        }
        return false
    }
}

class Solution392V2 {
    fun isSubsequence(s: String, t: String): Boolean {
        val m = s.length
        val n = t.length
        var i = 0
        var j = 0
        while (i < m && j < n) {
            if (s[i] == t[j]) {
                ++i
                ++j
            } else {
                ++j
            }
        }
        return i == m
    }
}

// https://leetcode.cn/problems/non-overlapping-intervals/
class Solution435 {
    fun eraseOverlapIntervals(arr: Array<IntArray>): Int {
        val comparator = Comparator<IntArray> { a, b ->
            if (a[0] == b[0]) {
                a[1] - b[1]
            } else {
                a[0] - b[0]
            }
        }
        arr.sortWith(comparator)
        // dp[i]表示以arr[i]结尾的最长不重叠区间的个数
        val dp = IntArray(arr.size) { 1 }
        for (i in 1 until dp.size) {
            for (j in 0 until i) {
                if (arr[i][0] >= arr[j][1]) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
        }
        var res = 0
        dp.forEach { res = maxOf(res, it) }
        return arr.size - res
    }
}
// 时间复杂度O(n^2), 超出时间限制

class Solution435V2 {
    fun eraseOverlapIntervals(arr: Array<IntArray>): Int {
        var n = arr.size
        if (n == 0) return 0
        val comparator = Comparator<IntArray> { a, b ->
            if (a[1] == b[1]) {
                a[0] - b[0]
            } else {
                a[1] - b[1]
            }
        }
        arr.sortWith(comparator)
        var res = 1
        var prev = 0
        for (i in 1 until n) {
            if (arr[i][0] >= arr[prev][1]) {
                ++res
                prev = i
            }
        }
        return n - res
    }
}

fun main() {
    val s455 = Solution455()
    println(s455.findContentChildren(intArrayOf(1, 2, 3), intArrayOf(1, 1)) == 1)
    println(s455.findContentChildren(intArrayOf(1, 2), intArrayOf(1, 2, 3)) == 2)

    println()
    val s392 = Solution392V2()
    println(s392.isSubsequence("abc", "ahbgdc"))
    println(s392.isSubsequence("axc", "ahbgdc").not())
}