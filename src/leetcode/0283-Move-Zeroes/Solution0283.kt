// https://leetcode.com/problems/move-zeroes/
// 283. Move Zeroes
/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
class Solution0283 {
    fun moveZeroes(nums: IntArray): Unit {
        var k = -1// nums[0..k] != 0
        nums.forEach { num ->
            if (num != 0) {
                nums[++k] = num
            }
        }
        for (i in k+1 until nums.size) {
            nums[i] = 0
        }
    }
}
/*
Runtime: 152 ms, faster than 100.00% of Kotlin online submissions for Move Zeroes.
Memory Usage: 43.9 MB, less than 100.00% of Kotlin online submissions for Move Zeroes.
 */