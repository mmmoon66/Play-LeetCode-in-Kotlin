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
class Solution0010_v2 {
    fun isMatch(s: String, p: String): Boolean {
        return match(s, 0, p, 0)
    }

    private fun match(s: String, sl: Int, p: String, pl: Int): Boolean {
        if (sl == s.length) return noMoreMatch(p, pl)
        if (pl == p.length) return false
        if (pl + 1 < p.length && p[pl + 1] == '*') {
            if (s[sl] == p[pl] || p[pl] == '.') {
                return match(s, sl + 1, p, pl) || match(s, sl, p, pl + 2)
            } else {
                return match(s, sl, p, pl + 2)
            }
        } else if (s[sl] == p[pl] || p[pl] == '.') {
            return match(s, sl + 1, p, pl + 1)
        }
        return false
    }

    private fun noMoreMatch(p: String, pl: Int): Boolean {
        var i = pl
        while (i + 1 < p.length) {
            if (p[i + 1] != '*') return false
            i += 2
        }
        return i == p.length
    }
}

fun main() {
    val s = Solution0010_v2()
    println(s.isMatch("aa", "a"))
    println(s.isMatch("aa", "a*"))
    println(s.isMatch("ab", ".*"))
    println(s.isMatch("aaa", "c*a*b"))
    println(s.isMatch("mississippi", "mis*is*p*."))
}
/*
执行用时：228 ms, 在所有 Kotlin 提交中击败了23.81%的用户
内存消耗：34.5 MB, 在所有 Kotlin 提交中击败了90.48%的用户
 */