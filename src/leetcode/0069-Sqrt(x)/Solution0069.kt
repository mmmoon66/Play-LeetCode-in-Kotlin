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
class Solution0069 {
    fun mySqrt(x: Int): Int {
        var i = 0L
        val n = x.toLong()
        while(i * i <= n) ++i
        return (i - 1).toInt()
    }
}

fun main() {
    val s = Solution0069()
    println(s.mySqrt(4))
    println(s.mySqrt(8))
}
/*
执行用时：176 ms, 在所有 Kotlin 提交中击败了35.90%的用户
内存消耗：32.6 MB, 在所有 Kotlin 提交中击败了24.36%的用户
 */