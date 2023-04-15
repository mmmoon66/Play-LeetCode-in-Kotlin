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
class Solution0091_v4 {
    fun numDecodings(s: String): Int {
        val n = s.length
        // dp[i]表示s[0..i)范围内元素的decode结果的个数
        val dp = IntArray(n + 1)
        dp[0] = 1
        for (i in 1..n) {
            // 分成s[0..i-1)和s[i-1]两部分
            if (s[i - 1] != '0') {
                dp[i] += dp[i - 1]
            }
            //分成s[0..i-2)和s[i-2..i-1]两部分
            if (i - 2 >= 0 && s.substring(i - 2, i).toInt() in 10..26) {
                dp[i] += dp[i - 2]
            }
        }
        return dp[n]
    }
}

fun main() {
    val s = Solution0091_v4()
    println(s.numDecodings("12") == 2)
    println(s.numDecodings("226") == 3)
    println(s.numDecodings("0") == 0)
    println(s.numDecodings("30") == 0)
    println(s.numDecodings("06") == 0)
    println(s.numDecodings("10011") == 0)
}
// Time Limit Exceeded