// https://leetcode-cn.com/problems/reorder-list/
// 143. Reorder List
/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reorder-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0143_v3 {
    fun reorderList(head: ListNode?) {
        if (head?.next?.next == null) return

        var slow = head
        var fast = head
        while (fast?.next?.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }
        val mid = slow
        val left = head
        var right = mid!!.next
        mid.next = null

        right = reverseList(right)

        mergeList(left, right)
    }

    private fun reverseList(head: ListNode?): ListNode? {
        val dummy = ListNode(-1)
        var cur = head
        while (cur != null) {
            val node = cur
            cur = cur.next
            node.next = null

            node.next = dummy.next
            dummy.next = node
        }
        val res = dummy.next
        dummy.next = null
        return res
    }

    private fun mergeList(l1: ListNode?, l2: ListNode?) {
        var cur1 = l1
        var cur2 = l2
        while (cur1 != null || cur2 != null) {
            val node1 = cur1
            cur1 = cur1?.next
            val node2 = cur2
            cur2 = cur2?.next
            node1?.next = node2
            node2?.next = cur1
        }
    }
}

fun main() {
    val s = Solution0143_v3()
//    val head = ListNode(intArrayOf(1, 2, 3, 4))
    val head = ListNode(intArrayOf(1, 2, 3, 4, 5))
    s.reorderList(head)
    println(head)
}
/*
执行用时：276 ms, 在所有 Kotlin 提交中击败了75.00%的用户
内存消耗：39.6 MB, 在所有 Kotlin 提交中击败了25.00%的用户
 */