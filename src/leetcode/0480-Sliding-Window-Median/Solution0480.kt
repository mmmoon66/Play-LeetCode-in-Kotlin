// https://leetcode-cn.com/problems/sliding-window-median/
// 480. Sliding Window Median
/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the
median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to
output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
Answers within 10^-5 of the actual value will be accepted as correct.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sliding-window-median
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0480 {
    fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
        if (nums.size < k) return doubleArrayOf()
        val res = mutableListOf<Double>()
        var l = 0
        while(l + k - 1 < nums.size) {
            res.add(median(nums, l, l + k - 1))
            ++l
        }
        return res.toDoubleArray()
    }

    private fun median(nums: IntArray, l: Int, r: Int): Double {
        val arr = nums.copyOfRange(l, r + 1)
        arr.sort()
        if (arr.size % 2 == 0) {
            return (arr[arr.size / 2].toDouble() + arr[arr.size / 2 - 1].toDouble()) / 2
        } else {
            return arr[arr.size / 2].toDouble()
        }
    }
}

fun main() {
    val s = Solution0480()
    val res = s.medianSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 3)
//    val res = s.medianSlidingWindow(intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE), 2)
    println(res.joinToString())
}
/*
执行用时：3156 ms, 在所有 Kotlin 提交中击败了25.00%的用户
内存消耗：44.9 MB, 在所有 Kotlin 提交中击败了25.00%的用户
 */