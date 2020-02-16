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
class Solution0023 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val dummyHead = ListNode(-1)
        var prev = dummyHead
        var finished = 0
        for (head in lists) {
            if (head == null) ++finished
        }
        while(finished < lists.size) {
            var minHead: ListNode? = null
            var minIndex = -1
            for (i in 0 until lists.size) {
                val head = lists[i]
                if (head != null && (minHead == null || head.`val` < minHead.`val`)) {
                    minHead = head
                    minIndex = i
                }
            }
            if (minHead == null || minIndex == -1) break
            lists[minIndex] = lists[minIndex]?.next
            if (lists[minIndex] == null) ++finished
            prev.next = minHead
            minHead.next = null
            prev = prev.next!!
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
    val s = Solution0023()
    val head = s.mergeKLists(lists)
    println(head)
}
/*
Runtime: 1480 ms, faster than 5.08% of Kotlin online submissions for Merge k Sorted Lists.
Memory Usage: 77.4 MB, less than 100.00% of Kotlin online submissions for Merge k Sorted Lists.
 */