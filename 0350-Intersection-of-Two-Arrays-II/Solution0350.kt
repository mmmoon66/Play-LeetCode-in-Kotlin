// https://leetcode.com/problems/intersection-of-two-arrays-ii/
// 350. Intersection of Two Arrays II
/*
Given two arrays, write a function to compute their intersection.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
class Solution0350 {
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val freq = HashMap<Int, Int>()
        for (num in nums1) {
            freq[num] = freq.getOrDefault(num, 0) + 1
        }
        val res = mutableListOf<Int>()
        for (num in nums2) {
            val count = freq[num]
            if (count != null && count != 0) {
                res.add(num)
                freq[num] = count - 1
            }
        }
        return res.toIntArray()
    }
}
/*
Runtime: 144 ms, faster than 100.00% of Kotlin online submissions for Intersection of Two Arrays II.
Memory Usage: 45.3 MB, less than 100.00% of Kotlin online submissions for Intersection of Two Arrays II.
 */