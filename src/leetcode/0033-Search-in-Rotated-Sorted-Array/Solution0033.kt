// https://leetcode.com/problems/search-in-rotated-sorted-array/
// 33. Search in Rotated Sorted Array
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
class Solution0033 {
    fun search(nums: IntArray, target: Int): Int {
        var lo = 0
        var hi = nums.size - 1
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            if (target == nums[mid]) {
                return mid
            } else if (target < nums[mid]) {
                if (nums[lo] <= nums[mid]) {
                    if (target < nums[lo]) {
                        lo = mid + 1
                    } else {
                        hi = mid - 1
                    }
                } else {
                    hi = mid - 1
                }
            } else {
                if (nums[hi] >= nums[mid]) {
                    if (target > nums[hi]) {
                        hi = mid - 1
                    } else {
                        lo = mid + 1
                    }
                } else {
                    lo = mid + 1
                }
            }
        }
        return -1
    }
}

fun main() {
    val s = Solution0033()
    assert(s.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0) == 4)
    assert(s.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3) == -1)
    assert(s.search(intArrayOf(3, 1), 1) == 1)
}
/*
Runtime: 152 ms, faster than 63.89% of Kotlin online submissions for Search in Rotated Sorted Array.
Memory Usage: 33.2 MB, less than 100.00% of Kotlin online submissions for Search in Rotated Sorted Array.
 */