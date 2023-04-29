// https://leetcode-cn.com/problems/powx-n/
// 50. Pow(x, n)
/*
Implement pow(x, n), which calculates x raised to the power n (i.e. xn).

Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25

Constraints:
-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= x^n <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/powx-n
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0050 {
    fun myPow(x: Double, n: Int): Double {
        return if (n >= 0) return pow(x, n.toLong()) else 1 / pow(x, -n.toLong())
    }

    private fun pow(x: Double, m: Long): Double {
        if (m == 0L) return 1.0
        if (m == 1L) return x
        val half = pow(x, m / 2)
        return if (m % 2 == 1L) {
            x * half * half
        } else {
            half * half
        }
    }
}

fun main() {
    val s = Solution0050()
    println(s.myPow(2.0, 2))
    println(s.myPow(2.0, -2))
    println(s.myPow(2.1, 3))
    println(s.myPow(2.0, 10))
    println(s.myPow(2.0, 11))
    println(s.myPow(2.0, Int.MIN_VALUE))
}
/*
执行用时：196 ms, 在所有 Kotlin 提交中击败了75.00%的用户
内存消耗：36.2 MB, 在所有 Kotlin 提交中击败了5.77%的用户
 */