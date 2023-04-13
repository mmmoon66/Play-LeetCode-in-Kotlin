// https://leetcode.com/problems/trapping-rain-water/
// 42. Trapping Rain Water
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
class Solution0042 {
    fun trap(height: IntArray): Int {
        val n = height.size
        // leftMax[i]代表height[0..i]区间内元素的最大值
        val leftMax = IntArray(n)
        for (i in 0 until n) {
            leftMax[i] = maxOf(height[i], leftMax.getOrNull(i - 1) ?: 0)
        }
        // rightMax[i]表示height[i..n-1]区间内元素的最大值
        val rightMax = IntArray(n)
        for (i in n - 1 downTo 0) {
            rightMax[i] = maxOf(height[i], rightMax.getOrNull(i + 1) ?: 0)
        }
        return height.mapIndexed { index, h -> minOf(leftMax[index], rightMax[index]) - h }.sum()
    }
}

fun main() {
    val s = Solution0042()
    println(s.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)) == 6)
}
/*
Runtime: 176 ms, faster than 92.86% of Kotlin online submissions for Trapping Rain Water.
Memory Usage: 33.9 MB, less than 85.71% of Kotlin online submissions for Trapping Rain Water.
 */