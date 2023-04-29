// https://leetcode.com/problems/majority-element/
// 169. Majority Element
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
 */
class Solution0169 {
    fun majorityElement(nums: IntArray): Int {
        val record = HashMap<Int, Int>()
        val n = nums.size
        for (num in nums) {
            val count = record.getOrDefault(num, 0) + 1
            if (count > n / 2) {
                return num
            } else {
                record[num] = count
            }
        }
        return -1
    }
}
/*
Runtime: 208 ms, faster than 100.00% of Kotlin online submissions for Majority Element.
Memory Usage: 48.9 MB, less than 100.00% of Kotlin online submissions for Majority Element.
 */