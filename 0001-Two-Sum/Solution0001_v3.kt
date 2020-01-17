import java.util.*
import kotlin.collections.HashMap

// https://leetcode.com/problems/two-sum/
/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

class Solution0001_v3 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val record = HashMap<Int, Int>()
        nums.forEachIndexed { index, value ->
            record[target - value]?.let {
                return intArrayOf(it, index)
            }
            record[value] = index
        }
        return intArrayOf()
    }
}

fun main() {
    val s = Solution0001_v3()
    val nums = intArrayOf(2, 7, 11, 15)
    val target = 9
    val result = s.twoSum(nums, target)
    println(Arrays.toString(result))
}
/*
Runtime: 156 ms, faster than 100.00% of Kotlin online submissions for Two Sum.
Memory Usage: 42 MB, less than 9.09% of Kotlin online submissions for Two Sum.
 */