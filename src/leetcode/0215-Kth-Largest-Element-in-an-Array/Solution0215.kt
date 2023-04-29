import java.util.*

// https://leetcode.com/problems/kth-largest-element-in-an-array
// 215. Kth Largest Element in an Array
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
class Solution0215 {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Int>()
        for (num in nums) {
            if (pq.size == k) {
                if (num > pq.peek()) {
                    pq.poll()
                    pq.offer(num)
                }
            } else {
                pq.offer(num)
            }
        }
        return pq.peek()
    }
}

fun main() {
    val s = Solution0215()
    println(s.findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
    println(s.findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))
}
/*
Runtime: 140 ms, faster than 100.00% of Kotlin online submissions for Kth Largest Element in an Array.
Memory Usage: 44.5 MB, less than 100.00% of Kotlin online submissions for Kth Largest Element in an Array.
 */