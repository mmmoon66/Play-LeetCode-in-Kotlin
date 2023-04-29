// https://leetcode.com/problems/4sum/
// 18. 4Sum
/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */
class Solution0018 {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (nums.size < 4) {
            return res
        }
        nums.sort()
        var i = 0
        while(i < nums.size) {
            var j = i + 1
            while(j < nums.size) {
                val t = target - nums[i] - nums[j]
                var l = j + 1
                var r = nums.size - 1
                while(l < r) {
                    val sum = nums[l] + nums[r]
                    if (sum == t) {
                        res.add(listOf(nums[i], nums[j], nums[l], nums[r]))
                        l = nextIndex(nums, l)
                        r = prevIndex(nums, r)
                    } else if (sum < t) {
                        l = nextIndex(nums, l)
                    } else {
                        r = prevIndex(nums, r)
                    }
                }
                j = nextIndex(nums, j)
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
    val s = Solution0018()
    println(s.fourSum(intArrayOf(1, 0, -1, 0, -2, 2), 0))
}
/*
Runtime: 296 ms, faster than 82.76% of Kotlin online submissions for 4Sum.
Memory Usage: 38 MB, less than 100.00% of Kotlin online submissions for 4Sum.
 */