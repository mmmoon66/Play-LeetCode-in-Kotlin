// https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
// 81. Search in Rotated Sorted Array II
/*
You are given an integer array nums sorted in ascending order (not necessarily distinct values), and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,4,4,5,6,6,7] might become [4,5,6,6,7,0,1,2,4,4]).

If target is found in the array return its index, otherwise, return -1.


Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 

Constraints:
1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
nums is guaranteed to be rotated at some pivot.
-10^4 <= target <= 10^4
 

Follow up: This problem is the same as Search in Rotated Sorted Array, where nums may contain duplicates. Would this
affect the run-time complexity? How and why?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0081 {
    fun search(nums: IntArray, target: Int): Boolean {
        return search(nums, 0, nums.size - 1, target)
    }

    private fun search(nums: IntArray, l: Int, r: Int, target: Int): Boolean {
        if (l > r) return false
        val mid = l + (r - l) / 2
        if (target == nums[mid]) return true
        if (target < nums[mid]) {
            if (isNonDescending(nums, mid, r)) {
                return search(nums, l, mid - 1, target)
            } else {
                return search(nums, l, mid - 1, target) || search(nums, mid + 1, r, target)
            }
        } else {
            if (isNonDescending(nums, l, mid)) {
                return search(nums, mid + 1, r, target)
            } else {
                return search(nums, l, mid - 1, target) || search(nums, mid + 1, r, target)
            }
        }
    }

    private fun isNonDescending(nums: IntArray, l: Int, r: Int): Boolean {
        var i = l
        while (i + 1 <= r) {
            if (nums[i] > nums[i + 1]) return false
            ++i
        }
        return true
    }
}

fun main() {
    val s = Solution0081()
    println(s.search(intArrayOf(2, 5, 6, 0, 0, 1, 2), 0))//true
    println(s.search(intArrayOf(2, 5, 6, 0, 0, 1, 2), 3))//false
    println(s.search(intArrayOf(1, 0, 1, 1, 1), 0))//true
    println(s.search(intArrayOf(3, 5, 1), 3))//true
    println(s.search(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1), 2))//true
}
/*
执行用时：196 ms, 在所有 Kotlin 提交中击败了95.65%的用户
内存消耗：35.7 MB, 在所有 Kotlin 提交中击败了52.17%的用户
 */