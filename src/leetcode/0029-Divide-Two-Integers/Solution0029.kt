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
        val a = dividend.toLong().abs()
        val b = divisor.toLong().abs()
        val sign = if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) 1 else -1
        val c = div(a, b)
        return if (sign > 0) {
            minOf(Int.MAX_VALUE.toLong(), c).toInt()
        } else {
            maxOf(Int.MIN_VALUE.toLong(), -c).toInt()
        }
    }

    private fun Long.abs(): Long = if (this > 0) this else -this

    private fun div(a: Long, b: Long): Long {
        if (a < b) return 0
        var count = 1L
        var tb = b
        while (tb + tb <= a) {
            count += count
            tb += tb
        }
        return count + div(a - tb, b)
    }
}

fun main() {
    val s = Solution0029()
    println(s.divide(10, 3) == 3)
    println(s.divide(7, -3) == -2)
    println(s.divide(1, 1) == 1)
    println(s.divide(0, 1) == 0)
    println(s.divide(Int.MIN_VALUE, -1) == Int.MAX_VALUE)
}
// 超出时间限制