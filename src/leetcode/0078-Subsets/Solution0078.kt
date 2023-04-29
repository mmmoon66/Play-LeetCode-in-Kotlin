// https://leetcode.com/problems/subsets/
// 78. Subsets
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
class Solution0078 {
    private fun generateSubset(nums: IntArray, startIndex: Int, len: Int, subset: MutableList<Int>, res: MutableList<List<Int>>) {
        if (subset.size == len) {
            res.add(subset.toList())
            return
        }
        for (i in startIndex until nums.size) {
            subset.add(nums[i])
            generateSubset(nums, i + 1, len, subset, res)
            subset.removeAt(subset.size - 1)
        }
    }

    fun subsets(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        for (len in 0..nums.size) {
            generateSubset(nums, 0, len, mutableListOf(), res)
        }
        return res
    }
}

fun main() {
    val s = Solution0078()
    println(s.subsets(intArrayOf(1, 2, 3)))
}
/*
Runtime: 168 ms, faster than 85.42% of Kotlin online submissions for Subsets.
Memory Usage: 42.1 MB, less than 100.00% of Kotlin online submissions for Subsets.
 */