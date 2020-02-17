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
        var l = 0
        var r = nums.size - 1
        while(l <= r) {
            val mid = l + (r - l) / 2
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] < target) {
                if (nums[l] > nums[r]) {
                    if (nums[mid] >= nums[l]) {
                        l = mid + 1
                    } else if (nums[mid] <= nums[r]) {
                        if (target >= nums[l]) {
                            r = mid - 1
                        } else if (target <= nums[r]) {
                            l = mid + 1
                        } else {
                            return -1
                        }
                    }
                } else {
                    l = mid + 1
                }
            } else {
                if (nums[l] > nums[r]) {
                    if (nums[mid] >= nums[l]) {
                        if (target >= nums[l]) {
                            r = mid - 1
                        } else if (target <= nums[r]) {
                            l = mid + 1
                        } else {
                            return -1
                        }
                    } else if (nums[mid] <= nums[r]) {
                        r = mid - 1
                    }
                } else {
                    r = mid - 1
                }
            }
        }
        return -1
    }
}

fun main() {
    val s = Solution0033()
    println(s.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0))
    println(s.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3))
    println(s.search(intArrayOf(3, 1), 1))
}
/*
Runtime: 152 ms, faster than 63.89% of Kotlin online submissions for Search in Rotated Sorted Array.
Memory Usage: 33.2 MB, less than 100.00% of Kotlin online submissions for Search in Rotated Sorted Array.
 */