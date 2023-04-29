// https://leetcode.com/problems/to-lower-case/
// 709. To Lower Case
/*
Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

Example 1:
Input: "Hello"
Output: "hello"

Example 2:
Input: "here"
Output: "here"

Example 3:
Input: "LOVELY"
Output: "lovely"
 */
class Solution0709 {
    fun toLowerCase(str: String): String {
        val sb = StringBuffer()
        str.forEach {
            if (it in 'A'..'Z') {
                sb.append((it + ('a' - 'A')))
            } else {
                sb.append(it)
            }
        }
        return sb.toString()
    }
}

fun main() {
    val s = Solution0709()
    println(s.toLowerCase("Hello"))
    println(s.toLowerCase("here"))
    println(s.toLowerCase("LOVELY"))
}
/*
Runtime: 92 ms, faster than 100.00% of Kotlin online submissions for To Lower Case.
Memory Usage: 36.3 MB, less than 50.00% of Kotlin online submissions for To Lower Case.
 */