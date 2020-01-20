// https://leetcode.com/problems/maximum-subarray/
// 53. Maximum Subarray
/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
class Solution0053 {
    fun maxSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var maxCur = nums[0]
        var maxSoFar = nums[0]
        for (i in 1 until nums.size) {
            maxCur = maxOf(nums[i], maxCur + nums[i])
            maxSoFar = maxOf(maxSoFar, maxCur)
        }
        return maxSoFar
    }
}

fun main() {
    val s = Solution0053()
    println(s.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    println(s.maxSubArray(intArrayOf(-1)))
}
/*
Runtime: 148 ms, faster than 98.51% of Kotlin online submissions for Maximum Subarray.
Memory Usage: 47.1 MB, less than 50.00% of Kotlin online submissions for Maximum Subarray.
 */