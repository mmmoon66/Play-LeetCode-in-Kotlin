// https://leetcode.com/problems/generate-parentheses/
// 22. Generate Parentheses
/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
class Solution0022 {

    // 方法1: 动态规划
/*    fun generateParenthesis(n: Int): List<String> {
        val dp = mutableListOf(listOf(""))
        for (i in 1..n) {
            val list = mutableListOf<String>()
            // ({countIn对括号}){countOut对括号}
            for (countIn in 0 until i) {
                val countOut = i - 1 - countIn
                dp[countIn].forEach { str1 ->
                    dp[countOut].forEach { str2 ->
                        list.add("($str1)$str2")
                    }
                }
            }
            dp.add(list)
        }
        return dp.last()
    }*/

    // 方法2: 记忆化搜索
    private val memo = mutableListOf(listOf(""))
    fun generateParenthesis(n: Int): List<String> {
        memo.getOrNull(n)?.let {
            return it
        }
        // ({countIn对括号}){countOut对括号}
        val ret = mutableListOf<String>()
        for (countIn in 0 until n) {
            val countOut = n - 1 - countIn
            generateParenthesis(countIn).forEach { str1 ->
                generateParenthesis(countOut).forEach { str2 ->
                    ret.add("($str1)$str2")
                }
            }
        }
        memo.add(ret)
        return ret
    }
}

fun main() {
    val s = Solution0022()
    println(s.generateParenthesis(3))
    println(s.generateParenthesis(2))
}
/*
Runtime: 136 ms, faster than 89.13% of Kotlin online submissions for Generate Parentheses.
Memory Usage: 39.6 MB, less than 100.00% of Kotlin online submissions for Generate Parentheses.
 */