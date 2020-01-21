import java.util.*

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
// 26. Remove Duplicates from Sorted Array
/*
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.

Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
 */
class Solution0026 {
    fun removeDuplicates(nums: IntArray): Int {
        val n = nums.size
        if (n < 2) return n
        var k = 1// nums[0..k)无重复元素
        var cur = nums.first()
        for (i in k until n) {
            if (nums[i] != cur) {
                nums[k++] = nums[i]
                cur = nums[i]
            }
        }
        return k
    }
}

fun main() {
    val s = Solution0026()
    var nums = intArrayOf(1, 1, 2)
    println(s.removeDuplicates(nums))
    println(Arrays.toString(nums))
    nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    println(s.removeDuplicates(nums))
    println(Arrays.toString(nums))
}
/*
Runtime: 220 ms, faster than 59.17% of Kotlin online submissions for Remove Duplicates from Sorted Array.
Memory Usage: 47.4 MB, less than 100.00% of Kotlin online submissions for Remove Duplicates from Sorted Array.
 */