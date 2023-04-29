// https://leetcode.com/problems/string-to-integer-atoi/
// 8. String to Integer (atoi)
/*
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

Example 1:
Input: "42"
Output: 42

Example 2:
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:
Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Example 4:
Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical
             digit or a +/- sign. Therefore no valid conversion could be performed.

Example 5:
Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−2^31) is returned.
 */
class Solution0008 {
    fun myAtoi(str: String): Int {
        var res = 0L
        var sign = 1
        var numCount = 0
        var hasSign = false
        for (c in str.trim()) {
            if (c in '0'..'9') {
                ++numCount
                res = res * 10 + (c - '0')
                if (res * sign >= Int.MAX_VALUE) {
                    return Int.MAX_VALUE
                } else if (res * sign <= Int.MIN_VALUE) {
                    return Int.MIN_VALUE
                }
            } else if (numCount == 0) {
                 if (c == '-' && !hasSign) {
                     sign = -1
                     hasSign = true
                 } else if (c == '+' && !hasSign) {
                     sign = 1
                     hasSign = true
                 } else {
                     break
                 }
            } else {
                break
            }
        }
        return (res * sign).toInt()
    }
}

fun main() {
    val s = Solution0008()
    println(s.myAtoi("42"))
    println(s.myAtoi("   -42"))
    println(s.myAtoi("4193 with words"))
    println(s.myAtoi("words and 987"))
    println(s.myAtoi("-91283472332"))
    println(s.myAtoi("+-2"))
    println(s.myAtoi("-    2"))
}
/*
Runtime: 212 ms, faster than 28.99% of Kotlin online submissions for String to Integer (atoi).
Memory Usage: 34.4 MB, less than 100.00% of Kotlin online submissions for String to Integer (atoi).
 */