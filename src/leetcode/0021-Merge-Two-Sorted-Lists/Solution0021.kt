// https://leetcode.com/problems/merge-two-sorted-lists/
// 21. Merge Two Sorted Lists
/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
 */
class Solution0021 {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummyHead = ListNode(-1)
        var h1 = l1
        var h2 = l2
        var curNode: ListNode? = dummyHead
        while (h1 != null && h2 != null) {
            if (h1.`val` < h2.`val`) {
                val node = h1
                h1 = h1.next
                node.next = null

                curNode?.next = node
                curNode = curNode?.next
            } else {
                val node = h2
                h2 = h2.next
                node.next = null

                curNode?.next = node
                curNode = curNode?.next
            }
        }
        if (h1 != null) curNode?.next = h1
        if (h2 != null) curNode?.next = h2
        return dummyHead.next
    }
}
/*
Runtime: 128 ms, faster than 98.58% of Kotlin online submissions for Merge Two Sorted Lists.
Memory Usage: 38.6 MB, less than 100.00% of Kotlin online submissions for Merge Two Sorted Lists.
 */