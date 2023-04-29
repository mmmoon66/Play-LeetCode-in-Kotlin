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
        if (nums.isEmpty()) return 0
        var curMin = 0
        var curMax = Int.MIN_VALUE
        var curSum = 0
        nums.forEach {
            curSum += it
            curMax = maxOf(curMax, curSum - curMin)
            curMin = minOf(curMin, curSum)
        }
        return curMax
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