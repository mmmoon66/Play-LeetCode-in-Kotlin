// https://leetcode.com/problems/target-sum/
// 494. Target Sum
/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Constraints:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
class Solution0494 {
    // a1 - a2 + ... + an = S
    // 将前面加-号的元素移到等号右侧
    // a1 + ... + an = S + a2 + ...
    // 等号两侧都加上前面加-号的元素
    // a1 + a2 + ... + an = S + 2(a2 + ...)
    // sum = S + 2(a2 + ...)
    // 问题转化为从[a1...an]中取若干个元素，使得它们的和等于 (sum - S) / 2
    fun findTargetSumWays(nums: IntArray, S: Int): Int {
        val sum = nums.fold(0) { acc, i -> acc + i }
        if (sum - S < 0 || (sum - S) % 2 != 0) {
            return 0
        }
        val C = (sum - S) / 2

        // dp[i]表示完全填满容量为i的背包的填充方法数
        val dp = IntArray(C + 1)

        // 只考虑 nums[0]
        dp[0] = 1 // 不放入任何元素
        for (j in 0..C) {
            dp[j] += if (nums[0] == j) 1 else 0
        }

        for (i in 1 until nums.size) {
            for (j in C..0) {
                // 将 nums[i] 加入背包中，剩余容量 j - nums[i]
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]]
                }
            }
        }
        return dp[C]
    }
}

fun main() {
    val s = Solution0494()
    println(s.findTargetSumWays(intArrayOf(1, 1, 1, 1, 1), 3))
}
/*
Runtime: 184 ms, faster than 100.00% of Kotlin online submissions for Target Sum.
Memory Usage: 33.9 MB, less than 42.86% of Kotlin online submissions for Target Sum.
 */