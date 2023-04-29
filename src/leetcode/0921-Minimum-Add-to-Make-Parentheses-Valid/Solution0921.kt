import java.util.*

// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
// 921. Minimum Add to Make Parentheses Valid
class Solution0921 {
    fun minAddToMakeValid(S: String): Int {
        var res = 0
        val stack = Stack<Char>()
        S.forEach { c ->
            if (c == '(') {
                stack.push(c)
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    ++res
                } else if (stack.peek() == '('){
                    stack.pop()
                }
            }
        }
        res += stack.size
        return res
    }
}

fun main() {
    val s = Solution0921()
    println(s.minAddToMakeValid("((("))
    println(s.minAddToMakeValid(")))((("))
}
/*
Runtime: 104 ms, faster than 100.00% of Kotlin online submissions for Minimum Add to Make Parentheses Valid.
Memory Usage: 36.5 MB, less than 100.00% of Kotlin online submissions for Minimum Add to Make Parentheses Valid.
 */