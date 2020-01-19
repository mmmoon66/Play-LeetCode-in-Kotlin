// https://leetcode.com/problems/contains-duplicate/
// 217. Contains Duplicate
/*
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:
Input: [1,2,3,1]
Output: true

Example 2:
Input: [1,2,3,4]
Output: false

Example 3:
Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
 */
class Solution0217 {
    fun containsDuplicate(nums: IntArray): Boolean {
        return nums.toSet().size != nums.size
    }
}
/*
Runtime: 216 ms, faster than 100.00% of Kotlin online submissions for Contains Duplicate.
Memory Usage: 49.9 MB, less than 100.00% of Kotlin online submissions for Contains Duplicate.
 */