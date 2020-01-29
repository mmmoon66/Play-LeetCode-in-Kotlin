import kotlin.math.abs

// https://leetcode.com/problems/find-all-duplicates-in-an-array/
// 442. Find All Duplicates in an Array
/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[2,3]
 */
class Solution0442 {
    fun findDuplicates(nums: IntArray): List<Int> {
        val res = mutableListOf<Int>()
        for (num in nums) {
            val index = abs(num) - 1
            if (nums[index] < 0) {
                res.add(abs(num))
            } else {
                nums[index] *= -1
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0442()
    println(s.findDuplicates(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
}
/*
Runtime: 652 ms, faster than 8.33% of Kotlin online submissions for Find All Duplicates in an Array.
Memory Usage: 71.1 MB, less than 100.00% of Kotlin online submissions for Find All Duplicates in an Array.
 */