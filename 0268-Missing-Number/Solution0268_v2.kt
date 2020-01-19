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
class Solution0268_v2 {
    fun missingNumber(nums: IntArray): Int {
        var result = 1
        nums.forEachIndexed { index, num ->
            result = result xor index
            result = result xor num
        }
        result = result xor nums.size
        return result xor 1
    }
}
/*
Runtime: 192 ms, faster than 97.10% of Kotlin online submissions for Missing Number.
Memory Usage: 46.6 MB, less than 100.00% of Kotlin online submissions for Missing Number.
 */