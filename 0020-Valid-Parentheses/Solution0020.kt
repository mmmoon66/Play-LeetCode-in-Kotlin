import java.util.*

// https://leetcode.com/problems/valid-parentheses/
// 20. Valid Parentheses
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:
Input: "()"
Output: true

Example 2:
Input: "()[]{}"
Output: true

Example 3:
Input: "(]"
Output: false

Example 4:
Input: "([)]"
Output: false

Example 5:
Input: "{[]}"
Output: true
 */
class Solution0020 {
    fun isValid(s: String): Boolean {
        if (s.length % 2 == 1) return false
        val stack = Stack<Char>()
        for (c in s) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c)
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false
                val top = stack.pop()
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false
            }
        }
        return stack.isEmpty()
    }
}
/*
Runtime: 104 ms, faster than 97.75% of Kotlin online submissions for Valid Parentheses.
Memory Usage: 36.2 MB, less than 100.00% of Kotlin online submissions for Valid Parentheses.
 */