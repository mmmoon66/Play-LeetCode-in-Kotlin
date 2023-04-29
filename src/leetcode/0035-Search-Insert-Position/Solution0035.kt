// https://leetcode.com/problems/search-insert-position/
// 35. Search Insert Position
/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:
Input: [1,3,5,6], 5
Output: 2

Example 2:
Input: [1,3,5,6], 2
Output: 1

Example 3:
Input: [1,3,5,6], 7
Output: 4

Example 4:
Input: [1,3,5,6], 0
Output: 0
 */
class Solution0035 {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        while(l <= r) {
            val mid = l + (r - l) / 2
            if (target > nums[mid]) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return l
    }
}

fun main() {
    val s = Solution0035()
    println(s.searchInsert(intArrayOf(1, 3, 5, 6), 5))
    println(s.searchInsert(intArrayOf(1, 3, 5, 6), 2))
    println(s.searchInsert(intArrayOf(1, 3, 5, 6), 7))
    println(s.searchInsert(intArrayOf(1, 3, 5, 6), 0))
}
/*
Runtime: 136 ms, faster than 97.59% of Kotlin online submissions for Search Insert Position.
Memory Usage: 41 MB, less than 100.00% of Kotlin online submissions for Search Insert Position.
 */