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
class Solution0090_v2 {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val res = mutableListOf<List<Int>>()
        for (len in 0..nums.size) {
            generateSubset(nums, len, 0, mutableListOf(), res)
        }
        return res
    }

    private fun generateSubset(
        sortedNums: IntArray,
        len: Int,
        index: Int,
        subset: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        if (subset.size == len) {
            res.add(subset.toList())
            return
        }
        for (i in index until sortedNums.size) {
            if (i - 1 >= index && sortedNums[i] == sortedNums[i - 1]) {
                continue
            }
            subset.add(sortedNums[i])
            generateSubset(sortedNums, len, i + 1, subset, res)
            subset.removeAt(subset.size - 1)
        }
    }
}

fun main() {
    val s = Solution0090_v2()
    println(s.subsetsWithDup(intArrayOf(1, 2, 2, 2)))
}
/*
Runtime: 320 ms, faster than 27.78% of Kotlin online submissions for Subsets II.
Memory Usage: 39.9 MB, less than 12.50% of Kotlin online submissions for Subsets II.
 */