import java.util.*

// https://leetcode.com/problems/add-two-numbers-ii/
// 445. Add Two Numbers II
/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
class Solution0445_v2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        val stack1 = Stack<Int>()
        val stack2 = Stack<Int>()
        var cur = l1
        while (cur != null) {
            stack1.push(cur.`val`)
            cur = cur.next
        }
        cur = l2
        while (cur != null) {
            stack2.push(cur.`val`)
            cur = cur.next
        }
        val dummyHead = ListNode(0)
        var carry = 0
        while (stack1.isNotEmpty() || stack2.isNotEmpty()) {
            var sum = carry
            if (stack1.isNotEmpty()) sum += stack1.pop()
            if (stack2.isNotEmpty()) sum += stack2.pop()
            carry = sum / 10
            sum %= 10
            val node = ListNode(sum)
            node.next = dummyHead.next
            dummyHead.next = node
        }
        dummyHead.`val` = carry
        return if (carry == 0) dummyHead.next else dummyHead
    }
}

fun main() {
    val l1 = ListNode(intArrayOf(7, 2, 4, 3))
    val l2 = ListNode(intArrayOf(5, 6, 4))
    val s = Solution0445_v2()
    println(s.addTwoNumbers(l1, l2))
}
/*
Runtime: 176 ms, faster than 100.00% of Kotlin online submissions for Add Two Numbers II.
Memory Usage: 45.6 MB, less than 100.00% of Kotlin online submissions for Add Two Numbers II.
 */