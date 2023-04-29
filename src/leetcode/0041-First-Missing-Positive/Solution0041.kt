// https://leetcode.com/problems/first-missing-positive/
// 41. First Missing Positive
/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1

Note:
Your algorithm should run in O(n) time and uses constant extra space.
 */

class Solution0041 {
    fun firstMissingPositive(nums: IntArray): Int {
        var res = 1
        nums.sort()
        for (num in nums) {
            if (num == res) {
                ++res
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0041()
    println(s.firstMissingPositive(intArrayOf(1, 2, 0)))
    println(s.firstMissingPositive(intArrayOf(3, 4, -1, 1)))
    println(s.firstMissingPositive(intArrayOf(7, 8,9, 11, 12)))
}
/*
Runtime: 192 ms, faster than 41.30% of Kotlin online submissions for First Missing Positive.
Memory Usage: 34.5 MB, less than 25.00% of Kotlin online submissions for First Missing Positive.
 */