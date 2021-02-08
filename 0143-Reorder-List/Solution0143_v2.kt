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
class Solution0143_v2 {
    fun reorderList(head: ListNode?) {
        if (head?.next == null) return
        val nodes = mutableListOf<ListNode>()
        var node = head
        while (node != null) {
            nodes.add(node)
            node = node.next
        }
        var l = 0
        var r = nodes.size - 1
        while(l < r) {
            nodes[l].next = nodes[r]
            ++l
            if (l == r) break
            nodes[r].next = nodes[l]
            --r
        }
        nodes[l].next = null
    }
}

fun main() {
    val s = Solution0143_v2()
//    val head = ListNode(intArrayOf(1, 2, 3, 4))
    val head = ListNode(intArrayOf(1, 2, 3, 4, 5))
    s.reorderList(head)
    println(head)
}
/*
执行用时：260 ms, 在所有 Kotlin 提交中击败了100.00%的用户
内存消耗：39.6 MB, 在所有 Kotlin 提交中击败了25.00%的用户
 */