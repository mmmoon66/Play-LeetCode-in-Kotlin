// https://leetcode.com/problems/odd-even-linked-list/
// 328. Odd Even Linked List
/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:
Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
 */
class Solution0328 {
    fun oddEvenList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) {
            return head
        }
        val oddDummyHead = ListNode(0)
        val evenDummyHead = ListNode(0)
        var oddPrev = oddDummyHead
        var evenPrev = evenDummyHead
        var index = 0
        var cur = head
        while(cur != null) {
            val node = cur
            cur = cur.next
            node.next = null
            if (index % 2 == 0) {
                evenPrev.next = node
                evenPrev = evenPrev.next!!
            } else {
                oddPrev.next = node
                oddPrev = oddPrev.next!!
            }
            ++index
        }
        evenPrev.next = oddDummyHead.next
        oddDummyHead.next = null
        val res = evenDummyHead.next
        evenDummyHead.next = null
        return res
    }
}

fun main() {
    val s = Solution0328()
    println(s.oddEvenList(ListNode(intArrayOf(1, 2, 3, 4, 5))))
}
/*
Runtime: 116 ms, faster than 100.00% of Kotlin online submissions for Odd Even Linked List.
Memory Usage: 42 MB, less than 100.00% of Kotlin online submissions for Odd Even Linked List.
 */