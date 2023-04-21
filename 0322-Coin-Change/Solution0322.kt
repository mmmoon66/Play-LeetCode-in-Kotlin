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
    private var upper = 0
    private var memo: Array<IntArray> = emptyArray()
    fun coinChange(coins: IntArray, amount: Int): Int {
        upper = amount + 1
        memo = Array(coins.size) { IntArray(amount + 1) { -1 } }
        return tryChangeCoin(coins, coins.size - 1, amount).takeIf { it != upper } ?: -1
    }

    // 使用coins[0..index]范围内面额的硬币凑齐amount，返回使用的最少硬币数
    private fun tryChangeCoin(coins: IntArray, index: Int, amount: Int): Int {
        if (amount == 0) return 0
        if (amount < 0 || index < 0) return upper
        if (memo[index][amount] != -1) return memo[index][amount]
        return minOf(
            // 不使用coins[index]
            tryChangeCoin(coins, index - 1, amount),
            // 使用coins[index]
            1 + tryChangeCoin(coins, index, amount - coins[index])
        ).also { memo[index][amount] = it }
    }
}

class Solution0322V4 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        if (amount == 0) return 0
        if (coins.isEmpty()) return -1
        val upper = amount + 1
        // dp[i][j]表示使用coins[0..i]范围内面额的硬币凑齐j的最少硬币数量
        val dp = Array(coins.size) { IntArray(amount + 1) { upper } }
        // 第一行特殊处理
        for (j in 0..amount) {
            if (j == 0) {
                dp[0][j] = 0
            } else {
                dp[0][j] = if (j % coins[0] == 0) j / coins[0] else upper
            }
        }
        for (i in 1 until coins.size) {
            dp[i][0] = 0
            for (j in 1..amount) {
                // 不使用coins[i]面额的硬币
                dp[i][j] = dp[i - 1][j]
                if (coins[i] <= j) {
                    // 使用coins[i]面额的硬币
                    dp[i][j] = minOf(dp[i][j], 1 + dp[i][j - coins[i]])
                }
            }
        }
        return dp.last().last().takeIf { it <= amount } ?: -1
    }
}
/*
执行用时：232 ms, 在所有 Kotlin 提交中击败了22.99%的用户
内存消耗：36.7 MB, 在所有 Kotlin 提交中击败了8.05%的用户
 */

class Solution0322V5 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        if (amount == 0) return 0
        if (coins.isEmpty()) return -1
        val upper = amount + 1
        val dp = IntArray(amount + 1) { upper }
        dp[0] = 0
        // 第一行特殊处理
        for (j in 1..amount) {
            dp[j] = if (j % coins[0] == 0) j / coins[0] else upper
        }
        for (i in 1 until coins.size) {
            for (j in 1..amount) {
                if (j >= coins[i]) {
                    dp[j] = minOf(dp[j], 1 + dp[j - coins[i]])
                }
            }
        }
        return dp.last().takeIf { it <= amount } ?: -1
    }
}
/*
执行用时：180 ms, 在所有 Kotlin 提交中击败了59.77%的用户
内存消耗：35.5 MB, 在所有 Kotlin 提交中击败了18.39%的用户
 */

// 基于Solution0322V5,不对第一行做特殊处理
// 经过各种简化，最终得到V2版本的代码
class Solution0322V6 {
    fun coinChange(coins: IntArray, amount: Int): Int {
//        if (amount == 0) return 0
//        if (coins.isEmpty()) return -1
        val upper = amount + 1
        val dp = IntArray(amount + 1) { upper }
        dp[0] = 0
        for (coin in coins.toSet()) {
//            for (j in 1..amount) {
//                if (j >= coin) {
//                    dp[j] = minOf(dp[j], 1 + dp[j - coin])
//                }
//            }
            for (j in coin..amount) {
                dp[j] = minOf(dp[j], 1 + dp[j - coin])
            }
        }
        return dp.last().takeIf { it <= amount } ?: -1
    }
}

fun main() {
    val s = Solution0322V4()
    println(s.coinChange(intArrayOf(1, 2, 5), 11) == 3)
    println(s.coinChange(intArrayOf(2), 3) == -1)
    println(s.coinChange(intArrayOf(), 1) == -1)
    println(s.coinChange(intArrayOf(), 0) == 0)
}
/*
Runtime: 216 ms, faster than 37.88% of Kotlin online submissions for Coin Change.
Memory Usage: 37 MB, less than 100.00% of Kotlin online submissions for Coin Change.
 */