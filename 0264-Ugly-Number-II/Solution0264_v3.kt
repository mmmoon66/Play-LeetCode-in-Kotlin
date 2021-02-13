import java.util.*
import kotlin.collections.HashSet

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
class Solution0264_v3 {
    private val ugly by lazy { Ugly() }

    fun nthUglyNumber(n: Int): Int {
        return ugly.get(n - 1)
    }

    class Ugly {
        private val uglyNums = LongArray(1690)
        private var i2 = 0
        private var i3 = 0
        private var i5 = 0

        init {
            uglyNums[0] = 1
            for (i in 1 until 1690) {
                val u = minOf(2 * uglyNums[i2], 3 * uglyNums[i3], 5 * uglyNums[i5])
                uglyNums[i] = u
                if (u == 2 * uglyNums[i2]) ++i2
                if (u == 3 * uglyNums[i3]) ++i3
                if (u == 5 * uglyNums[i5]) ++i5
            }
        }

        private fun minOf(a: Long, b: Long, c: Long): Long {
            return if (a < b) {
                if (a < c) a else c
            } else {
                if (b < c) b else c
            }
        }

        fun get(index: Int): Int {
            return uglyNums[index].toInt()
        }
    }
}

fun main() {
    val s = Solution0264_v3()
    println(s.nthUglyNumber(1407))
    println(s.nthUglyNumber(10))
    println(s.nthUglyNumber(1))
}
/*
执行用时：208 ms, 在所有 Kotlin 提交中击败了40.00%的用户
内存消耗：34.8 MB, 在所有 Kotlin 提交中击败了40.00%的用户
 */