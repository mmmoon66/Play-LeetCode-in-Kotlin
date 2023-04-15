class Solution0416 {

    private var memo: Array<IntArray> = emptyArray()

    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 == 1) return false
        // 从nums中选取若干个元素放入容量为sum/2的背包中
        val capacity = sum / 2
        memo = Array(nums.size) { IntArray(capacity + 1) { -1 } }
        return tryPartition(nums, nums.size - 1, capacity)
    }


    // 从nums[0..index]范围内取出若干个元素填满容量为sum的背包
    private fun tryPartition(nums: IntArray, index: Int, sum: Int): Boolean {
        if (sum == 0) return true
        if (index < 0 || sum < 0) return false
        if (memo[index][sum] != -1) return memo[index][sum] == 1
        return tryPartition(nums, index - 1, sum) || tryPartition(
            nums,
            index - 1,
            sum - nums[index]
        ).also { memo[index][sum] = if (it) 1 else 0 }
    }
}

class Solution0416V2 {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 == 1) return false
        // 从nums中选取若干个元素放入容量为sum/2的背包中
        val capacity = sum / 2
        val dp = BooleanArray(capacity + 1)
        for (j in 0..capacity) {
            if (j == 0) {
                dp[j] = true
            } else {
                dp[j] = j == nums[0]
            }
        }
        for (i in 1 until nums.size) {
            for (j in capacity downTo 0) {
                if (j == 0) {
                    dp[j] = true
                } else {
                    // 不将nums[i]放入背包 no-op
                    // 考虑将nums[i]放入背包
                    if (j >= nums[i]) {
                        dp[j] = dp[j] || dp[j - nums[i]]
                    }
                }
            }
        }
        return dp[capacity]
    }
}

fun main() {
    val s = Solution0416V2()
    println(s.canPartition(intArrayOf(1, 5, 11, 5)))
    println(s.canPartition(intArrayOf(1, 2, 3, 5)).not())
    println(s.canPartition(intArrayOf(2)).not())
    println(s.canPartition(intArrayOf(0)))
}