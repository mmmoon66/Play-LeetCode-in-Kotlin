// https://leetcode-cn.com/problems/valid-perfect-square/
// 367. Valid Perfect Square
/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.

Example 1:
Input: num = 16
Output: true

Example 2:
Input: num = 14
Output: false

Constraints:
1 <= num <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-perfect-square
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0367 {
    fun isPerfectSquare(num: Int): Boolean {
        var l = 1
        var r = num
        while(l <= r) {
            val mid = l + (r - l) / 2
            val product = mid.toLong().let { it * it }
            if (product == num.toLong()) return true
            if (product < num.toLong()) l = mid + 1
            else r = mid - 1
        }
        return false
    }
}

fun main() {
    val s = Solution0367()
    println(s.isPerfectSquare(16))
    println(s.isPerfectSquare(12))
}