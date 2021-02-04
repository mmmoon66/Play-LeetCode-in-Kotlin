import kotlin.math.sqrt

// https://leetcode-cn.com/problems/sqrtx/
// 69. Sqrt(x)
/*
Given a non-negative integer x, compute and return the square root of x.
Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 
Example 1:
Input: x = 4
Output: 2

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.

Constraints:
0 <= x <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sqrtx
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0069_v2 {
    fun mySqrt(x: Int): Int {
        if (x == 0) return 0
        val num = x.toLong()
        var l = 1L
        var r = num
        while(r - l > 1) {
            val mid = (l + (r - l) / 2)
            val product: Long = mid * mid
            if (product == num) return mid.toInt()
            if (product < x) {
                l = mid
            } else {
                r = mid
            }
        }
        return l.toInt()
    }
}

fun main() {
    val s = Solution0069_v2()
    println(s.mySqrt(4))
    println(s.mySqrt(8))
    println(s.mySqrt(666) == sqrt(666.0).toInt())
    println(s.mySqrt(Int.MAX_VALUE) == sqrt(Int.MAX_VALUE.toDouble()).toInt())
}
/*
执行用时：168 ms, 在所有 Kotlin 提交中击败了62.82%的用户
内存消耗：32.5 MB, 在所有 Kotlin 提交中击败了44.87%的用户
 */