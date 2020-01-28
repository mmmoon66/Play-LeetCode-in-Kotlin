import java.util.*

// https://leetcode.com/problems/merge-sorted-array/
// 88. Merge Sorted Array
/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.

Example:
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
Output: [1,2,2,3,5,6]
 */
class Solution0088 {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        val aux = IntArray(m + n)
        var i = 0
        var j = 0
        var k = 0
        while (i < m || j < n) {
            if (i == m) {
                aux[k] = nums2[j]
                ++j
                ++k
            } else if (j == n) {
                aux[k] = nums1[i]
                ++i
                ++k
            } else if (nums1[i] < nums2[j]) {
                aux[k] = nums1[i]
                ++i
                ++k
            } else {
                aux[k] = nums2[j]
                ++j
                ++k
            }
        }
        for (index in 0 until m + n) {
            nums1[index] = aux[index]
        }
    }
}

fun main() {
    val s = Solution0088()
    val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
    val nums2 = intArrayOf(2, 5, 6)
    s.merge(nums1, 3, nums2, 3)
    println(Arrays.toString(nums1))
}
/*
Runtime: 112 ms, faster than 98.85% of Kotlin online submissions for Merge Sorted Array.
Memory Usage: 37.5 MB, less than 100.00% of Kotlin online submissions for Merge Sorted Array.
 */