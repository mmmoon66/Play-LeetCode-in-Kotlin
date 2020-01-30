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
    private lateinit var used: BooleanArray

    private fun generatePermutation(nums: IntArray, path: MutableList<Int>, res: MutableList<List<Int>>) {
        if (path.size == nums.size) {
            res.add(path.toList())
            return
        }
        nums.forEachIndexed { index, num ->
            if (!used[index]) {
                used[index] = true
                path.add(num)
                generatePermutation(nums, path, res)
                path.removeAt(path.size - 1)
                used[index] = false
            }
        }
    }

    fun permute(nums: IntArray): List<List<Int>> {
        used = BooleanArray(nums.size)
        val res = mutableListOf<List<Int>>()
        generatePermutation(nums, mutableListOf(), res)
        return res
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