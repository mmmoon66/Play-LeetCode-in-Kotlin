// https://leetcode.com/problems/combinations/
// 77. Combinations
/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
class Solution0077 {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        generateCombination(n, k, 1, mutableListOf(), res)
        return res
    }

    private fun generateCombination(
        n: Int,
        k: Int,
        startNum: Int,
        solution: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        if (solution.size == k) {
            res.add(solution.toList())
            return
        }
        if (startNum > n) {
            return
        }
        for (i in startNum..n) {
            solution.add(i)
            generateCombination(n, k, i + 1, solution, res)
            solution.removeAt(solution.size - 1)
        }
    }
}

fun main() {
    val s = Solution0077()
    println(s.combine(4, 2))
}
/*
Runtime: 260 ms, faster than 84.21% of Kotlin online submissions for Combinations.
Memory Usage: 48.9 MB, less than 100.00% of Kotlin online submissions for Combinations.
 */