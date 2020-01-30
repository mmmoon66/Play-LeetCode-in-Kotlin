import java.util.*

// https://leetcode.com/problems/product-of-array-except-self/
// 238. Product of Array Except Self
/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
class Solution0238 {
    fun productExceptSelf(nums: IntArray): IntArray {
        val res = IntArray(nums.size)
        var product = 1
        for (i in 0 until nums.size - 1) {
            product *= nums[i]
            res[i] = product
        }
        product = 1
        for (i in nums.size-1 downTo 1) {
            res[i] = product * res[i - 1]
            product *= nums[i]
        }
        res[0] = product
        return res
    }
}

fun main() {
    val s = Solution0238()
    s.productExceptSelf(intArrayOf(1, 2, 3, 4)).also { println(Arrays.toString(it))}
    s.productExceptSelf(intArrayOf(1, 2)).also { println(Arrays.toString(it))}
}
/*
Runtime: 200 ms, faster than 100.00% of Kotlin online submissions for Product of Array Except Self.
Memory Usage: 49.8 MB, less than 100.00% of Kotlin online submissions for Product of Array Except Self.
 */