// https://leetcode.com/problems/middle-of-the-linked-list/
// 876. Middle of the Linked List
/*
Given a non-empty, singly linked list with head node head, return a middle node of linked list.
If there are two middle nodes, return the second middle node.

Example 1:
Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.

Example 2:
Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.

Note:
The number of nodes in the given list will be between 1 and 100.
 */

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution0876 {
    fun middleNode(head: ListNode?): ListNode? {
        val dummyHead = ListNode(-1)
        dummyHead.next = head
        var slow: ListNode?  = dummyHead
        var fast: ListNode? = dummyHead
        while(fast!!.next != null && fast.next!!.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }
        return slow!!.next
    }
}

fun main() {
    val s = Solution0876()
    println(s.middleNode(ListNode(intArrayOf(1, 2, 3, 4, 5))))
    println(s.middleNode(ListNode(intArrayOf(1, 2, 3, 4, 5, 6))))
}
/*
Runtime: 84 ms, faster than 100.00% of Kotlin online submissions for Middle of the Linked List.
Memory Usage: 35.7 MB, less than 100.00% of Kotlin online submissions for Middle of the Linked List.
 */