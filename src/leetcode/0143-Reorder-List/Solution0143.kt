import javax.swing.DefaultDesktopManager

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
class Solution0143 {
    fun reorderList(head: ListNode?) {
        if (head?.next == null) return
        head.next = helper(head.next)
    }

    private fun helper(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        val nodes = mutableListOf<ListNode>()
        var cur = head
        while (cur != null) {
            val node = cur
            cur = cur.next
            node.next = null
            nodes.add(node)
        }
        val dummyHead = ListNode(-1)
        var end = nodes.size - 1
        var start = 0
        var prev = dummyHead
        while (start <= end) {
            prev.next = nodes[end]
            prev = prev.next!!
            if (start != end) {
                prev.next = nodes[start]
                prev = prev.next!!
            }
            ++start
            --end
        }
        val res = dummyHead.next
        dummyHead.next = null
        return res
    }
}

fun main() {
    val s = Solution0143()
//    val head = ListNode(intArrayOf(1, 2, 3, 4))
    val head = ListNode(intArrayOf(1, 2, 3, 4, 5))
    s.reorderList(head)
    println(head)
}
/*
执行用时：284 ms, 在所有 Kotlin 提交中击败了25.00%的用户
内存消耗：38.8 MB, 在所有 Kotlin 提交中击败了25.00%的用户
 */