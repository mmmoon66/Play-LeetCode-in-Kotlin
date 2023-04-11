// https://leetcode.com/problems/median-of-two-sorted-arrays/
// 4. Median of Two Sorted Arrays
/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.

Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5
 */
class Solution0004 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        if (m == 0) {
            return median(nums2)
        }
        val n = nums2.size
        if (n == 0) {
            return median(nums1)
        }
        val half = (m + n) / 2
        val nums = IntArray(half + 1)
        var i = 0
        var j = 0
        var k = 0
        while (k < nums.size) {
            if (i == m) {
                nums[k++] = nums2[j++]
            } else if (j == n) {
                nums[k++] = nums1[i++]
            } else if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++]
            } else {
                nums[k++] = nums2[j++]
            }
        }
        return if ((m + n) % 2 == 0) {
            (nums[half - 1] + nums[half]) / 2.0
        } else {
            nums[half] * 1.0
        }
    }

    private fun median(nums: IntArray): Double {
        val size = nums.size

        return if (size == 0) {
            0.0
        } else if (size % 2 == 0) {
            (nums[size / 2] + nums[size / 2 - 1]) / 2.0
        } else {
            nums[size / 2] * 1.0
        }
    }
}

fun main() {
    val s = Solution0004()
    println(s.findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)) == 2.0)
//    println(s.findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)) == 2.5)
}
/*
Runtime: 396 ms, faster than 18.54% of Kotlin online submissions for Median of Two Sorted Arrays.
Memory Usage: 44.9 MB, less than 34.88% of Kotlin online submissions for Median of Two Sorted Arrays.
 */