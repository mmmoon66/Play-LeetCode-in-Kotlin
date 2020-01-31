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
        val set = mutableSetOf<String>()
        for (str in strs) {
            set.add(str.toCharArray().sorted().joinToString(""))
        }
        val res = mutableListOf<List<String>>()
        for (s in set) {
            val item = mutableListOf<String>()
            for (str in strs) {
                if (isAnagram(s, str)) item.add(str)
            }
            res.add(item)
        }
        return res
    }

    private fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val freq = mutableMapOf<Char, Int>()
        for (c in s) {
            freq[c] = freq.getOrDefault(c, 0) + 1
        }
        for (c in t) {
            if (freq[c] == null || freq[c] == 0) {
                return false
            }
            freq[c] = freq[c]!! - 1
        }
        return true
    }
}
/*
Runtime: 2844 ms, faster than 5.56% of Kotlin online submissions for Group Anagrams.
Memory Usage: 56.4 MB, less than 100.00% of Kotlin online submissions for Group Anagrams.
 */