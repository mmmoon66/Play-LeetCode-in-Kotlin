// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// 3. Longest Substring Without Repeating Characters
/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
class Solution0003_v2 {
    fun lengthOfLongestSubstring(s: String): Int {
        val hash = IntArray(256)
        var res = 0
        var l = 0
        var r = -1 // s[l...r]内无重复字符
        while(r + 1 < s.length) {
            if (hash[s[r + 1].toInt()] == 0) {
                hash[s[++r].toInt()]++
            } else {
                hash[s[l++].toInt()]--
            }
            res = maxOf(res, r - l + 1)
        }
        return res
    }
}

fun main() {
    val s = Solution0003_v2()
    println(s.lengthOfLongestSubstring("abcabcbb"))
    println(s.lengthOfLongestSubstring("bbbb"))
    println(s.lengthOfLongestSubstring("pwwkew"))
}
/*
Runtime: 192 ms, faster than 84.84% of Kotlin online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 36.1 MB, less than 100.00% of Kotlin online submissions for Longest Substring Without Repeating Characters.
 */