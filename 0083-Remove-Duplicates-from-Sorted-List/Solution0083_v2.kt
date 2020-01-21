// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// 83. Remove Duplicates from Sorted List
/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
Input: 1->1->2
Output: 1->2

Example 2:
Input: 1->1->2->3->3
Output: 1->2->3
 */
class Solution0083_v2 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return head
        val dummyHead = ListNode(head.`val` - 1)
        var prev = dummyHead
        var curHead = head
        while(curHead != null) {
            val node = curHead
            curHead = curHead.next
            node.next = null

            if (node.`val` != prev.`val`) {
                prev.next = node
                prev = prev.next!!
            }
        }
        return dummyHead.next
    }
}

fun main() {
    val s = Solution0083_v2()
    var head = ListNode(intArrayOf(1, 1, 2))
    println(s.deleteDuplicates(head))
    head = ListNode(intArrayOf(1, 1, 2, 3, 3))
    println(s.deleteDuplicates(head))
}
/*
Runtime: 124 ms, faster than 100.00% of Kotlin online submissions for Remove Duplicates from Sorted List.
Memory Usage: 42.3 MB, less than 100.00% of Kotlin online submissions for Remove Duplicates from Sorted List.
 */