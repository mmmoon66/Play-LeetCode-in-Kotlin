import java.util.*

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
        // 可能的最大长度
        val maxLen = s.length - s.length % 2
        var len = maxLen
        while(len >= 0) {
            var i = 0
            while(i < s.length) {
                val end = i + len - 1
                if (end < s.length && isValidParentheses(s, i, end)) return len
                ++i
            }
            len -= 2
        }
        return 0
    }

    // s[start..end]
    private fun isValidParentheses(s: String, start: Int, end: Int): Boolean {
        val len = end - start + 1
        if (len % 2 == 1) return false
        val stack = Stack<Char>()
        var i = start
        while(i <= end) {
            val c = s[i]
            ++i
            if (c == '(') {
                stack.push(c)
            } else if (c == ')') {
                if (stack.isEmpty()) return false
                val top = stack.pop()
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