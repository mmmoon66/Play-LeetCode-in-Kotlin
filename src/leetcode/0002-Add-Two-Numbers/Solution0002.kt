// https://leetcode.com/problems/add-two-numbers/
// 2. Add Two Numbers
/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
class Solution0002 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var p = l1
        var q = l2
        val dummyHead = ListNode(-1)
        var curNode = dummyHead
        var carry = 0
        while (p != null || q != null || carry > 0) {
            var sum = carry
            p?.let {
                sum += it.`val`
                p = it.next
            }
            q?.let {
                sum += it.`val`
                q = it.next
            }
            curNode.next = ListNode(sum % 10)
            curNode = curNode.next!!
            carry = sum / 10
        }
        val ret = dummyHead.next
        dummyHead.next = null
        return ret
    }
}

fun main() {
    val s = Solution0002()
    println(s.addTwoNumbers(ListNode(intArrayOf(2, 4, 5)), ListNode(intArrayOf(5, 6, 4))))
}
/*
Runtime: 220 ms, faster than 37.86% of Kotlin online submissions for Add Two Numbers.
Memory Usage: 35.4 MB, less than 100.00% of Kotlin online submissions for Add Two Numbers.
 */