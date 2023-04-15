class Solution0300 {
    fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return n
        // dp[i]表示以nums[i]结尾的子序列的最大长度
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

fun main() {
    println(Solution0300().lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)) == 4)
}