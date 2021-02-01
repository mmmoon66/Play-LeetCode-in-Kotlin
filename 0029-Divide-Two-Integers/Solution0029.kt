import kotlin.math.absoluteValue

// https://leetcode-cn.com/problems/divide-two-integers/
// 29. Divide Two Integers
/*
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note:
Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. For this problem, assume that your function returns 2^31 − 1 when the division result overflows.

Example 1:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.

Example 2:
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.

Example 3:
Input: dividend = 0, divisor = 1
Output: 0

Example 4:
Input: dividend = 1, divisor = 1
Output: 1

Constraints:
-2^31 <= dividend, divisor <= 2^31 - 1
divisor != 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/divide-two-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0029 {
    fun divide(dividend: Int, divisor: Int): Int {
        assert(divisor != 0)
        if (dividend == 0) return 0
        val a: Long = dividend.toLong().absoluteValue
        val b: Long = divisor.toLong().absoluteValue
        val sign = if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) 1 else -1
        var count: Long = 0
        var quotient: Long = 0
        var c: Long = b
        while(c <= a) {
            ++count
            quotient = if (sign == 1) count else -count
            if (quotient > Int.MAX_VALUE || quotient < Int.MIN_VALUE) return Int.MAX_VALUE
            c += b
        }
        return quotient.toInt()
    }
}

fun main() {
    val s = Solution0029()
    println(s.divide(10, 3))
    println(s.divide(7, -3))
    println(s.divide(1, 1))
    println(s.divide(0, 1))
    println(s.divide(Int.MIN_VALUE, -1))
}
// 超出时间限制