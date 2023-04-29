// https://leetcode.com/problems/subsets-ii/
// 90. Subsets II
/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
class Solution0090_v3 {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()

        val res = mutableListOf<List<Int>>()
        res.add(emptyList())
        var i = 0
        while(i < nums.size) {
            var dupCount = 1
            while(i + dupCount < nums.size && nums[i] == nums[i + dupCount]) {
                ++dupCount
            }
            val subsetCount = res.size
            for (p in 0 until subsetCount) {
                val subset = res[p].toMutableList()
                for (q in 0 until dupCount) {
                    subset.add(nums[i])
                    res.add(subset.toList())
                }
            }
            i += dupCount
        }

        return res
    }
}

fun main() {
    val s = Solution0090_v3()
    println(s.subsetsWithDup(intArrayOf(1, 2, 2)))
}
/*
Runtime: 268 ms, faster than 33.33% of Kotlin online submissions for Subsets II.
Memory Usage: 40.4 MB, less than 12.50% of Kotlin online submissions for Subsets II.
 */