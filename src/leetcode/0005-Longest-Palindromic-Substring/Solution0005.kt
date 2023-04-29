// https://leetcode.com/problems/longest-palindromic-substring/
// 5. Longest Palindromic Substring
/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: "cbbd"
Output: "bb"
 */
class Solution0005 {
    fun longestPalindrome(s: String): String {
        var ret = ""
        for (i in s.indices) {
            var p = findPalindrome(s, i, i)
            if (p.length > ret.length) {
                ret = p
            }
            if (i + 1 < s.length) {
                p = findPalindrome(s, i, i + 1)
                if (p.length > ret.length) {
                    ret = p
                }
            }
        }
        return ret
    }

    private fun findPalindrome(s: String, left: Int, right: Int): String {
        var l = left
        var r = right
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            --l
            ++r
        }
        return s.substring(l + 1, r)
    }
}

fun main() {
    val s = Solution0005()
    println(s.longestPalindrome("babad"))
    println(s.longestPalindrome("cbbd"))
}
/*
Runtime: 188 ms, faster than 79.73% of Kotlin online submissions for Longest Palindromic Substring.
Memory Usage: 36 MB, less than 100.00% of Kotlin online submissions for Longest Palindromic Substring.
 */