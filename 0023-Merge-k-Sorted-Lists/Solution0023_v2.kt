import java.util.*

// https://leetcode.com/problems/merge-k-sorted-lists/
// 23. Merge k Sorted Lists
/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */
class Solution0023_v2 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val dummyHead = ListNode(-1)
        var curNode = dummyHead
        val pq = PriorityQueue<ListNode> { a, b -> a.`val` - b.`val` }
        for (head in lists) {
            if (head != null) pq.offer(head)
        }
        while(pq.isNotEmpty()) {
            var top = pq.poll()
            if (pq.isEmpty()) {
                curNode.next = top
                break
            }
            curNode.next = top
            top = top.next
            curNode = curNode.next!!
            if (top != null) {
                pq.offer(top)
            }
        }
        return dummyHead.next
    }
}

fun main() {
    val lists = arrayOf<ListNode?>(
        ListNode(intArrayOf(1, 4, 5)),
        ListNode(intArrayOf(1, 3, 4)),
        ListNode(intArrayOf(2, 6))
    )
    val s = Solution0023_v2()
    val head = s.mergeKLists(lists)
    println(head)
}
/*
Runtime: 380 ms, faster than 39.83% of Kotlin online submissions for Merge k Sorted Lists.
Memory Usage: 40.1 MB, less than 100.00% of Kotlin online submissions for Merge k Sorted Lists.
 */