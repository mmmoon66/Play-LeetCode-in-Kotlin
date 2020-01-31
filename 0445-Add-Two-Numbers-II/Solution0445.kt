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
class Solution0445 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        return stringToList(listToString(l1), listToString(l2))
    }

    private fun stringToList(s: String, t: String): ListNode? {
        val dummyHead = ListNode(0)
        var i = s.length -1
        var j = t.length -1
        var carry = 0
        while(i >= 0 || j >= 0) {
            var sum = carry
            if (i >= 0) {
                sum += s[i] - '0'
                --i
            }
            if (j >= 0) {
                sum += t[j] - '0'
                --j
            }
            carry = sum / 10
            sum %= 10

            val node = ListNode(sum)
            node.next = dummyHead.next
            dummyHead.next = node
        }
        if (carry > 0) {
            val node = ListNode(carry)
            node.next = dummyHead.next
            dummyHead.next = node
        }
        return dummyHead.next
    }

    private fun listToString(head: ListNode?): String {
        val res = StringBuilder()
        var cur = head
        while(cur != null) {
            res.append(cur.`val`)
            cur = cur.next
        }
        return res.toString()
    }
}
/*
Runtime: 184 ms, faster than 100.00% of Kotlin online submissions for Add Two Numbers II.
Memory Usage: 49.3 MB, less than 100.00% of Kotlin online submissions for Add Two Numbers II.
 */