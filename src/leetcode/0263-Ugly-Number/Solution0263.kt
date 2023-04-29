// https://leetcode-cn.com/problems/ugly-number/
// 263. Ugly Number
/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:
Input: 6
Output: true
Explanation: 6 = 2 × 3

Example 2:
Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2

Example 3:
Input: 14
Output: false
Explanation: 14 is not ugly since it includes another prime factor 7.

Note:
1 is typically treated as an ugly number.
Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ugly-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0263 {
    fun isUgly(num: Int): Boolean {
        if (num <= 0) return false
        var n = num
        while(n > 1) {
            if (n % 5 != 0 && n % 3 != 0 && n % 2 != 0) return false
            if (n % 5 == 0) n /= 5
            if (n % 3 == 0) n /= 3
            if (n % 2 == 0) n /= 2
        }
        return true
    }
}

fun main() {
    val s = Solution0263()
    println(s.isUgly(6))
    println(s.isUgly(8))
    println(s.isUgly(14))
    println(s.isUgly(1))
}