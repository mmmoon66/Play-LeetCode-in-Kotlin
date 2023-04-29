// https://leetcode.com/problems/remove-linked-list-elements/
// 203. Remove Linked List Elements
/*
Remove all elements from a linked list of integers that have value val.

Example:
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
 */
class Solution0203 {
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        if (head == null) return null
        head.next = removeElements(head.next, `val`)
        return if (head.`val` == `val`) head.next else head
    }
}
/*
Runtime: 164 ms, faster than 95.83% of Kotlin online submissions for Remove Linked List Elements.
Memory Usage: 46.3 MB, less than 100.00% of Kotlin online submissions for Remove Linked List Elements.
 */