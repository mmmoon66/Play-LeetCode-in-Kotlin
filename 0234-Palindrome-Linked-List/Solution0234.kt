import java.util.*

// https://leetcode.com/problems/palindrome-linked-list/
// 234. Palindrome Linked List
/*
Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true

Follow up:
Could you do it in O(n) time and O(1) space?
 */
class Solution0234 {
    fun isPalindrome(head: ListNode?): Boolean {
        // 将链表分成两部分
        val dummyHead = ListNode(-1)
        dummyHead.next = head
        var slow = dummyHead
        var fast = dummyHead
        while(slow.next != null && fast.next != null && fast.next!!.next != null) {
            slow = slow.next!!
            fast = fast.next!!.next!!
        }
        val right = slow.next
        slow.next = null
        val left = dummyHead.next
        dummyHead.next = null

        // 将右半部分链表反转
        var cur = right
        while(cur != null) {
            val node = cur
            cur = cur.next
            node.next = dummyHead.next
            dummyHead.next = node
        }
        val reversedRight = dummyHead.next

        // 比较left和reversedRight
        var l = left
        var r = reversedRight
        while(l != null && r != null) {
            if (l.`val` != r.`val`) return false
            l = l.next
            r = r.next
        }
        return true
    }
}

fun main() {
    val s = Solution0234()
    println(s.isPalindrome(ListNode(intArrayOf(1, 2, 3, 2, 1))))
    println(s.isPalindrome(ListNode(intArrayOf(1, 2, 3, 3, 1))))
    println(s.isPalindrome(ListNode(intArrayOf(1, 2, 2, 1))))
    println(s.isPalindrome(ListNode(intArrayOf(1, 2, 3, 1))))
}
/*
Runtime: 176 ms, faster than 100.00% of Kotlin online submissions for Palindrome Linked List.
Memory Usage: 44.1 MB, less than 100.00% of Kotlin online submissions for Palindrome Linked List.
 */