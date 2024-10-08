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
class Solution0198_v2 {
    fun rob(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        if (n == 1) return nums[0]
        val dp = IntArray(n)//dp[i]表示偷取nums[0..i]范围内的宝物
        dp[0] = nums[0]
        dp[1] = maxOf(nums[0], nums[1])
        for (i in 2 until n) {
            dp[i] = maxOf(dp[i - 1], nums[i] + dp[i - 2])
        }
        return dp.last()
    }
}

fun main() {
    val s = Solution0198_v2()
    println(s.rob(intArrayOf()) == 0)
    println(s.rob(intArrayOf(1)) == 1)
    println(s.rob(intArrayOf(1, 2, 3, 1)) == 4)
    println(s.rob(intArrayOf(2, 7, 9, 3, 1)) == 12)
}
