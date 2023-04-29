// https://leetcode-cn.com/problems/regular-expression-matching/
// 10. Regular Expression Matching
/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).
 

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:
Input: s = "mississippi", p = "mis*is*p*."
Output: false

Constraints:
0 <= s.length <= 20
0 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/regular-expression-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0010 {
    fun isMatch(s: String, p: String): Boolean {
        val m = s.length
        val n = p.length
        // dp[i][j]表示s的前i个字符与p的前j个字符是否匹配
        val dp = Array(m + 1) { BooleanArray(n + 1) }
        dp[0][0] = true
        for (i in 0..m) {
            for (j in 1..n) {
                if (p[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2]
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1]
                    }
                }
            }
        }
        return dp[m][n]
    }

    private fun matches(s: String, p: String, i: Int, j: Int): Boolean {
        if (i == 0) return false
        return p[j - 1] == '.' || s[i - 1] == p[j - 1]
    }
}

fun main() {
    val s = Solution0010()
    println(s.isMatch("aa", "a"))
    println(s.isMatch("aa", "a*"))
    println(s.isMatch("ab", ".*"))
    println(s.isMatch("aaa", "c*a*b"))
    println(s.isMatch("mississippi", "mis*is*p*."))
}