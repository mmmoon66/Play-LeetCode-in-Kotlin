// https://leetcode.com/problems/valid-anagram/
// 242. Valid Anagram
/*
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
class Solution0242 {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }
        val freq = mutableMapOf<Char, Int>()
        s.forEach { c ->
            freq[c] = freq.getOrDefault(c, 0) + 1
        }
        t.forEach { c ->
            val count = freq[c]
            if (count == null || count == 0) {
                return false
            }
            freq[c] = count - 1
        }
        return true
    }
}

fun main() {
    val s = Solution0242()
    println(s.isAnagram("anagram", "nagaram"))
    println(s.isAnagram("car", "rat"))
}
/*
Runtime: 168 ms, faster than 98.85% of Kotlin online submissions for Valid Anagram.
Memory Usage: 43.9 MB, less than 33.33% of Kotlin online submissions for Valid Anagram.
 */