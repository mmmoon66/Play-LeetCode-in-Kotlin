// https://leetcode.com/problems/permutations/
// 46. Permutations
/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
class Solution0046 {
    fun permute(nums: IntArray): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        genPermutation(nums, mutableListOf(), ret, BooleanArray(nums.size))
        return ret
    }

    private fun genPermutation(
        nums: IntArray, solution: MutableList<Int>, ret: MutableList<List<Int>>, used: BooleanArray
    ) {
        if (solution.size == nums.size) {
            ret.add(solution.toList())
            return
        }
        nums.forEachIndexed { index, i ->
            if (used[index].not()) {
                used[index] = true
                solution.add(i)
                genPermutation(nums, solution, ret, used)
                solution.removeLast()
                used[index] = false
            }
        }
    }
}

fun main() {
    val s = Solution0046()
    println(s.permute(intArrayOf(1, 2, 3)))
}
/*
Runtime: 180 ms, faster than 89.85% of Kotlin online submissions for Permutations.
Memory Usage: 45.5 MB, less than 50.00% of Kotlin online submissions for Permutations.
 */

// https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)