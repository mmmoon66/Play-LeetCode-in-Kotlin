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
        var res = ""
        for (i in 0 until s.length) {
            var p = findPalindrome(s, i, i)
            if (p.length > res.length) {
                res = p
            }
            p = findPalindrome(s, i, i + 1)
            if (p.length > res.length) {
                res = p
            }
        }
        return res
    }

    private fun findPalindrome(s: String, l: Int, r: Int): String {
        if (r >= s.length) {
            return ""
        }
        var i = l
        var j = r
        while(i >= 0 && j < s.length && s[i] == s[j]) {
            --i
            ++j
        }
        return s.substring(i + 1, j)
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