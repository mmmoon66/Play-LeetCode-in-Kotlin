// https://leetcode-cn.com/problems/non-decreasing-array/
// 665. Non-decreasing Array
/*
Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.

We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 

Example 1:
Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: nums = [4,2,1]
Output: false
Explanation: You can't get a non-decreasing array by modify at most one element.
 

Constraints:
n == nums.length
1 <= n <= 10^4
-10^5 <= nums[i] <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/non-decreasing-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0665 {
    fun checkPossibility(nums: IntArray): Boolean {
        var i = 0
        while (i + 1 < nums.size) {
            if (nums[i] > nums[i + 1]) {
                val a = nums[i]
                val b = nums[i + 1]
                nums[i] = a
                nums[i + 1] = a
                if (isNonDescending(nums)) return true
                nums[i] = b
                nums[i + 1] = b
                if (isNonDescending(nums)) return true
                return false
            } else {
                ++i
            }
        }
        return true
    }

    private fun isNonDescending(nums: IntArray): Boolean {
        var i = 0
        while (i + 1 < nums.size) {
            if (nums[i] > nums[i + 1]) return false
            ++i
        }
        return true
    }
}

fun main() {
    val s = Solution0665()
    println(s.checkPossibility(intArrayOf(3, 4, 1, 2)))
    println(s.checkPossibility(intArrayOf(1, 2, 3, 4)))
    println(s.checkPossibility(intArrayOf(1)))
    println(s.checkPossibility(intArrayOf(4, 2, 3)))
    println(s.checkPossibility(intArrayOf(4, 2, 1)))
    println(s.checkPossibility(intArrayOf(5, 7, 1, 8)))
}
/*
执行用时：268 ms, 在所有 Kotlin 提交中击败了100.00%的用户
内存消耗：38.6 MB, 在所有 Kotlin 提交中击败了37.50%的用户
 */