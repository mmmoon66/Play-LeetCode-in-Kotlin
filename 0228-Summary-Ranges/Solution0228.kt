// https://leetcode-cn.com/problems/summary-ranges/
// 228. Summary Ranges
/*
You are given a sorted unique integer array nums.
Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums
is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
Each range [a,b] in the list should be output as:
"a->b" if a != b
"a" if a == b

Example 1:
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"

Example 2:
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"

Example 3:
Input: nums = []
Output: []

Example 4:
Input: nums = [-1]
Output: ["-1"]

Example 5:
Input: nums = [0]
Output: ["0"]
 

Constraints:
0 <= nums.length <= 20
-2^31 <= nums[i] <= 2^31 - 1
All the values of nums are unique.
nums is sorted in ascending order.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/summary-ranges
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0228 {
    fun summaryRanges(nums: IntArray): List<String> {
        val res = mutableListOf<String>()
        val n = nums.size
        var l = 0
        var r = 0
        while (r < n) {
            if (r + 1 < n && nums[r + 1] == nums[r] + 1) {
                ++r
            } else {
                if (nums[l] == nums[r]) {
                    res.add(nums[l].toString())
                } else {
                    res.add("${nums[l]}->${nums[r]}")
                }
                ++r
                l = r
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0228()
    println(s.summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)).joinToString())
    println(s.summaryRanges(intArrayOf(0, 2, 3, 4, 6, 8, 9)).joinToString())
    println(s.summaryRanges(intArrayOf()).joinToString())
    println(s.summaryRanges(intArrayOf(-1)).joinToString())
    println(s.summaryRanges(intArrayOf(0)).joinToString())
}
/*
执行用时：168 ms, 在所有 Kotlin 提交中击败了93.42%的用户
内存消耗：33 MB, 在所有 Kotlin 提交中击败了64.47%的用户
 */