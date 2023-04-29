import java.util.*

// https://leetcode.com/problems/top-k-frequent-elements/
// 347. Top K Frequent Elements
/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
class Solution0347 {
    fun topKFrequent(nums: IntArray, k: Int): List<Int> {
        val freq = mutableMapOf<Int, Int>()
        for (num in nums) {
            freq[num] = freq.getOrDefault(num, 0) + 1
        }
        val bucket = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }
        for ((num, count) in freq) {
            bucket[count].add(num)
        }
        val res = mutableListOf<Int>()
        for (i in bucket.size-1 downTo 0) {
            bucket[i].forEach { num ->
                if (res.size < k) {
                    res.add(num)
                } else {
                    return res
                }
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0347()
    println(s.topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2))
}
/*
Runtime: 244 ms, faster than 86.15% of Kotlin online submissions for Top K Frequent Elements.
Memory Usage: 43.7 MB, less than 33.33% of Kotlin online submissions for Top K Frequent Elements.
 */