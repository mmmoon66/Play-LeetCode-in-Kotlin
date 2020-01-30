// https://leetcode.com/problems/combination-sum/
// 39. Combination Sum
/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */
class Solution0039 {
    private fun generateCombination(
        candidates: IntArray,
        target: Int,
        startIndex: Int,
        solution: MutableList<Int>,
        sum: Int,
        res: MutableList<List<Int>>
    ) {
        if (sum == target) {
            res.add(solution.toList())
            return
        }
        if (sum > target || startIndex >= candidates.size) {
            return
        }
        for (i in startIndex until candidates.size) {
            solution.add(candidates[i])
            generateCombination(candidates, target, i, solution, sum + candidates[i], res)
            solution.removeAt(solution.size - 1)
        }
    }

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        generateCombination(candidates, target, 0, mutableListOf(), 0, res)
        return res
    }
}

fun main() {
    val s = Solution0039()
    println(s.combinationSum(intArrayOf(2, 3, 6, 7), 7))
    println(s.combinationSum(intArrayOf(2, 3, 5), 8))
}
/*
Runtime: 196 ms, faster than 96.55% of Kotlin online submissions for Combination Sum.
Memory Usage: 44.4 MB, less than 100.00% of Kotlin online submissions for Combination Sum.
 */