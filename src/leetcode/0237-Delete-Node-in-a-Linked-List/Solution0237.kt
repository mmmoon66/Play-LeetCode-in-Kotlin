// https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
// 237. Delete Node in a Linked List
class Solution0237 {
    fun deleteNode(node: ListNode?) {
        node?: return
        val delNode = node.next!!
        node.`val` = delNode.`val`
        node.next = delNode.next
        delNode.next = null
    }
}