// https://leetcode.com/problems/map-sum-pairs/
// 677. Map Sum Pairs
/*
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5
 */
class MapSum() {

    /** Initialize your data structure here. */
    private val map = mutableMapOf<String, Int>()


    fun insert(key: String, `val`: Int) {
        map[key] = `val`
    }

    fun sum(prefix: String): Int {
        var res = 0
        for ((key, `val`) in map) {
            if (key.startsWith(prefix)) {
               res += `val`
            }
        }
        return res
    }
}
/*
Runtime: 124 ms, faster than 100.00% of Kotlin online submissions for Map Sum Pairs.
Memory Usage: 39.9 MB, less than 100.00% of Kotlin online submissions for Map Sum Pairs.
 */