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
class Solution0189 {
    fun rotate(nums: IntArray, k: Int): Unit {
        if (nums.isEmpty()) return
        val r = k % nums.size
        if (r == 0) return
        for (i in 0 until r) {
            val last = nums.last()
            var j = nums.size - 1
            while(j - 1 >= 0) {
                nums[j] = nums[j - 1]
                --j
            }
            nums[0] = last
        }
    }
}

fun main() {
    val s = Solution0189()
    val nums1 = intArrayOf(-1, -100, 3, 99)
    s.rotate(nums1, 2)
    println(nums1.contentToString())
}
/*
Runtime: 352 ms, faster than 33.96% of Kotlin online submissions for Rotate Array.
Memory Usage: 37.4 MB, less than 40.88% of Kotlin online submissions for Rotate Array.
 */