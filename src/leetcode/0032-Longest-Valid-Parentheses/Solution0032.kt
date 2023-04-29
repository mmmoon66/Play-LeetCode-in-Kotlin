// https://leetcode.com/problems/longest-valid-parentheses/
// 32. Longest Valid Parentheses
/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */
class Solution0032 {
    fun longestValidParentheses(s: String): Int {
        val len = if (s.length % 2 == 0) s.length else s.length - 1
        for (l in len downTo 2 step 2) {
            for (i in s.indices) {
                val r = i + l - 1
                if (r >= s.length) break
                if (isValidParentheses(s, i, r)) return l
            }
        }
        return 0
    }

    private fun isValidParentheses(s: String, l: Int, r: Int): Boolean {
        val len = r - l + 1
        if (len % 2 == 1) return false
        val stack = mutableListOf<Char>()
        for (i in l..r) {
            if (s[i] == '(') {
                stack.add(s[i])
            } else {
                val top = stack.removeLastOrNull()
                if (top != '(') return false
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    val s = Solution0032()
    println(s.longestValidParentheses("(()"))
    println(s.longestValidParentheses(")()())"))
    println(s.longestValidParentheses(")))((("))
}
/*
Time Limit Exceeded
 */