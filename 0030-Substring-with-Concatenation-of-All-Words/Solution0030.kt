// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
// 30. Substring with Concatenation of All Words
/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:
Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:
Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
 */

class Solution0030 {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val res = mutableListOf<Int>()
        if (words.isEmpty()) return res
        val length = words.first().length
        val totalLength = words.size * length
        for (i in s.indices) {
            if (isConcatenation(s, i, i + totalLength - 1, words, length)) {
                res.add(i)
            }
        }
        return res
    }

    // s[start..end]是否为words的concatenation
    private fun isConcatenation(s: String, start: Int, end: Int, words: Array<String>, length: Int): Boolean {
        if (end >= s.length) return false
        val freq = mutableMapOf<String, Int>()
        words.forEach { freq[it] = freq.getOrDefault(it, 0) + 1 }

        var i = start
        while(i <= end) {
            val substr = s.substring(i, i + length)
            val count = freq.getOrDefault(substr, 0)
            if (count == 0) {
                return false
            }
            freq[substr] = count - 1
            i += length
        }
        return true
    }
}

fun main() {
    val s = Solution0030()
    println(s.findSubstring("barfoothefoobarman", arrayOf("foo", "bar")))
}
/*
Runtime: 728 ms, faster than 28.57% of Kotlin online submissions for Substring with Concatenation of All Words.
Memory Usage: 37.1 MB, less than 69.23% of Kotlin online submissions for Substring with Concatenation of All Words.
 */