import kotlin.math.abs

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// 448. Find All Numbers Disappeared in an Array
/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[5,6]
 */
class Solution0448 {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        for (num in nums) {
            val index = abs(num) - 1
            if (nums[index] > 0) {
                nums[index] *= -1
            }
        }
        val res = mutableListOf<Int>()
        nums.forEachIndexed { index, num ->
            if (num > 0) {
                res.add(index + 1)
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0448()
    println(s.findDisappearedNumbers(intArrayOf(4,3,2,7,8,2,3,1)))
}
/*
Runtime: 324 ms, faster than 98.51% of Kotlin online submissions for Find All Numbers Disappeared in an Array.
Memory Usage: 55.5 MB, less than 100.00% of Kotlin online submissions for Find All Numbers Disappeared in an Array.
 */