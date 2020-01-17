import java.util.*

// https://leetcode.com/problems/two-sum/
/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

class Solution0001 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        if (nums.size < 2) {
            return intArrayOf(-1, -1)
        }
        val record = HashMap<Int, Int>()
        for (index in nums.indices) {
            val other = target - nums[index]
            if (record.containsKey(other)) {
                return intArrayOf(record[other]!!, index)
            } else {
                record[nums[index]] = index
            }
        }
        return intArrayOf(-1, -1)
    }
}

fun main() {
    val s = Solution0001()
    val nums = intArrayOf(2, 7, 11, 15)
    val target = 9
    val result = s.twoSum(nums, target)
    println(Arrays.toString(result))
}
/*
Runtime: 136 ms, faster than 100.00% of Kotlin online submissions for Two Sum.
Memory Usage: 43.7 MB, less than 9.09% of Kotlin online submissions for Two Sum.
 */