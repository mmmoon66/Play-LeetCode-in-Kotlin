// https://leetcode.com/problems/intersection-of-two-arrays/
// 349. Intersection of Two Arrays
/*
Given two arrays, write a function to compute their intersection.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:
Each element in the result must be unique.
The result can be in any order.
 */
class Solution0349 {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        return (nums1.toSet() intersect nums2.toSet()).toIntArray()
    }
}
/*
Runtime: 164 ms, faster than 100.00% of Kotlin online submissions for Intersection of Two Arrays.
Memory Usage: 44 MB, less than 100.00% of Kotlin online submissions for Intersection of Two Arrays.
 */