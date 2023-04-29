class Solution0213 {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]
        return maxOf(rob(nums, 0, nums.size - 2), rob(nums, 1, nums.size - 1))
    }

    // 在nums[start..end]范围内偷取财物的最大值
    private fun rob(nums: IntArray, start: Int, end: Int): Int {
        if (start > end) return 0
        if (start == end) return nums[start]
        var prevMax = nums[start]
        var curMax = maxOf(nums[start], nums[start + 1])
        for (i in start + 2..end) {
            curMax = maxOf(curMax, nums[i] + prevMax).also { prevMax = curMax }
        }
        return curMax
    }
}