// https://leetcode.com/problems/find-the-duplicate-number/
// 287. Find the Duplicate Number
/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:
Input: [1,3,4,2,2]
Output: 2

Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */
class Solution0287 {
    fun findDuplicate(nums: IntArray): Int {
        var slow = 0
        var fast = 0
        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while(slow != fast)
        fast = 0
        while(slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }
        return slow
     }
}

fun main() {
    val s = Solution0287()
    println(s.findDuplicate(intArrayOf(2, 2, 2, 2)))
    println(s.findDuplicate(intArrayOf(1, 3, 4, 2, 2)))
    println(s.findDuplicate(intArrayOf(3, 1, 3, 4, 2)))
}
/*
Runtime: 144 ms, faster than 96.88% of Kotlin online submissions for Find the Duplicate Number.
Memory Usage: 42 MB, less than 100.00% of Kotlin online submissions for Find the Duplicate Number.
 */