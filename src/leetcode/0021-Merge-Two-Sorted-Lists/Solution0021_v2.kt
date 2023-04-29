// https://leetcode.com/problems/merge-two-sorted-lists/
// 21. Merge Two Sorted Lists
/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
 */
class Solution0021_v2 {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        if (l1.`val` < l2.`val`) {
            l1.next = mergeTwoLists(l1.next, l2)
            return l1
        }  else {
            l2.next = mergeTwoLists(l1, l2.next)
            return l2
        }
    }
}
/*
Runtime: 128 ms, faster than 98.58% of Kotlin online submissions for Merge Two Sorted Lists.
Memory Usage: 38.6 MB, less than 100.00% of Kotlin online submissions for Merge Two Sorted Lists.
 */