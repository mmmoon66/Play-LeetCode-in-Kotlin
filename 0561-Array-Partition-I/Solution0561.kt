// https://leetcode.com/problems/array-partition-i/
// 561. Array Partition I
/*
Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]
Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
 */
class Solution0561 {
    fun arrayPairSum(nums: IntArray): Int {
        nums.sort()
        var sum = 0
        for (i in 0 until nums.size step 2) {
            sum += nums[i]
        }
        return sum
    }
}

fun main() {
    val s = Solution0561()
    println(s.arrayPairSum(intArrayOf(1, 4, 3, 2)))
}
/*
Runtime: 292 ms, faster than 92.68% of Kotlin online submissions for Array Partition I.
Memory Usage: 50.7 MB, less than 100.00% of Kotlin online submissions for Array Partition I.
 */