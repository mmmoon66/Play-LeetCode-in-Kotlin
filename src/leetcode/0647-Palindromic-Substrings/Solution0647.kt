// https://leetcode.com/problems/palindromic-substrings/
// 647. Palindromic Substrings
/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Note:
The input string length won't exceed 1000.
 */
class Solution0647 {
    private fun countPalindrome(s: String, L: Int, R: Int): Int {
        var res = 0
        var l = L
        var r = R
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            ++res
            --l
            ++r
        }
        return res
    }

    fun countSubstrings(s: String): Int {
        var res = 0
        s.indices.forEach { index ->
            res += countPalindrome(s, index, index) + countPalindrome(s, index, index + 1)
        }
        return res
    }
}
/*
Runtime: 132 ms, faster than 89.47% of Kotlin online submissions for Palindromic Substrings.
Memory Usage: 37.8 MB, less than 100.00% of Kotlin online submissions for Palindromic Substrings.
 */