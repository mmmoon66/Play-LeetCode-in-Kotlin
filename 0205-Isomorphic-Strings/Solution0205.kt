// https://leetcode.com/problems/isomorphic-strings/
// 205. Isomorphic Strings
/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Note:
You may assume both s and t have the same length.
 */
class Solution0205 {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val s2tMap = HashMap<Char, Char>()
        val t2sMap = HashMap<Char, Char>()
        for (i in 0 until s.length) {
            val sChar = s[i]
            val tChar = t[i]
            if (s2tMap[sChar] == null && t2sMap[tChar] == null) {
                s2tMap[sChar] = tChar
                t2sMap[tChar] = sChar
            } else if (s2tMap[sChar] != tChar || t2sMap[tChar] != sChar) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val s = Solution0205()
    println(s.isIsomorphic("egg", "add"))
    println(s.isIsomorphic("bar", "foo"))
    println(s.isIsomorphic("paper", "title"))
}
/*
Runtime: 140 ms, faster than 100.00% of Kotlin online submissions for Isomorphic Strings.
Memory Usage: 39.3 MB, less than 100.00% of Kotlin online submissions for Isomorphic Strings.
 */