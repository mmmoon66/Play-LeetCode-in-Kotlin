// https://leetcode.com/problems/longest-common-prefix/
// 14. Longest Common Prefix
/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.
 */
class Solution0014 {
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        strs.sortBy { it.length }
        var len = strs[0].length
        while(len > 0) {
            val prefix = strs[0].substring(0, len)
            var isCommonPrefix = true
            for (str in strs) {
                if (!str.startsWith(prefix)) {
                    isCommonPrefix = false
                    break
                }
            }
            if (isCommonPrefix) {
                return prefix
            }
            --len
        }
        return ""
    }
}

fun main() {
    val s = Solution0014()
    println(s.longestCommonPrefix(arrayOf("flower", "flow", "flight")))
}
/*
Runtime: 132 ms, faster than 92.73% of Kotlin online submissions for Longest Common Prefix.
Memory Usage: 41.4 MB, less than 100.00% of Kotlin online submissions for Longest Common Prefix.
 */