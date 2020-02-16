// https://leetcode.com/problems/reverse-words-in-a-string/
// 151. Reverse Words in a String
/*
Given an input string, reverse the string word by word.

Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
class Solution0151 {
    fun reverseWords(s: String): String {
        return s.split(' ')
            .asReversed()
            .asSequence()
            .filter { it.isNotEmpty() }
            .joinToString(" ")
    }
}

fun main() {
    val s = Solution0151()
    println(s.reverseWords("the sky is blue"))
    println(s.reverseWords("  hello world!  "))
    println(s.reverseWords("a good   example"))
}
/*
Runtime: 228 ms, faster than 25.71% of Kotlin online submissions for Reverse Words in a String.
Memory Usage: 36.7 MB, less than 100.00% of Kotlin online submissions for Reverse Words in a String.
 */