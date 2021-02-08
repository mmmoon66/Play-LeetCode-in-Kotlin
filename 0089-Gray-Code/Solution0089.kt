// https://leetcode-cn.com/problems/gray-code/
// 89. Gray Code
/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given an integer n representing the total number of bits in the code, return any sequence of gray code.

A gray code sequence must begin with 0.
 

Example 1:
Input: n = 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2
[0,2,3,1] is also a valid gray code sequence.
00 - 0
10 - 2
11 - 3
01 - 1

Example 2:
Input: n = 1
Output: [0,1]
 

Constraints:
1 <= n <= 16

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/gray-code
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0089 {
    fun grayCode(n: Int): List<Int> {
        val res = mutableListOf(0)
        for (i in 0 until n) {
            val add = 1 shl i
            for (j in res.size - 1 downTo 0) {
                res.add(add + res[j])
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0089()
    println(s.grayCode(2))
}