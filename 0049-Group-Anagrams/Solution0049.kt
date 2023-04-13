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
class Solution0049 {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        for (str in strs) {
            val sorted = String(str.toCharArray().sorted().toCharArray())
            if (map[sorted] == null) {
                map[sorted] = mutableListOf()
            }
            map[sorted]!!.add(str)
        }
        return map.values.toList()
    }
}

fun main() {
    val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    val s = Solution0049()
    println(s.groupAnagrams(strs))
}
/*
Runtime: 2844 ms, faster than 5.56% of Kotlin online submissions for Group Anagrams.
Memory Usage: 56.4 MB, less than 100.00% of Kotlin online submissions for Group Anagrams.
 */