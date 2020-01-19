// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// 167. Two Sum II - Input array is sorted
/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:
Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.

Example:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */
class Solution0167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val n = numbers.size
        var i = 0
        var j = n - 1
        while(i < j) {
            val sum = numbers[i] + numbers[j]
            if (sum < target) {
                ++i
            } else if (sum > target) {
                --j
            } else {
                return intArrayOf(i + 1, j + 1)
            }
        }
        return intArrayOf(-1, -1)
    }
}
/*
Runtime: 132 ms, faster than 100.00% of Kotlin online submissions for Two Sum II - Input array is sorted.
Memory Usage: 42.2 MB, less than 100.00% of Kotlin online submissions for Two Sum II - Input array is sorted.
 */