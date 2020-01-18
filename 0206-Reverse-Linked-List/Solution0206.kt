// https://leetcode.com/problems/reverse-linked-list/
// 206. Reverse Linked List
/*
Reverse a singly linked list.

Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Follow up:
A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
class Solution0206 {
    fun reverseList(head: ListNode?): ListNode? {
        val dummyHead = ListNode(-1)
        var cur = head
        while(cur != null) {
            val node = cur
            cur = cur.next
            node.next = dummyHead.next
            dummyHead.next = node
        }
        return dummyHead.next
    }
}
/*
Runtime: 116 ms, faster than 98.18% of Kotlin online submissions for Reverse Linked List.
Memory Usage: 38.2 MB, less than 100.00% of Kotlin online submissions for Reverse Linked List.
 */