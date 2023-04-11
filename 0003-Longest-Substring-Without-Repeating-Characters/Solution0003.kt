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
        val freq = IntArray(256)
        var l = 0
        var r = -1//letters[l..r]范围内无重复元素
        var ret = 0
        while (r + 1 < s.length) {
            if (freq[s[r + 1].toInt()] == 0) {
                ++freq[s[++r].toInt()]
            } else {
                --freq[s[l++].toInt()]
            }
            ret = maxOf(ret, r - l + 1)
        }
        return ret
    }
}

fun main() {
    val s = Solution0003()
    println(s.lengthOfLongestSubstring("abcabcbb") == 3)
    println(s.lengthOfLongestSubstring("bbbb") == 1)
    println(s.lengthOfLongestSubstring("pwwkew") == 3)
}
/*
Runtime: 212 ms, faster than 64.14% of Kotlin online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 38.3 MB, less than 20.00% of Kotlin online submissions for Longest Substring Without Repeating Characters.
 */