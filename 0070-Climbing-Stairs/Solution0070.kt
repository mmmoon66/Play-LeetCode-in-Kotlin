// https://leetcode.com/problems/climbing-stairs/
// 70. Climbing Stairs
/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */
class Solution0070 {
    fun climbStairs(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp.last()
    }
}

fun main() {
    val s = Solution0070()
    println(s.climbStairs(2))
    println(s.climbStairs(3))
}
/*
Runtime: 76 ms, faster than 98.99% of Kotlin online submissions for Climbing Stairs.
Memory Usage: 37.5 MB, less than 100.00% of Kotlin online submissions for Climbing Stairs.
 */