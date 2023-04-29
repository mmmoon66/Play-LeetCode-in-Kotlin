// https://leetcode-cn.com/problems/single-number-ii/
// 137. Single Number II
/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 

Example 1:
Input: nums = [2,2,3,2]
Output: 3

Example 2:
Input: nums = [0,1,0,1,0,1,99]
Output: 99
 

Constraints:
1 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each element in nums appears exactly three times except for one element which appears once.
 

Follow up: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/single-number-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0137_v2 {
    fun singleNumber(nums: IntArray): Int {
        val sum1 = nums.fold(0L) { a, b -> a + b }
        val sum2 = nums.toSet().fold(0L) { a, b -> a + b }
        return ((3 * sum2 - sum1) / 2).toInt()
    }
}
/*
执行用时：244 ms, 在所有 Kotlin 提交中击败了33.33%的用户
内存消耗：36.1 MB, 在所有 Kotlin 提交中击败了16.67%的用户
 */