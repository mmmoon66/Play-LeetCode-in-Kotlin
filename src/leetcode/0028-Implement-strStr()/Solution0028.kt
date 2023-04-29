// https://leetcode.com/problems/implement-strstr/
// 28. Implement strStr()
/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:
Input: haystack = "aaaaa", needle = "bba"
Output: -1

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
class Solution0028 {
    fun strStr(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0
        for (i in 0..haystack.length - needle.length) {
            if (haystack.substring(i, i + needle.length) == needle) {
                return i
            }
        }
        return -1
    }
}

fun main() {
    val s = Solution0028()
    println(s.strStr("hello", "ll"))
    println(s.strStr("hello", "lo"))
}
/*
Runtime: 156 ms, faster than 82.76% of Kotlin online submissions for Implement strStr().
Memory Usage: 38.8 MB, less than 100.00% of Kotlin online submissions for Implement strStr().
 */