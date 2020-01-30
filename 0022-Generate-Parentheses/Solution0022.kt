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
    fun generateParenthesis(n: Int): List<String> {
        val dp = mutableListOf(listOf(""))
        for (i in 1..n) {
            // (j)i-1-j
            // 括号内j对括号，括号外(i - 1 - j)对括号
            val list = mutableListOf<String>()
            for (j in 0 until i) {
                dp[j].forEach { str1 ->
                    dp[i - 1 - j].forEach { str2 ->
                        list.add("($str1)$str2")
                    }
                }
            }
            dp.add(list)
        }
        return dp.last()
    }
}

fun main() {
    val s = Solution0022()
    println(s.generateParenthesis(3))
}
/*
Runtime: 136 ms, faster than 89.13% of Kotlin online submissions for Generate Parentheses.
Memory Usage: 39.6 MB, less than 100.00% of Kotlin online submissions for Generate Parentheses.
 */