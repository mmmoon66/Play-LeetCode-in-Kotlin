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
class Solution0090 {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val resultSet = mutableSetOf<List<Int>>()
        for (len in 0..nums.size) {
            generateSubset(nums, len, 0, mutableListOf(), resultSet)
        }
        return resultSet.toList()
    }

    private fun generateSubset(
        nums: IntArray,
        len: Int,
        index: Int,
        subset: MutableList<Int>,
        res: MutableSet<List<Int>>
    ) {
        if (subset.size == len) {
            res.add(subset.toList())
            return
        }
        for (i in index until nums.size) {
            subset.add(nums[i])
            generateSubset(nums, len, i + 1, subset, res)
            subset.removeAt(subset.size -1)
        }
    }
}

fun main() {
    val s = Solution0090()
    println(s.subsetsWithDup(intArrayOf(3, 1, 2, 2)))
}
/*
Runtime: 420 ms, faster than 5.56% of Kotlin online submissions for Subsets II.
Memory Usage: 44 MB, less than 12.50% of Kotlin online submissions for Subsets II.
 */