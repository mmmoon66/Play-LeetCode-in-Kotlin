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
        var head1 = l1
        var head2 = l2
        var prev = dummyHead
        while(head1 != null && head2 != null) {
            if (head1.`val` < head2.`val`) {
                val node = head1
                head1 = node.next
                node.next = null

                prev.next = node
                prev = prev.next!!
            } else {
                val node = head2
                head2 = node.next
                node.next = null

                prev.next = node
                prev = prev.next!!
            }
        }
        if (head1 != null) {
            prev.next = head1
        }
        if (head2 != null) {
            prev.next = head2
        }
        return dummyHead.next
    }
}
/*
Runtime: 128 ms, faster than 98.58% of Kotlin online submissions for Merge Two Sorted Lists.
Memory Usage: 38.6 MB, less than 100.00% of Kotlin online submissions for Merge Two Sorted Lists.
 */