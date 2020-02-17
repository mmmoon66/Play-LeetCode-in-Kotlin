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
        if (l1 == null) return l2
        if (l2 == null) return l1
        var p: ListNode? = l1
        var q: ListNode? = l2
        val dummyHead = ListNode(-1)
        var curNode = dummyHead
        var carry = 0
        while(p != null || q != null) {
            var sum = carry
            if (p != null) {
                sum += p.`val`
                p = p.next
            }
            if (q != null) {
                sum += q.`val`
                q = q.next
            }
            carry = sum / 10
            sum %= 10
            curNode.next = ListNode(sum)
            curNode = curNode.next!!
        }
        if (carry > 0) {
            curNode.next = ListNode(carry)
        }
        return dummyHead.next
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