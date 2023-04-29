// https://leetcode.com/problems/sort-colors/
// 75. Sort Colors
/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 */
class Solution0075 {
    fun sortColors(nums: IntArray): Unit {
        var zero = 0
        var one = 0
        var two = 0
        for (num in nums) {
            if (num == 0) {
                ++zero
            } else if (num == 1) {
                ++one
            } else if (num == 2) {
                ++two
            }
        }
        var index = 0
        for (i in 0 until zero) nums[index++] = 0
        for (i in 0 until one) nums[index++] = 1
        for (i in 0 until two) nums[index++] = 2
    }
}
/*
Runtime: 156 ms, faster than 66.67% of Kotlin online submissions for Sort Colors.
Memory Usage: 32.8 MB, less than 100.00% of Kotlin online submissions for Sort Colors.
 */