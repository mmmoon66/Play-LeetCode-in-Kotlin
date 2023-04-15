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
        // dp[i]表示用coins凑齐总额为i的最少硬币数
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        for (i in 1..amount) {
            for (coin in coins.toSet()) {
                if (coin <= i) {
                    // 使用面额为coin的硬币
                    dp[i] = minOf(dp[i], 1 + dp[i - coin])
                } else {
                    // dp[i] = dp[i]//不使用面额为coin的硬币
                }
            }
        }
        return dp[amount].takeIf { it <= amount } ?: -1
    }
}

class Solution0322V2 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        for (coin in coins.toSet()) {
            for (i in coin..amount) {
                dp[i] = minOf(dp[i], 1 + dp[i - coin])
            }
        }
        return if (dp[amount] == amount + 1) -1 else dp[amount]
    }
}

class Solution0322V3 {
    private var memo: Array<IntArray> = emptyArray()
    private var exceedMax = 0
    fun coinChange(coins: IntArray, amount: Int): Int {
        val n = coins.size
        exceedMax = amount + 1
        memo = Array(n) { IntArray(amount + 1) { -1 } }
        return tryChangeCoin(coins, coins.size - 1, amount).takeIf { it <= amount } ?: -1
    }

    // 使用coins[0..index]范围内元素填充amount的最少元素数量
    private fun tryChangeCoin(coins: IntArray, index: Int, amount: Int): Int {
        if (amount == 0) return 0
        if (index < 0 || amount < 0) return exceedMax
        if (memo[index][amount] != -1) return memo[index][amount]
        return minOf(
            tryChangeCoin(coins, index - 1, amount),//不使用coins[i]面额的硬币
            1 + tryChangeCoin(coins, index, amount - coins[index])//使用coins[i]面额的硬币
        ).also { memo[index][amount] = it }
    }
}

fun main() {
    val s = Solution0322()
    println(s.coinChange(intArrayOf(1, 2, 5), 11) == 3)
    println(s.coinChange(intArrayOf(2), 3) == -1)
    println(s.coinChange(intArrayOf(), 1) == -1)
    println(s.coinChange(intArrayOf(), 0) == 0)
}
/*
Runtime: 216 ms, faster than 37.88% of Kotlin online submissions for Coin Change.
Memory Usage: 37 MB, less than 100.00% of Kotlin online submissions for Coin Change.
 */