// https://leetcode.com/problems/3sum/
// 15. 3Sum
/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
class Solution0015 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (nums.size < 3) {
            return res
        }
        nums.sort()
        var i = 0
        while(i < nums.size) {
            val target = -nums[i]
            var l = i + 1
            var r = nums.size - 1
            while(l < r) {
                val sum = nums[l] + nums[r]
                if (sum == target) {
                    res.add(listOf(nums[i], nums[l], nums[r]))
                    l = nextIndex(nums, l)
                    r = prevIndex(nums, r)
                } else if (sum < target) {
                    l = nextIndex(nums, l)
                } else {
                    r = prevIndex(nums, r)
                }
            }
            i = nextIndex(nums, i)
        }
        return res
    }

    private fun nextIndex(nums: IntArray, index: Int): Int {
        for (i in index + 1 until nums.size) {
            if (nums[i] != nums[index]) {
                return i
            }
        }
        return nums.size
    }

    private fun prevIndex(nums: IntArray, index: Int): Int {
        for (i in index - 1 downTo 0) {
            if (nums[i] != nums[index]) {
                return i
            }
        }
        return -1
    }
}

fun main() {
    val s = Solution0015()
    println(s.threeSum(intArrayOf(-1, 0, 1, 2, -1, 4)))
    println(s.threeSum(intArrayOf(0, 0, 0, 0, 0)))
    println(s.threeSum(intArrayOf(-2, 0, 0, 2, 2)))
}
/*
Runtime: 376 ms, faster than 81.87% of Kotlin online submissions for 3Sum.
Memory Usage: 47.7 MB, less than 100.00% of Kotlin online submissions for 3Sum.
 */