// https://leetcode-cn.com/problems/find-peak-element/
// 162. Find Peak Element
/*
A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.
 

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 

Constraints:
1 <= nums.length <= 1000
-2^31 <= nums[i] <= 2^31 - 1
nums[i] != nums[i + 1] for all valid i.
 

Follow up: Could you implement a solution with logarithmic complexity?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-peak-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0162 {
    fun findPeakElement(nums: IntArray): Int {
        for (i in nums.indices) {
            val higherThanLeft = (i - 1) < 0 || nums[i - 1] < nums[i]
            val higherThanRight = (i + 1) >= nums.size || nums[i] > nums[i + 1]
            if (higherThanLeft && higherThanRight) return i
        }
        return -1
    }
}
/*
执行用时：236 ms, 在所有 Kotlin 提交中击败了33.33%的用户
内存消耗：35.3 MB, 在所有 Kotlin 提交中击败了66.67%的用户
 */