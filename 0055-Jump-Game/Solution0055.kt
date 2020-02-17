// https://leetcode.com/problems/jump-game/
// 55. Jump Game
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */
class Solution0055 {
    fun canJump(nums: IntArray): Boolean {
        val dp = BooleanArray(nums.size)
        dp[0] = true
        for (i in 0 until nums.size - 1) {
            if (!dp[i]) {
                return false
            }
            for (j in minOf(nums.size - 1, i + nums[i]) downTo i + 1) {
                if (j == nums.size - 1) {
                    return true
                }
                dp[j] = true
            }
        }
        return dp[nums.size - 1]
    }
}

fun main() {
    val s = Solution0055()
    println(s.canJump(intArrayOf(2, 3, 1, 1, 4)))
    println(s.canJump(intArrayOf(3, 2, 1, 0, 4)))
}
/*
Runtime: 356 ms, faster than 27.50% of Kotlin online submissions for Jump Game.
Memory Usage: 39.4 MB, less than 100.00% of Kotlin online submissions for Jump Game.
 */