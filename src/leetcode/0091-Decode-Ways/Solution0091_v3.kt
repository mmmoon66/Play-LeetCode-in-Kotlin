// https://leetcode.com/problems/decode-ways/
// 91. Decode Ways
/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
class Solution0091_v3 {
    private var memo = intArrayOf()

    fun numDecodings(s: String): Int {
        memo = IntArray(s.length + 1) { -1 }
        return recursive(s, s.length)
    }

    // s[0..end)范围内元素decode的总数
    private fun recursive(s: String, end: Int): Int {
        if (end < 0) return 0
        if (end == 0) return 1
        if (memo[end] != -1) return memo[end]
        var res = 0
        //分为s[0..end-1)和s[end-1]两部分
        if (s[end - 1] != '0') {
            res += recursive(s, end - 1)
        }
        //分为s[0..end-2)和s[end-2..end-1]两部分
        if (end - 2 >= 0 && s.substring(end - 2, end).toInt() in 10..26) {
            res += recursive(s, end - 2)
        }
        memo[end] = res
        return res
    }
}

fun main() {
    val s = Solution0091_v3()
    println(s.numDecodings("12") == 2)
    println(s.numDecodings("226") == 3)
    println(s.numDecodings("0") == 0)
    println(s.numDecodings("30") == 0)
    println(s.numDecodings("06") == 0)
    println(s.numDecodings("10011") == 0)
}
// Time Limit Exceeded