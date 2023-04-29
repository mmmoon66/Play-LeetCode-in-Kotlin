// https://leetcode.com/problems/max-consecutive-ones/
// 485. Max Consecutive Ones
/*
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.

Note:
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
 */
class Solution0485 {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var count = 0
        nums.forEach { num ->
            if (num == 1) {
                ++count
                max = maxOf(max, count)
            } else if (num == 0) {
                count = 0
            }
        }
        return max
    }
}
/*
Runtime: 224 ms, faster than 100.00% of Kotlin online submissions for Max Consecutive Ones.
Memory Usage: 45.8 MB, less than 100.00% of Kotlin online submissions for Max Consecutive Ones.
 */