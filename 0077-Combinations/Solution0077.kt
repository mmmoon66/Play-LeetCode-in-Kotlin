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
        val ret = mutableListOf<List<Int>>()
        generateCombination(n, k, 1, mutableListOf(), ret)
        return ret
    }

    private fun generateCombination(
        n: Int, k: Int, startNum: Int, path: MutableList<Int>, ret: MutableList<List<Int>>
    ) {
        if (path.size == k) {
            ret.add(path.toList())
            return
        }
        if (path.size + (n - startNum + 1) < k) return//剪枝
        for (i in startNum..n) {
            path.add(i)
            generateCombination(n, k, i + 1, path, ret)
            path.removeAt(path.lastIndex)
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