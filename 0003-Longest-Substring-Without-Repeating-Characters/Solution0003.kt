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
class Solution0003 {
    fun lengthOfLongestSubstring(s: String): Int {
        val freq = mutableMapOf<Char, Int>()
        var res = 0
        var len = 0
        var l = 0
        // s[l...i)内无重复字符
        for (i in 0 until s.length) {
            val c = s[i]
            if (freq[c] == null || freq[c] == 0) {
                ++len
                freq[c] = freq.getOrDefault(c, 0) + 1
            } else {
                res = maxOf(res, len)

                while(s[l] != c) {
                    freq[s[l]]  = freq[s[l]]!! - 1
                    ++l
                    --len
                }
                ++l
            }
        }
        res = maxOf(res, len)
        return res
    }
}

fun main() {
    val s = Solution0003()
    println(s.lengthOfLongestSubstring("abcabcbb"))
    println(s.lengthOfLongestSubstring("bbbb"))
    println(s.lengthOfLongestSubstring("pwwkew"))
}
/*
Runtime: 212 ms, faster than 64.14% of Kotlin online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 38.3 MB, less than 20.00% of Kotlin online submissions for Longest Substring Without Repeating Characters.
 */