// https://leetcode.com/problems/range-sum-query-immutable/
// 303. Range Sum Query - Immutable
/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

Note:
You may assume that the array does not change.
There are many calls to sumRange function.
 */
class NumArray(nums: IntArray) {
    // sums[i] 等于 nums[0...i)范围内元素的和
    private val sums = IntArray(nums.size + 1)

    init {
        sums[0] = 0
        for (i in 1 until sums.size) {
            sums[i] = sums[i - 1] + nums[i - 1]
        }
    }

    fun sumRange(i: Int, j: Int): Int {
        return sums[j + 1] - sums[i]
    }
}

fun main() {
    val numArray = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
    println(numArray.sumRange(0, 2))
    println(numArray.sumRange(2, 5))
    println(numArray.sumRange(0, 5))
}
/*
Runtime: 168 ms, faster than 100.00% of Kotlin online submissions for Range Sum Query - Immutable.
Memory Usage: 49.9 MB, less than 100.00% of Kotlin online submissions for Range Sum Query - Immutable.
 */