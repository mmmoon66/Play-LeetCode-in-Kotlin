import java.util.*
import kotlin.collections.HashSet

// https://leetcode.com/problems/longest-word-in-dictionary/
// 720. Longest Word in Dictionary
/*
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.

Example 1:
Input:
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation:
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

Example 2:
Input:
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation:
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".

Note:
All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].
 */
class Solution0720 {
    fun longestWord(words: Array<String>): String {
        val set = HashSet<String>()
        set.add("")
        words.sort()
        var res = ""
        words.forEach { word ->
            if (set.contains(word.substring(0, word.length - 1))) {
                set.add(word)
                if (word.length > res.length) {
                    res = word
                }
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0720()
    println(s.longestWord(arrayOf("w", "wo", "wor", "worl", "world")))
    println(s.longestWord(arrayOf("a", "banana", "app", "appl", "ap", "apply", "apple")))
}
/*
Runtime: 188 ms, faster than 100.00% of Kotlin online submissions for Longest Word in Dictionary.
Memory Usage: 45 MB, less than 100.00% of Kotlin online submissions for Longest Word in Dictionary.
 */