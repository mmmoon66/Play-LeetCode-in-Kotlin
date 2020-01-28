// https://leetcode.com/problems/valid-palindrome-ii/
// 680. Valid Palindrome II
/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
class Solution0680 {
    private fun isPalindrome(s: String, L: Int, R: Int): Boolean {
        var l = L
        var r = R
        while(l < r) {
            if (s[l] != s[r]) {
                return false
            } else {
                ++l
                --r
            }
        }
        return true
    }

    fun validPalindrome(s: String): Boolean {
        var l = 0
        var r = s.length - 1
        while(l < r) {
            if (s[l] == s[r]) {
                ++l
                --r
            } else {
                return isPalindrome(s, l, r - 1) || isPalindrome(s, l + 1, r)
            }
        }
        return true
    }
}
/*
Runtime: 296 ms, faster than 23.33% of Kotlin online submissions for Valid Palindrome II.
Memory Usage: 47.8 MB, less than 100.00% of Kotlin online submissions for Valid Palindrome II.
 */