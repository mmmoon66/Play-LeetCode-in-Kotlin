// https://leetcode.com/problems/missing-number/
// 268. Missing Number
/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:
Input: [3,0,1]
Output: 2

Example 2:
Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
class Solution0268 {
    fun missingNumber(nums: IntArray): Int {
        var sum = 0L
        val n = nums.size
        for (i in 0..n) {
            sum += i
        }
        for (num in nums) {
            sum -= num
        }
        return sum.toInt()
    }
}
/*
Runtime: 200 ms, faster than 94.20% of Kotlin online submissions for Missing Number.
Memory Usage: 46.1 MB, less than 100.00% of Kotlin online submissions for Missing Number.
 */