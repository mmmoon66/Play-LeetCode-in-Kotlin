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
        if (n <= 2) return 0
        // leftMax[i] 表示 height[0..i] 区间内的最大值
        val leftMax = IntArray(n)
        // rightMax[i] 表示 height[i..n-1] 区间内的最大值
        val rightMax = IntArray(n)

        var i = 0
        var max = 0
        while (i < n) {
            max = maxOf(max, height[i])
            leftMax[i] = max
            ++i
        }

        i = n - 1
        max = 0
        while (i >= 0) {
            max = maxOf(max, height[i])
            rightMax[i] = max
            --i
        }

        var sum = 0
        height.forEachIndexed { index, h ->
            if (leftMax[index] > h && rightMax[index] > h) {
                sum += minOf(leftMax[index], rightMax[index]) - h
            }
        }
        return sum
    }
}

fun main() {
    val s = Solution0042()
    println(s.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
}
/*
Runtime: 176 ms, faster than 92.86% of Kotlin online submissions for Trapping Rain Water.
Memory Usage: 33.9 MB, less than 85.71% of Kotlin online submissions for Trapping Rain Water.
 */