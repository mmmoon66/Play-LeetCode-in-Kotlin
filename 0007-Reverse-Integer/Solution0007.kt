import kotlin.math.abs

// https://leetcode.com/problems/reverse-integer/
// 7. Reverse Integer
/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
Input: 123
Output: 321

Example 2:
Input: -123
Output: -321

Example 3:
Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
class Solution0007 {
    fun reverse(x: Int): Int {
        var num: Long = abs(x.toLong())
        var res: Long = 0L
        while(num > 0) {
            res = 10 * res + num % 10
            num /= 10
        }
        res *= if (x >= 0) 1 else -1
        if (res in Int.MIN_VALUE..Int.MAX_VALUE) {
            return res.toInt()
        } else {
            return 0
        }
    }
}
/*
Runtime: 96 ms, faster than 97.57% of Kotlin online submissions for Reverse Integer.
Memory Usage: 36.2 MB, less than 50.00% of Kotlin online submissions for Reverse Integer.
 */