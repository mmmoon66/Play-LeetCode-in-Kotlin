class Solution0034 {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (target == nums[mid]) {
                var i = mid
                while (i >= left && nums[i] == target) --i
                var j = mid
                while (j <= right && nums[j] == target) ++j
                return intArrayOf(i + 1, j - 1)
            } else if (target < nums[mid]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return intArrayOf(-1, -1)
    }
}

fun main() {
    assert(Solution0034().searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8).contentEquals(intArrayOf(3, 4)))
    assert(Solution0034().searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 6).contentEquals(intArrayOf(-1, -1)))
}