// https://leetcode.com/problems/valid-palindrome/
// 125. Valid Palindrome
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:
Input: "race a car"
Output: false
 */
class Solution0125 {
    private fun isAlphanumeric(c: Char): Boolean {
        return c in 'a'..'z' || c in 'A'..'Z' || c in '0'..'9'
    }

    fun isPalindrome(s: String): Boolean {
        var l = 0
        var r = s.length - 1
        while(true) {
            while(l <= r && !isAlphanumeric(s[l])) ++l
            while(r >= l && !isAlphanumeric(s[r])) --r
            if (l > r) {
                break
            }
            if (s[l].equals(s[r], true)) {
                ++l
                --r
            } else {
                return false
            }
        }
        return true
    }
}

fun main() {
    val s = Solution0125()
    println(s.isPalindrome("A man, a plan, a canal: Panama"))
    println(s.isPalindrome("race a car"))
}
/*
Runtime: 136 ms, faster than 100.00% of Kotlin online submissions for Valid Palindrome.
Memory Usage: 40.3 MB, less than 100.00% of Kotlin online submissions for Valid Palindrome.
 */