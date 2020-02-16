// https://leetcode.com/problems/design-linked-list/
// 707. Design Linked List
class MyLinkedList() {

    /** Initialize your data structure here. */
    private class ListNode(var `val`: Int, var next: ListNode?) {
        constructor(`val`: Int): this(`val`, null)
    }

    private val dummyHead = ListNode(-1)
    private var size = 0

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    fun get(index: Int): Int {
        if (index < 0 || index >= size) {
            return -1
        }
        var curNode = dummyHead
        for (i in 0..index) {
            curNode = curNode.next!!
        }
        return curNode.`val`
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    fun addAtHead(`val`: Int) {
        val node = ListNode(`val`, dummyHead.next)
        dummyHead.next = node
        ++size
    }

    /** Append a node of value val to the last element of the linked list. */
    fun addAtTail(`val`: Int) {
        var prev = dummyHead
        while(prev.next != null) {
            prev = prev.next!!
        }
        prev.next = ListNode(`val`)
        ++size
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    fun addAtIndex(index: Int, `val`: Int) {
        if (index < 0 || index > size) {
            return
        }
        var prev = dummyHead
        for (i in 0 until index) {
            prev = prev.next!!
        }
        prev.next = ListNode(`val`, prev.next)
        ++size
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    fun deleteAtIndex(index: Int) {
        if (index < 0 || index >= size) {
            return
        }
        var prev = dummyHead
        for (i in 0 until index) {
            prev = prev.next!!
        }
        val delNode = prev.next
        prev.next = delNode?.next
        delNode?.next = null
        --size
    }
}
/*
Runtime: 240 ms, faster than 70.00% of Kotlin online submissions for Design Linked List.
Memory Usage: 44.3 MB, less than 100.00% of Kotlin online submissions for Design Linked List.
 */