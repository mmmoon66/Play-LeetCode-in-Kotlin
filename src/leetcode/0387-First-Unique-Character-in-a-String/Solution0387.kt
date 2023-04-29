import java.util.*

// https://leetcode.com/problems/first-unique-character-in-a-string/
// 387. First Unique Character in a String
/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

Note: You may assume the string contain only lowercase letters.
 */
class Solution0387 {
    fun firstUniqChar(s: String): Int {
        val hash = IntArray(26) { 0 }
        s.forEach { ++hash[it - 'a']}
        s.forEachIndexed { index, c ->
            if (hash[c - 'a'] == 1) {
                return index
            }
        }
        return -1
    }
}

fun main() {
    val s = Solution0387()
    println(s.firstUniqChar("leetcode"))
    println(s.firstUniqChar("loveleetcode"))
}
/*
Runtime: 216 ms, faster than 95.71% of Kotlin online submissions for First Unique Character in a String.
Memory Usage: 41.8 MB, less than 100.00% of Kotlin online submissions for First Unique Character in a String.
 */