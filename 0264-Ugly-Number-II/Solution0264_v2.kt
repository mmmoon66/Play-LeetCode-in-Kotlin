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
class Solution0264_v2 {
    private val ugly by lazy { Ugly() }

    fun nthUglyNumber(n: Int): Int {
        return ugly.get(n)
    }

    class Ugly {
        private val heap = PriorityQueue<Long>()
        private val seen = HashSet<Long>()
        private val uglyNums = LongArray(1690 + 1)
        private val primes = intArrayOf(2, 3, 5)

        init {
            heap.add(1)
            for (i in 1..1690) {
                val curUgly = heap.poll()
                uglyNums[i] = curUgly

                primes.forEach {
                    val newUgly = it * curUgly
                    if (newUgly !in seen) {
                        seen.add(newUgly)
                        heap.add(newUgly)
                    }
                }
            }
        }

        fun get(index: Int): Int {
            return uglyNums[index].toInt()
        }
    }
}

fun main() {
    val s = Solution0264_v2()
    println(s.nthUglyNumber(1407))
    println(s.nthUglyNumber(10))
    println(s.nthUglyNumber(1))
}
/*
执行用时：648 ms, 在所有 Kotlin 提交中击败了10.00%的用户
内存消耗：37.6 MB, 在所有 Kotlin 提交中击败了20.00%的用户
 */