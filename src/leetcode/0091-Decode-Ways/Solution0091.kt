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
class Solution0091 {
    fun numDecodings(s: String): Int {
        val res = mutableListOf<String>()
        generateWay(s, 0, mutableListOf(), res)
        return res.size
    }

    private fun generateWay(s: String, curIndex: Int, path: MutableList<Char>, res: MutableList<String>) {
        if (curIndex == s.length) {
            res.add(path.joinToString(""))
            return
        }

        if (s[curIndex] == '0') {
            return
        }

        path.add('A' + (s[curIndex] - '1'))
        generateWay(s, curIndex + 1, path, res)
        path.removeAt(path.size - 1)

        if (curIndex + 1 < s.length) {
            val num = s.substring(curIndex, curIndex + 2).toInt()
            if (num in 1..26) {
                path.add('A' + (num - 1));
                generateWay(s, curIndex + 2, path, res)
                path.removeAt(path.size - 1)
            }
        }
    }
}

fun main() {
    val s = Solution0091()
    println(s.numDecodings("12"))
    println(s.numDecodings("226"))
    println(s.numDecodings("0"))
    println(s.numDecodings("30"))
}
// Time Limit Exceeded