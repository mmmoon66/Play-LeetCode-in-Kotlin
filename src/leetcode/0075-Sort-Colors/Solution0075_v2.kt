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
class Solution0075_v2 {
    fun sortColors(nums: IntArray): Unit {
        val n = nums.size
        var lt = -1 // nums[0..lt] < 1
        var gt = n // nums[gt..n-1] > 1
        var i = 0
        while(i < gt) {
            if (nums[i] < 1) {
                if (lt + 1 != i) {
                    nums[lt + 1] = nums[i].also { nums[i] = nums[lt + 1] }
                }
                ++lt
                ++i
            } else if (nums[i] > 1) {
                nums[gt - 1] = nums[i].also { nums[i] = nums[gt - 1] }
                --gt
            } else {
                ++i
            }
        }
    }
}
/*
Runtime: 164 ms, faster than 62.50% of Kotlin online submissions for Sort Colors.
Memory Usage: 33 MB, less than 100.00% of Kotlin online submissions for Sort Colors.
 */