// https://leetcode-cn.com/problems/ugly-number-ii/
// 264. Ugly Number II
/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note:  
1 is typically treated as an ugly number.
n does not exceed 1690.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ugly-number-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0264 {
    fun nthUglyNumber(n: Int): Int {
        val hash = hashSetOf(1)
        var x = 0
        var count = 0
        while (count < n) {
            ++x
            if (x in hash) {
                hash.add(2 * x)
                hash.add(3 * x)
                hash.add(5 * x)
                ++count
            }
        }
        return x
    }
}

fun main() {
    val s = Solution0264()
    println(s.nthUglyNumber(10))
    println(s.nthUglyNumber(1))
}
// 超出时间限制