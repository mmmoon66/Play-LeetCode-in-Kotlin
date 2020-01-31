// https://leetcode.com/problems/group-anagrams/
// 49. Group Anagrams
/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

All inputs will be in lowercase.
The order of your output does not matter.
 */
class Solution0049_v2 {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        for (str in strs) {
            val s = String(str.toCharArray().sorted().toCharArray())
            if (map[s] == null) {
                map[s] = mutableListOf()
            }
            map[s]!!.add(str)
        }
        return map.values.toList()
    }
}
/*
Runtime: 308 ms, faster than 84.44% of Kotlin online submissions for Group Anagrams.
Memory Usage: 48.3 MB, less than 100.00% of Kotlin online submissions for Group Anagrams.
 */