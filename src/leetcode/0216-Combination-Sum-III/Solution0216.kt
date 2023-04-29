// https://leetcode.com/problems/combination-sum-iii/
// 216. Combination Sum III
/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
class Solution0216 {
    private fun generateCombination(
        k: Int,
        n: Int,
        startNum: Int,
        solution: MutableList<Int>,
        sum: Int,
        res: MutableList<List<Int>>
    ) {
        if (solution.size == k && sum == n) {
            res.add(solution.toList())
            return
        }
        if (solution.size > k || sum > n || startNum > 9) {
            return
        }
        for (i in startNum..9) {
            solution.add(i)
            generateCombination(k, n, i + 1, solution, sum + i, res)
            solution.removeAt(solution.size - 1)
        }
    }

    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        generateCombination(k, n, 1, mutableListOf(), 0, res)
        return res
    }
}

fun main() {
    val s = Solution0216()
    println(s.combinationSum3(3, 7))
    println(s.combinationSum3(3, 9))
}
/*
Runtime: 172 ms, faster than 51.85% of Kotlin online submissions for Combination Sum III.
Memory Usage: 38.4 MB, less than 100.00% of Kotlin online submissions for Combination Sum III.
 */