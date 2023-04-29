// https://leetcode-cn.com/problems/contains-duplicate-ii/
// 219. Contains Duplicate II
/*
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such
that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/contains-duplicate-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0219_v2 {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        if (nums.size <= 1) return false
        if (k <= 0) return false
        val set = hashSetOf<Int>()
        for (i in nums.indices) {
            if (nums[i] in set) return true
            set.add(nums[i])
            if (set.size == k + 1) set.remove(nums[i - k])
        }
        return false
    }
}

fun main() {
    val s = Solution0219_v2()
    println(s.containsNearbyDuplicate(intArrayOf(1, 2, 3, 1), 3))
    println(s.containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1))
    println(s.containsNearbyDuplicate(intArrayOf(1, 2, 3, 1, 2, 3), 2))
}
/*
执行用时：344 ms, 在所有 Kotlin 提交中击败了22.22%的用户
内存消耗：41.7 MB, 在所有 Kotlin 提交中击败了44.44%的用户
 */