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
class Solution0083 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return head
        val next = deleteDuplicates(head.next)
        if (next == null) {
            return head
        } else if (next.`val` == head.`val`) {
            return next
        } else {
            head.next = next
            return head
        }
    }
}

fun main() {
    val s = Solution0083()
    var head = ListNode(intArrayOf(1, 1, 2))
    println(s.deleteDuplicates(head))
    head = ListNode(intArrayOf(1, 1, 2, 3, 3))
    println(s.deleteDuplicates(head))
}
/*
Runtime: 148 ms, faster than 100.00% of Kotlin online submissions for Remove Duplicates from Sorted List.
Memory Usage: 40.2 MB, less than 100.00% of Kotlin online submissions for Remove Duplicates from Sorted List.
 */