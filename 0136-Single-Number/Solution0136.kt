// https://leetcode.com/problems/single-number/
// 136. Single Number
/*
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
Input: [2,2,1]
Output: 1

Example 2:
Input: [4,1,2,1,2]
Output: 4
 */
class Solution0136 {
    fun singleNumber(nums: IntArray): Int {
        var x = 1
        nums.forEach { num -> x = x xor num }
        return x xor 1
    }
}

fun main() {
    val s = Solution0136()
    println(s.singleNumber(intArrayOf(2, 2, 1)))
    println(s.singleNumber(intArrayOf(4, 1, 2, 2, 1)))
}
/*
Runtime: 152 ms, faster than 100.00% of Kotlin online submissions for Single Number.
Memory Usage: 43.2 MB, less than 12.50% of Kotlin online submissions for Single Number.
 */