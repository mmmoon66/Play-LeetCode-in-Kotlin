// https://leetcode.com/problems/longest-palindrome/
// 409. Longest Palindrome
/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input:
"abccccdd"
Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
class Solution0409 {
    fun longestPalindrome(s: String): Int {
        val freq = HashMap<Char, Int>()
        for (c in s) freq[c] = freq.getOrDefault(c, 0) + 1
        var hasOddCount = false
        var res = 0
        for (count in freq.values) {
            if (count % 2 == 0) {
                res += count
            } else {
                hasOddCount = true
                res += count - 1
            }
        }
        if (hasOddCount) ++res
        return res
    }
}

fun main() {
    val s = Solution0409()
    println(s.longestPalindrome("abccccdd"))
}
/*
Runtime: 136 ms, faster than 91.67% of Kotlin online submissions for Longest Palindrome.
Memory Usage: 38 MB, less than 100.00% of Kotlin online submissions for Longest Palindrome.
 */