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
class Solution0091_v2 {

    private var map = mutableMapOf<String, Char>().apply {
        for (i in 1..26) {
            put(i.toString(), 'A' + i - 1)
        }
    }

    private lateinit var memo: IntArray

    fun numDecodings(s: String): Int {
        memo = IntArray(s.length) { -1 }
        return generateDecoding(s, 0)
    }

    private fun generateDecoding(s: String, curIndex: Int): Int {
        if (curIndex == s.length) {
            return 1
        }
        if (memo[curIndex] != -1) {
            return memo[curIndex]
        }
        if (s[curIndex] == '0') {
            memo[curIndex] = 0
            return 0
        }
        var res = generateDecoding(s, curIndex + 1)
        if (curIndex + 1 < s.length && map[s.substring(curIndex, curIndex + 2)] != null) {
            res += generateDecoding(s, curIndex + 2)
        }
        memo[curIndex] = res
        return res
    }
}

fun main() {
    val s = Solution0091_v2()
    println(s.numDecodings("12"))
    println(s.numDecodings("226"))
    println(s.numDecodings("0"))
    println(s.numDecodings("30"))
}
/*
Runtime: 168 ms, faster than 48.15% of Kotlin online submissions for Decode Ways.
Memory Usage: 33.4 MB, less than 100.00% of Kotlin online submissions for Decode Ways.
 */