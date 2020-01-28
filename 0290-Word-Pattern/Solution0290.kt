// https://leetcode.com/problems/word-pattern/
// 290. Word Pattern
/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:
Input: pattern = "abba", str = "dog cat cat dog"
Output: true

Example 2:
Input:pattern = "abba", str = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false

Example 4:
Input: pattern = "abba", str = "dog dog dog dog"
Output: false

Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
class Solution0290 {
    fun wordPattern(pattern: String, str: String): Boolean {
        val words = str.split(' ')
        if (pattern.length != words.size) return false
        val p2w = HashMap<Char, String>()
        val w2p = HashMap<String, Char>()
        for (i in 0 until pattern.length) {
            val p = pattern[i]
            val w = words[i]
            if (p2w[p] == null && w2p[w] == null) {
                p2w[p] = w
                w2p[w] = p
            } else if (p2w[p] != w || w2p[w] != p) {
                return false
            }
        }
        return true
    }
}
/*
Runtime: 104 ms, faster than 96.43% of Kotlin online submissions for Word Pattern.
Memory Usage: 38.5 MB, less than 100.00% of Kotlin online submissions for Word Pattern.
 */