// https://leetcode.com/problems/reverse-nodes-in-k-group/
// 25. Reverse Nodes in k-Group
/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:
Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
class Solution0025 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (k == 1) return head

        // 最终返回结果的虚拟头结点
        val dummyHead = ListNode(0)
        var prev = dummyHead

        // 分组的虚拟头结点
        val groupDummy = ListNode(0)
        var groupPrev = groupDummy
        var groupCount = 0 // 当前分组的节点数量
        var cur = head
        while(cur != null) {
            val node = cur
            cur = cur.next
            node.next = null

            groupPrev.next = node
            groupPrev = node
            ++groupCount

            if (groupCount == k) {
                var groupCur = groupDummy.next

                groupCount = 0
                groupPrev = groupDummy
                groupDummy.next = null

                while(groupCur != null) {
                    val groupNode = groupCur
                    groupCur = groupCur.next
                    groupNode.next = null

                    groupNode.next = prev.next
                    prev.next = groupNode
                }

                while(prev.next != null) {
                    prev = prev.next!!
                }
            }
        }
        prev.next = groupDummy.next
        return dummyHead.next
    }
}

fun main() {
    val s = Solution0025()
    println(s.reverseKGroup(ListNode(intArrayOf(1, 2, 3, 4, 5)), 3))
    println(s.reverseKGroup(ListNode(intArrayOf(1, 2, 3, 4, 5)), 2))
    println(s.reverseKGroup(ListNode(intArrayOf(1, 2, 3, 4, 5)), 1))
}
/*
Runtime: 188 ms, faster than 64.86% of Kotlin online submissions for Reverse Nodes in k-Group.
Memory Usage: 34.7 MB, less than 70.00% of Kotlin online submissions for Reverse Nodes in k-Group.
 */