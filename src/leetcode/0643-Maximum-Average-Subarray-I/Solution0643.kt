// https://leetcode-cn.com/problems/maximum-average-subarray-i/
// 643. Maximum Average Subarray I
/*
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0643 {
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var curSum = 0
        for (i in 0 until k) {
            curSum += nums[i]
        }
        var maxSum = curSum
        for (i in k until nums.size) {
            curSum = curSum + nums[i] - nums[i - k]
            maxSum = maxOf(maxSum, curSum)
        }
        return maxSum / k.toDouble()
    }
}

fun main() {
    val s = Solution0643()
    println(s.findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))
}