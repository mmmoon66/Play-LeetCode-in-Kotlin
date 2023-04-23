package dp

import TreeNode

// https://leetcode.cn/problems/climbing-stairs/
class Solution70 {
    fun climbStairs(n: Int): Int {
        if (n == 0 || n == 1) return 1
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }
}

// https://leetcode.cn/problems/triangle/
class Solution120 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        // dp[i][j]表示 (i,j) 到达底部的最小值, dp[0][0]就是结果
        val dp = Array(triangle.size) { triangle[it].toIntArray() }
        for (i in dp.size - 2 downTo 0) {
            for (j in 0..i) {
                dp[i][j] += minOf(dp[i + 1][j], dp[i + 1][j + 1])
            }
        }
        return dp[0][0]
    }
}

// https://leetcode.cn/problems/minimum-path-sum/
class Solution64 {
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        if (m == 0) return 0
        val n = grid[0].size
        if (n == 0) return 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                grid[i][j] += minOf(
                    grid[i].getOrNull(j - 1) ?: Int.MAX_VALUE,
                    grid.getOrNull(i - 1)?.getOrNull(j) ?: Int.MAX_VALUE
                ).takeIf { it != Int.MAX_VALUE } ?: 0
            }
        }
        return grid.lastOrNull()?.lastOrNull() ?: 0
    }
}

// https://leetcode.cn/problems/integer-break/
class Solution343 {
    fun integerBreak(n: Int): Int {
        if (n == 0 || n == 1) return 0
        val dp = IntArray(n + 1)
        dp[2] = 1
        for (i in 3..n) {
            for (j in 1 until i) {
                dp[i] = maxOf(dp[i], j * (i - j), j * dp[i - j])
            }
        }
        return dp[n]
    }
}

// https://leetcode.cn/problems/perfect-squares/
class Solution279 {
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { it }
        for (i in 0..n) {
            var j = 1
            while (j * j <= i) {
                dp[i] = minOf(dp[i], 1 + dp[i - j * j])
                ++j
            }
        }
        return dp[n]
    }
}

// https://leetcode.cn/problems/decode-ways/
class Solution91 {
    fun numDecodings(s: String): Int {
        // dp[i]表示s[0..i]范围内数字能够得到的编码数
        if (s.isEmpty()) return 0
        if (s[0] == '0') return 0
        val dp = IntArray(s.length)
        dp[0] = 1
        for (i in 1 until dp.size) {
            if (s[i] != '0') {
                dp[i] += dp[i - 1]
            }
            if (s.substring(i - 1, i + 1).toInt() in 10..26) {
                dp[i] += dp.getOrNull(i - 2) ?: 1
            }
        }
        return dp.last()
    }
}

// https://leetcode.cn/problems/unique-paths/
class Solution62 {
    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0
        val dp = IntArray(n) { 1 }
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[j] += dp[j - 1]
            }
        }
        return dp.last()
    }
}

// https://leetcode.cn/problems/unique-paths-ii/
class Solution63 {
    fun uniquePathsWithObstacles(grid: Array<IntArray>): Int {
        val m = grid.size
        if (m == 0) return 0
        val n = grid[0].size
        if (n == 0) return 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0
                } else {
                    if (i == 0 && j == 0) {
                        grid[i][j] = 1
                    } else {
                        grid[i][j] = (grid.getOrNull(i - 1)?.getOrNull(j) ?: 0) + (grid[i].getOrNull(j - 1) ?: 0)
                    }
                }
            }
        }
        return grid.last().last()
    }
}

// https://leetcode.cn/problems/house-robber/
class Solution198 {
    fun rob(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        if (n == 1) return nums[0]
        // dp[i]表示考虑偷取nums[0..i]范围内房子能得到的最大值
        val dp = IntArray(n)
        dp[0] = nums[0]
        dp[1] = maxOf(nums[0], nums[1])
        for (i in 2 until n) {
            dp[i] = maxOf(
                // 偷取当前位置房子内的财物
                nums[i] + dp[i - 2],
                // 不偷取当前位置房子内的财物
                dp[i - 1]
            )
        }
        return dp.last()
    }
}

// https://leetcode.cn/problems/house-robber-ii/
class Solution213 {
    fun rob(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        if (n == 1) return nums[0]
        return maxOf(rob(nums, 0, n - 2), rob(nums, 1, n - 1))
    }

    private fun rob(nums: IntArray, l: Int, r: Int): Int {
        if (l > r) return 0
        if (l == r) return nums[l]
        var prevMax = nums[l]
        var curMax = maxOf(nums[l], nums[l + 1])
        for (i in l + 2..r) {
            curMax = maxOf(curMax, nums[i] + prevMax).also { prevMax = curMax }
        }
        return curMax
    }
}

// https://leetcode.cn/problems/house-robber-iii/
class Solution337 {

    private val memo = mutableMapOf<TreeNode, Int>()

    fun rob(root: TreeNode?): Int {
        memo.clear()
        return tryRob(root)
    }

    private fun tryRob(node: TreeNode?): Int {
        if (node == null) return 0
        if (memo[node] != null) return memo[node]!!
        return maxOf(
            // 偷取当前节点
            node.`val` + tryRob(node.left?.left) + tryRob(node.left?.right) + tryRob(node.right?.left) + tryRob(node.right?.right),
            // 不偷取当前节点
            tryRob(node.left) + tryRob(node.right)
        ).also { memo[node] = it }
    }
}

// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
class Solution309 {
    fun maxProfit(arr: IntArray): Int {
        // buy[i]表示第i天以买入结束获取的最大收益
        // sell[i]表示第i天以卖出结束获取的最大收益
        // rest[i]表示第i天以休息结束获取的最大收益
        // buy[i] = maxOf(buy[i-1], rest[i-1]-arr[i])         (1)
        // sell[i] = maxOf(sell[i-1], buy[i-1]+arr[i])        (2)
        // rest[i] = maxOf(rest[i-1], sell[i-1], buy[i-1])    (3)
        // 又有:
        // rest[i] >= buy[i]                                  (4)
        // rest[i] <= sell[i]                                 (5)
        // 由(3)(4)(5)得到: rest[i] = sell[i-1]                (6)
        // 将(6)代入(1)(2)得到
        // buy[i] = maxOf(buy[i-1], sell[i-2]-arr[i])
        // sell[i] = maxOf(sell[i-1], buy[i-1]+arr[i])
        val n = arr.size
        if (n == 0 || n == 1) return 0
        val buy = IntArray(n)
        val sell = IntArray(n)
        buy[0] = -arr[0]
        buy[1] = maxOf(-arr[0], -arr[1])
        sell[0] = 0
        sell[1] = maxOf(0, arr[1] - arr[0])
        for (i in 2 until n) {
            buy[i] = maxOf(buy[i - 1], sell[i - 2] - arr[i])
            sell[i] = maxOf(sell[i - 1], buy[i - 1] + arr[i])
        }
        return sell.last()
    }
}

class Knapsack01V1 {
    private var memo: Array<IntArray> = emptyArray()

    fun knapsack01(weight: IntArray, value: IntArray, capacity: Int): Int {
        assert(weight.size == value.size)
        val n = weight.size
        memo = Array(n) { IntArray(capacity + 1) { -1 } }
        return bestValue(weight, value, n - 1, capacity)
    }

    // 使用[0..index]范围内的物品填充容量为capacity的背包，返回最大价值
    private fun bestValue(weight: IntArray, value: IntArray, index: Int, capacity: Int): Int {
        if (index < 0 || capacity == 0) return 0
        if (memo[index][capacity] != -1) return memo[index][capacity]
        // 不将index位置的物品加入背包
        var res = bestValue(weight, value, index - 1, capacity)
        // 考虑将index位置的物品加入背包
        if (capacity >= weight[index]) {
            res = maxOf(res, value[index] + bestValue(weight, value, index - 1, capacity - weight[index]))
        }
        memo[index][capacity] = res
        return res
    }
}

class Knapsack01V2 {
    fun knapsack01(weight: IntArray, value: IntArray, capacity: Int): Int {
        assert(weight.size == value.size)
        val n = weight.size
        if (n == 0 || capacity == 0) return 0
        val dp = Array(n) { IntArray(capacity + 1) }
        // 第一行特殊处理
        for (j in 0..capacity) {
            dp[0][j] = if (j >= weight[0]) value[0] else 0
        }
        for (i in 1 until n) {
            for (j in 0..capacity) {
                // 不将i位置处的物品放入背包
                dp[i][j] = dp[i - 1][j]
                // 考虑将i位置的物品放入背包
                if (j >= weight[i]) {
                    dp[i][j] = maxOf(dp[i][j], value[i] + dp[i - 1][j - weight[i]])
                }
            }
        }
        return dp[n - 1][capacity]
    }
}

class Knapsack01V3 {
    fun knapsack01(weight: IntArray, value: IntArray, capacity: Int): Int {
        assert(weight.size == value.size)
        val n = weight.size
        if (n == 0 || capacity == 0) return 0
        val dp = Array(2) { IntArray(capacity + 1) }
        // 第一行特殊处理
        for (j in 0..capacity) {
            dp[0][j] = if (j >= weight[0]) value[0] else 0
        }
        for (i in 1 until n) {
            for (j in 0..capacity) {
                dp[i % 2][j] = dp[(i - 1) % 2][j]
                if (j >= weight[i]) {
                    dp[i % 2][j] = maxOf(dp[i % 2][j], value[i] + dp[(i - 1) % 2][j - weight[i]])
                }
            }
        }
        return dp[(n - 1) % 2][capacity]
    }
}

class Knapsack01V4 {
    fun knapsack01(weight: IntArray, value: IntArray, capacity: Int): Int {
        assert(weight.size == value.size)
        val n = weight.size
        if (n == 0 || capacity == 0) return 0
        val dp = IntArray(capacity + 1)
        // 第一行特殊处理
        for (j in 0..capacity) {
            dp[j] = if (j >= weight[0]) value[0] else 0
        }
        for (i in 1 until n) {
            for (j in capacity downTo weight[i]) {
                dp[j] = maxOf(dp[j], value[i] + dp[j - weight[i]])
            }
        }
        return dp[capacity]
    }
}

// https://leetcode.cn/problems/partition-equal-subset-sum/
class Solution416 {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 == 1) return false
        // 问题转化为选取若干个物品恰好填满容量为sum/2的背包
        val capacity = sum / 2
        val dp = BooleanArray(capacity + 1)
        // 第一行特殊处理
        for (j in 0..capacity) {
            dp[j] = nums[0] == j
        }
        for (i in 1 until nums.size) {
            for (j in capacity downTo nums[i]) {
                dp[j] = dp[j] || dp[j - nums[i]]
            }
        }
        return dp[capacity]
    }
}

class Solution322 {
    private var upper = 0
    private var memo: Array<IntArray> = emptyArray()
    fun coinChange(coins: IntArray, amount: Int): Int {
        upper = amount + 1
        memo = Array(coins.size) { IntArray(amount + 1) { -1 } }
        return tryChangeCoin(coins, coins.size - 1, amount).takeIf { it <= amount } ?: -1
    }

    // 尝试使用coins[0..index]兑换
    private fun tryChangeCoin(coins: IntArray, index: Int, amount: Int): Int {
        if (amount == 0) return 0
        if (index < 0 || amount < 0) return upper
        if (memo[index][amount] != -1) return memo[index][amount]
        return minOf(
            tryChangeCoin(coins, index - 1, amount),
            1 + tryChangeCoin(coins, index, amount - coins[index])
        ).also { memo[index][amount] = it }
    }
}

class Solution322V2 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0 //注意边界条件
        for (coin in coins.toSet()) {
            // 注意：物品可重复选取的话，二层循环应该从前向后遍历
            for (j in coin..amount) {
                dp[j] = minOf(dp[j], 1 + dp[j - coin])
            }
        }
        return dp[amount].takeIf { it <= amount } ?: -1
    }
}

// https://leetcode.cn/problems/combination-sum-iv/
class Solution377 {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        // dp[i]表示使用nums中元素组成和为i的组合的数量
        val dp = IntArray(target + 1)
        dp[0] = 1
        for (i in 1..target) {
            for (num in nums) {
                if (i >= num) dp[i] += dp[i - num]
            }
        }
        return dp[target]
    }
}

// https://leetcode.cn/problems/ones-and-zeroes/
class Solution474 {

    private var memo: Array<Array<IntArray>> = emptyArray()
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        memo = Array(strs.size) { Array(m + 1) { IntArray(n + 1) { -1 } } }
        val freq: Map<String, Pair<Int, Int>> = strs.associateWith { str ->
            val zero = str.count { it == '0' }
            val one = str.length - zero
            (zero to one)
        }
        return helper(strs, strs.size - 1, m, n, freq)
    }


    // 使用strs[0..index]范围内元素组成至多m个0、n个1的subset,返回最多元素数
    private fun helper(strs: Array<String>, index: Int, m: Int, n: Int, freq: Map<String, Pair<Int, Int>>): Int {
        if (m < 0 || n < 0) return Int.MIN_VALUE
        if (m == 0 && n == 0) return 0
        if (index < 0) return 0
        if (memo[index][m][n] != -1) return memo[index][m][n]
        var res = 0
        // 不选取strs[index]
        res = maxOf(res, helper(strs, index - 1, m, n, freq))
        // 选取strs[index]
        val (zero, one) = freq[strs[index]]!!
        res = maxOf(res, 1 + helper(strs, index - 1, m - zero, n - one, freq))
        memo[index][m][n] = res
        return res
    }
}

// https://leetcode.cn/problems/word-break/
class Solution139 {
    private var memo = intArrayOf()
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        memo = IntArray(s.length) { -1 }
        return tryBreakWord(s, 0, wordDict)
    }

    // 尝试使用wordDict中的元素组成 s[index..length)
    private fun tryBreakWord(s: String, index: Int, wordDict: List<String>): Boolean {
        if (index == s.length) return true
        if (index > s.length) return false
        if (memo[index] != -1) return memo[index] == 1
        var res = false
        for (word in wordDict) {
            if (s.substring(index).startsWith(word) && tryBreakWord(s, index + word.length, wordDict)) {
                res = true
                break
            }
        }
        memo[index] = if (res) 1 else 0
        return res
    }
}

class Solution139V2 {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length
        if (n == 0) return false
        // dp[i]表示s[0..i)是否使用wordDict中的字符传组成
        val dp = BooleanArray(n + 1)
        dp[0] = true
        val chars = s.toCharArray()
        for (i in 0..n) {
            for (word in wordDict) {
                if (i >= word.length && endsWith(chars, i, word)) {
                    if (dp[i - word.length]) {
                        dp[i] = true
                        break
                    }
                }
            }
        }
        return dp[n]
    }

    // chars[0..index)是否以字符串s结尾
    private fun endsWith(chars: CharArray, index: Int, s: String): Boolean {
        if (s.length > index) return false
        var i = index - 1
        var j = s.length - 1
        while (j >= 0) {
            if (chars[i] != s[j]) return false
            --i
            --j
        }
        return true
    }
}

// https://leetcode.cn/problems/target-sum/
class Solution494 {
    private var memo: Array<IntArray> = emptyArray()

    // 所有加正号的元素和为pos,所有加负号的元素和为neg,则有pos-neg=target,又有pos+neg=sum
    // 两式相减则有2*neg=sum-target
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        if (sum - target < 0 || (sum - target) % 2 == 1) return 0
        val capacity = (sum - target) / 2
        memo = Array(nums.size + 1) { IntArray(capacity + 1) { -1 } }
        return targetSum(nums, nums.size - 1, capacity)
    }

    // 从nums[0..index]中选取若干个元素，使得它们的和等于capacity, 返回选取方式的数量
    private fun targetSum(nums: IntArray, index: Int, capacity: Int): Int {
        if (capacity < 0) return 0
        if (index < 0) return if (capacity == 0) 1 else 0
        if (memo[index][capacity] != -1) return memo[index][capacity]
        val res = targetSum(nums, index - 1, capacity) + targetSum(nums, index - 1, capacity - nums[index])
        memo[index][capacity] = res
        return res
    }
}

class Solution494V2 {
    private var memo: Array<IntArray> = emptyArray()
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        if (sum + target < 0 || (sum + target) % 2 == 1) return 0
        val capacity = (sum + target) / 2
        memo = Array(nums.size) { IntArray(capacity + 1) { -1 } }
        return targetSum(nums, nums.size - 1, capacity)
    }

    private fun targetSum(nums: IntArray, index: Int, capacity: Int): Int {
        if (capacity < 0) return 0
        if (index < 0) return if (capacity == 0) 1 else 0
        if (memo[index][capacity] != -1) return memo[index][capacity]
        return (targetSum(nums, index - 1, capacity) + targetSum(
            nums,
            index - 1,
            capacity - nums[index]
        )).also { memo[index][capacity] = it }
    }
}

class Solution494V3 {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        if (sum - target < 0 || (sum - target) % 2 == 1) return 0
        val n = nums.size
        val capacity = (sum - target) / 2
        // dp[i][j]表示使用nums[0..i)范围内元素填充容量为j的背包
        val dp = Array(n + 1) { IntArray(capacity + 1) }
        dp[0][0] = 1
        for (i in 1..n) {
            for (j in 0..capacity) {
                dp[i][j] += dp[i - 1][j]
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]]
                }
            }
        }
        return dp[n][capacity]
    }
}

class Solution494V4 {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        if (sum - target < 0 || (sum - target) % 2 == 1) return 0
        val n = nums.size
        val capacity = (sum - target) / 2
        // dp[j]表示使用nums[0..i)范围内元素填充容量为j的背包
        val dp = IntArray(capacity + 1)
        dp[0] = 1
        for (i in 1..n) {
            // 不可重复选取则从后向前遍历
            for (j in capacity downTo nums[i - 1]) {
                dp[j] += dp[j - nums[i - 1]]
            }
        }
        return dp[capacity]
    }
}

// https://leetcode.cn/problems/longest-increasing-subsequence/
class Solution300 {
    fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        // dp[i]表示以nums[i]结尾的最长上升子序列
        val dp = IntArray(n) { 1 }
        for (i in 1 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
        }
        var res = 0
        dp.forEach { res = maxOf(res, it) }
        return res
    }
}

// https://leetcode.cn/problems/wiggle-subsequence/
class Solution376 {
    fun wiggleMaxLength(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        // up[i]表示以nums[i]结尾的上摆子序列
        val up = IntArray(n) { 1 }
        // down[i]表示以nums[i]结尾的下摆子序列
        val down = IntArray(n) { 1 }
        var res = 0
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    up[i] = maxOf(up[i], 1 + down[j])
                }
                if (nums[i] < nums[j]) {
                    down[i] = maxOf(down[i], 1 + up[j])
                }
            }
            res = maxOf(res, up[i], down[i])
        }
        return res
    }
}

// https://leetcode.cn/problems/longest-common-subsequence/
class Solution1143 {
    private var memo: Array<IntArray> = emptyArray()
    fun longestCommonSubsequence(a: String, b: String): Int {
        val m = a.length
        if (m == 0) return 0
        val n = b.length
        if (n == 0) return 0
        memo = Array(m) { IntArray(n) { -1 } }
        return lcs(a, b, m - 1, n - 1)
    }

    // a[0..m]与b[0..n]的最长公共子序列
    private fun lcs(a: String, b: String, m: Int, n: Int): Int {
        if (m < 0 || n < 0) return 0
        if (memo[m][n] != -1) return memo[m][n]
        val res = if (a[m] == b[n]) {
            1 + lcs(a, b, m - 1, n - 1)
        } else {
            maxOf(lcs(a, b, m - 1, n), lcs(a, b, m, n - 1))
        }
        memo[m][n] = res
        return res
    }
}

class Solution1143V2 {
    fun longestCommonSubsequence(a: String, b: String): Int {
        val m = a.length
        if (m == 0) return 0
        val n = b.length
        if (n == 0) return 0
        // dp[i][j]表示a[0..m)和b[0..n)的最长公共子序列的长度
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            for (j in 1..n) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[m][n]
    }
}

fun main() {
//    val weight = intArrayOf(1, 2, 3)
//    val value = intArrayOf(6, 10, 12)
//    val capacity = 5
//    val s = Knapsack01V4()
//    println(s.knapsack01(weight, value, capacity) == 22)

//    val s = Solution474()
//    println(s.findMaxForm(arrayOf("10", "0001", "111001", "1", "0"), 5, 3) == 4)

//    val s = Solution139V2()
//    println(s.wordBreak("leetcode", listOf("leet", "code")) == true)
//    println(s.wordBreak("applepenapple", listOf("apple", "pen")) == true)

    val s = Solution494V3()
    println(s.findTargetSumWays(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1), 1) == 256)
    println(s.findTargetSumWays(intArrayOf(1, 1, 1, 1, 1), 3) == 5)
}