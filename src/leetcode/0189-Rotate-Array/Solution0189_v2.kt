import java.util.*

// https://leetcode.com/problems/rotate-array/
// 189. Rotate Array
/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?


Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:
1 <= nums.length <= 2 * 10^4
It's guaranteed that nums[i] fits in a 32 bit-signed integer.
k >= 0
 */
class Solution0189_v2 {
    fun rotate(nums: IntArray, k: Int): Unit {
        val n = nums.size
        if (n == 0) return
        val r = k % n
        if (r == 0) return

        var start = 0
        var cur = start
        var t = nums[cur]
        for (i in 0 until n) {
            val next = (cur + k) % n
            val temp = nums[next]
            nums[next] = t
            cur = next
            t = temp
            if (cur == start) {
                ++start
                cur = start
                t = nums[cur]
            }
        }
    }
}

fun main() {
    val s = Solution0189_v2()
    val nums1 = intArrayOf(1, 2, 3, 4, 5, 6)
    s.rotate(nums1, 3)
    println(nums1.contentToString())
}
/*
Runtime: 168 ms, faster than 100.00% of Kotlin online submissions for Rotate Array.
Memory Usage: 35 MB, less than 84.28% of Kotlin online submissions for Rotate Array.
 */