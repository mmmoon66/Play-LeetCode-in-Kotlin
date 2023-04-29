// https://leetcode.com/problems/reverse-words-in-a-string-iii/
// 557. Reverse Words in a String III
/*
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
class Solution0557 {
    fun reverseWords(s: String): String {
        return s.split(" ").joinToString(separator = " ") { word -> word.reversed() }
    }
}

fun main() {
    val s = Solution0557()
    println(s.reverseWords("Let's take LeetCode contest"))
}
/*
Runtime: 212 ms, faster than 100.00% of Kotlin online submissions for Reverse Words in a String III.
Memory Usage: 44.1 MB, less than 100.00% of Kotlin online submissions for Reverse Words in a String III.
 */