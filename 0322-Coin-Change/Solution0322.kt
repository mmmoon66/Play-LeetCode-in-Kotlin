// https://leetcode.com/problems/coin-change/
// 322. Coin Change
/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.
 */
class Solution0322 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        val coinSet = coins.toSet()
        for (i in 1..amount) {
            for (coin in coinSet) {
                if (coin <= i) {
                    dp[i] = minOf(dp[i], 1 + dp[i - coin])
                }
            }
        }
        return if (dp[amount] == amount + 1) -1 else dp[amount]
    }
}

fun main() {
    val s = Solution0322()
    println(s.coinChange(intArrayOf(1, 2, 5), 11))
    println(s.coinChange(intArrayOf(2), 3))
}
/*
Runtime: 216 ms, faster than 37.88% of Kotlin online submissions for Coin Change.
Memory Usage: 37 MB, less than 100.00% of Kotlin online submissions for Coin Change.
 */