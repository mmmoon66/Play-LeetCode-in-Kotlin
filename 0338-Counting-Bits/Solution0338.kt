import java.util.*

// https://leetcode.com/problems/counting-bits/
// 338. Counting Bits
/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:
Input: 2
Output: [0,1,1]

Example 2:
Input: 5
Output: [0,1,1,2,1,2]

Follow up:
It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
class Solution0338 {
    fun countBits(num: Int): IntArray {
        val res = IntArray(num + 1)
        for (i in 1..num) {
            res[i] = res[i shr 1] + (i and 1)
        }
        return res
    }
}

fun main() {
    val s = Solution0338()
    println(Arrays.toString(s.countBits(0)))
    println(Arrays.toString(s.countBits(2)))
    println(Arrays.toString(s.countBits(5)))
}
/*
Runtime: 164 ms, faster than 89.66% of Kotlin online submissions for Counting Bits.
Memory Usage: 45.5 MB, less than 100.00% of Kotlin online submissions for Counting Bits.
 */