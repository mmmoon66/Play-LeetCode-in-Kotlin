// https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// 581. Shortest Unsorted Continuous Subarray
/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
 */
class Solution0581 {
    fun findUnsortedSubarray(nums: IntArray): Int {
        val sortedNums = nums.sorted()
        var l = 0
        var r = nums.size - 1
        while(l <= r && nums[l] == sortedNums[l]) ++l
        while(r >= l && nums[r] == sortedNums[r]) --r
        return r - l + 1
    }
}

fun main() {
    val s = Solution0581()
    println(s.findUnsortedSubarray(intArrayOf(2, 6, 4, 8, 10, 9, 15)))
    println(s.findUnsortedSubarray(intArrayOf(1, 2, 3, 4, 5)))
}
/*
Runtime: 276 ms, faster than 66.67% of Kotlin online submissions for Shortest Unsorted Continuous Subarray.
Memory Usage: 49.9 MB, less than 100.00% of Kotlin online submissions for Shortest Unsorted Continuous Subarray.
 */