// https://leetcode.com/problems/rotate-list/
// 61. Rotate List
/*
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->N
 */
class Solution0061 {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return head
        }
        var count = 0
        var node = head
        while(node != null) {
            ++count
            node = node.next
        }
        var rem = k % count
        if (rem == 0) {
            return head
        }
        var fast: ListNode = head
        while(rem > 0) {
            fast = fast.next!!
            --rem
        }
        var slow: ListNode = head
        while(fast.next != null) {
            fast = fast.next!!
            slow = slow.next!!
        }
        val newHead = slow.next
        slow.next = null
        fast.next = head
        return newHead
    }
}

fun main() {
    val head = ListNode(intArrayOf(1, 2, 3, 4, 5))
    val s = Solution0061()
    println(s.rotateRight(head, 2))
}
/*
Runtime: 164 ms, faster than 72.73% of Kotlin online submissions for Rotate List.
Memory Usage: 33.7 MB, less than 100.00% of Kotlin online submissions for Rotate List.
 */