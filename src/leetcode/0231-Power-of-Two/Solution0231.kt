// https://leetcode-cn.com/problems/power-of-two/
// 231. Power of Two
/*
Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.


Example 1:
Input: n = 1
Output: true
Explanation: 2^0 = 1

Example 2:
Input: n = 16
Output: true
Explanation: 2^4 = 16

Example 3:
Input: n = 3
Output: false

Example 4:
Input: n = 4
Output: true

Example 5:
Input: n = 5
Output: false

Constraints:
-2^31 <= n <= 2^31 - 1


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/power-of-two
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0231 {
    fun isPowerOfTwo(n: Int): Boolean {
        if (n <= 0) return false
        var num = n
        while(num > 1) {
            if (num % 2 == 1) return false
            num /= 2
        }
        return true
    }
}

fun main() {
    val s = Solution0231()
    println(s.isPowerOfTwo(1))
    println(s.isPowerOfTwo(16))
    println(s.isPowerOfTwo(3))
    println(s.isPowerOfTwo(4))
    println(s.isPowerOfTwo(5))
    println(s.isPowerOfTwo(10))
    println(s.isPowerOfTwo(-4))
}