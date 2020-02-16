// https://leetcode.com/problems/maximum-product-subarray/
// 152. Maximum Product Subarray
/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
class Solution0152 {
    fun maxProduct(nums: IntArray): Int {
        var max = nums[0]
        var min = nums[0]
        var res = nums[0]
        for (i in 1 until nums.size) {
            if (nums[i] < 0) {
                min = max.also { max = min }
            }
            min = minOf(nums[i], nums[i] * min)
            max = maxOf(nums[i], nums[i] * max)
            res = maxOf(res, max)
        }
        return res
    }
}

fun main() {
    val s = Solution0152()
    println(s.maxProduct(intArrayOf(2, 3, -2, 4)))
    println(s.maxProduct(intArrayOf(-2, 0, -1)))
    println(s.maxProduct(intArrayOf(0, 2)))
    println(s.maxProduct(intArrayOf(0, -2)))
    println(s.maxProduct(intArrayOf(3, -1, 4)))
    println(s.maxProduct(intArrayOf(2, -5, -2, -4, 3)))
}
/*
Runtime: 156 ms, faster than 70.83% of Kotlin online submissions for Maximum Product Subarray.
Memory Usage: 33.7 MB, less than 100.00% of Kotlin online submissions for Maximum Product Subarray.
 */