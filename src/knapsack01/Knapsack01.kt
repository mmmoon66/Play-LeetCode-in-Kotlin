package knapsack01

class Knapsack01 {
    private var memo: Array<IntArray> = emptyArray()

    // 选取若干个物品放入容量为capacity的背包中的最大价值
    fun knapsack01(weight: IntArray, value: IntArray, capacity: Int): Int {
        assert(weight.size == value.size)
        val n = weight.size
        if (n == 0) return 0
        memo = Array(n) { IntArray(capacity + 1) { -1 } }
        return bestValue(weight, value, n - 1, capacity)
    }

    // 使用[0..index]范围内物品填充背包
    private fun bestValue(weight: IntArray, value: IntArray, index: Int, capacity: Int): Int {
        if (index < 0 || capacity == 0) return 0
        if (memo[index][capacity] != -1) return memo[index][capacity]
        // 不将index位置物品放入背包
        var res = bestValue(weight, value, index - 1, capacity)

        // 尝试将index位置物品放入背包
        if (weight[index] <= capacity) {
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
        if (n == 0) return 0
        // dp[i][j]表示使用[0..i]范围内的物品填充容量为j的背包的最大价值
        val dp = Array(n) { IntArray(capacity + 1) }
        // 特殊处理第一行
        for (j in 0..capacity) {
            if (weight[0] <= j) {
                dp[0][j] = value[0]
            }
        }
        for (i in 1 until n) {
            for (j in 1..capacity) {
                // 不将第i个物品放入背包
                dp[i][j] = dp[i - 1][j]
                // 考虑将第i个物品放入背包
                if (weight[i] <= j) {
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
        if (n == 0) return 0
        val dp = Array(2) { IntArray(capacity + 1) }
        // 第一行特殊处理
        for (j in 0..capacity) {
            if (j >= weight[0]) {
                dp[0][j] = value[0]
            }
        }
        for (i in 1 until n) {
            for (j in 1..capacity) {
                // 不将位置物品放入背包
                dp[i % 2][j] = dp[(i - 1) % 2][j]
                //考虑将i位置物品放入背包
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
        if (n == 0) return 0
//        val dp = IntArray(capacity + 1)
//        //只考虑第一个物品
//        for (j in 0..capacity) {
//            if (j >= weight[0]) {
//                dp[j] = value[0]
//            }
//        }
        val dp = IntArray(capacity + 1) { c -> if (c >= weight[0]) value[0] else 0 }
        for (i in 1 until n) {
            for (j in capacity downTo 1) {
                //不将i位置物品放入背包
                // no-op
                //考虑将i位置物品放入背包
                if (weight[i] <= j) {
                    dp[j] = maxOf(dp[j], value[i] + dp[j - weight[i]])
                }
            }
        }
        return dp[capacity]
    }
}

fun main() {
    val weight = intArrayOf(1, 2, 3)
    val value = intArrayOf(6, 10, 12)
    val capacity = 5
    val s = Knapsack01V4()
    println(s.knapsack01(weight, value, capacity) == 22)
}