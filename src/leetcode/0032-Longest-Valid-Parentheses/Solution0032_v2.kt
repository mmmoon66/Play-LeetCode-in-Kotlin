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
class Solution0032_v2 {
    fun longestValidParentheses(s: String): Int {
        // 保存未匹配的括号的索引
        val stack = Stack<Int>()
        for (i in s.indices) {
            if (s[i] == '(') {
                stack.push(i)
            } else if (s[i] == ')') {
                if (stack.isEmpty()) {
                    stack.push(i)
                } else {
                    val c = s[stack.peek()]
                    if (c == '(') {
                        stack.pop()
                    } else {
                        stack.push(i)
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            return s.length
        } else {
            // 未匹配的索引之间的字符串就是就是合法的子字符串
            var longest = 0
            var r = s.length
            // s(l..r)是合法的子字符串
            while(stack.isNotEmpty()) {
                val l = stack.pop()
                longest = maxOf(longest, r - l - 1)
                r = l
            }
            longest = maxOf(longest, r - (-1) - 1)
            return longest
        }
    }
}

fun main() {
    val s = Solution0032_v2()
    println(s.longestValidParentheses("(()"))
    println(s.longestValidParentheses(")()())"))
    println(s.longestValidParentheses(")))((("))
}
/*
Runtime: 352 ms, faster than 12.50% of Kotlin online submissions for Longest Valid Parentheses.
Memory Usage: 36.5 MB, less than 27.78% of Kotlin online submissions for Longest Valid Parentheses.
 */