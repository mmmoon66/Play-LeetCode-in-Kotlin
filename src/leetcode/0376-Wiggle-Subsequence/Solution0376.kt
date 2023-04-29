class Solution0376 {
    fun wiggleMaxLength(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return n
        // dp[i]表示以nums[i]结尾的wiggle subsequence的最大长度
        // 其中dp[i][0]是以nums[i]结尾，并且前一个元素比nums[i]小的结果
        // dp[i][1]是以nums[i]结尾，并且前一个元素比nums[i]大的结果
        val dp = Array(n) { intArrayOf(1, 1) }
        for (i in 1 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i][0] = maxOf(dp[i][0], 1 + dp[j][1])
                } else if (nums[i] < nums[j]) {
                    dp[i][1] = maxOf(dp[i][1], 1 + dp[j][0])
                }
            }
        }
        var res = 0
        dp.forEach { res = maxOf(res, *it) }
        return res
    }
}

class Solution0376V2 {
    fun wiggleMaxLength(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return n
        val up = IntArray(n) { 1 }// up[i]表示以nums[0..i]范围内某个元素结尾的上升摆动序列的最大长度
        val down = IntArray(n) { 1 }// down[i]表示以nums[0..i]范围内某个元素结尾的下降摆动序列的最大长度
        // up[i] = maxOf(up[i-1], 1+down[i-1])
        // down[i] = maxOf(down[i-1], 1+up[i-1])
        for (i in 1 until n) {
            if (nums[i] > nums[i - 1]) {
                up[i] = maxOf(up[i - 1], 1 + down[i - 1])
            } else {
                up[i] = up[i - 1]
            }

            if (nums[i] < nums[i - 1]) {
                down[i] = maxOf(down[i - 1], 1 + up[i - 1])
            } else {
                down[i] = down[i - 1]
            }
        }
        return maxOf(up[n - 1], down[n - 1])
    }
}

fun main() {
    val s = Solution0376V2()
    println(s.wiggleMaxLength(intArrayOf(1, 7, 4, 9, 2, 5)) == 6)
    println(s.wiggleMaxLength(intArrayOf(1, 17, 5, 10, 13, 15, 10, 5, 16, 8)) == 7)
    println(s.wiggleMaxLength(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)) == 2)
}
/*
执行用时：160 ms, 在所有 Kotlin 提交中击败了20.00% 的用户
内存消耗：33.1 MB, 在所有 Kotlin 提交中击败了6.67% 的用户
 */