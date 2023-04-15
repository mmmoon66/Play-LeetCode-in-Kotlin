// https://leetcode.com/problems/house-robber/
// 198. House Robber
/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
 */
class Solution0198 {
    private var memo = intArrayOf()

    fun rob(nums: IntArray): Int {
        memo = IntArray(nums.size) { -1 }
        return tryRob(nums, nums.lastIndex)
    }

    // 尝试在nums[0..index]范围内偷取宝物所能偷取的最大值
    private fun tryRob(nums: IntArray, index: Int): Int {
        if (index < 0) return 0
        if (index == 0) return nums[0]
        if (memo[index] != -1) return memo[index]
        return maxOf(
            nums[index] + tryRob(nums, index - 2),//偷取index位置处的宝物
            tryRob(nums, index - 1)//不偷取index位置处的宝物
        ).also { memo[index] = it }
    }
}

fun main() {
    val s = Solution0198()
    println(s.rob(intArrayOf()))
    println(s.rob(intArrayOf(1)))
    println(s.rob(intArrayOf(1, 2, 3, 1)))
    println(s.rob(intArrayOf(2, 7, 9, 3, 1)))
}
