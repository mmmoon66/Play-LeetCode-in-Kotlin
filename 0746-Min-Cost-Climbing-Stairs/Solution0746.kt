// https://leetcode.com/problems/min-cost-climbing-stairs/
// 746. Min Cost Climbing Stairs
/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].
 */
class Solution0746 {
    fun minCostClimbingStairs(cost: IntArray): Int {
        if (cost.size < 2) return 0
        val dp = IntArray(cost.size + 1) // dp[i]达到第i阶台阶的最小花费
        dp[0] = 0
        dp[1] = 0
        for (i in 2..cost.size) {
            dp[i] = minOf(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        }
        return dp.last()
    }
}

fun main() {
    val s = Solution0746()
    println(s.minCostClimbingStairs(intArrayOf(10, 15, 20)))
    println(s.minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)))
}
/*
Runtime: 164 ms, faster than 100.00% of Kotlin online submissions for Min Cost Climbing Stairs.
Memory Usage: 44.1 MB, less than 100.00% of Kotlin online submissions for Min Cost Climbing Stairs.
 */