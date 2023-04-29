class Solution0019 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummyHead = ListNode(-1)
        dummyHead.next = head
        var fast: ListNode? = dummyHead
        repeat(n) {
            fast = fast?.next
        }
        var slow: ListNode? = dummyHead
        while (fast?.next != null) {
            fast = fast?.next
            slow = slow?.next
        }
        val delNode = slow?.next
        slow?.next = delNode?.next
        delNode?.next = null
        return dummyHead.next
    }
}

fun main() {
    val s = Solution0019()
    println(s.removeNthFromEnd(ListNode(intArrayOf(1, 2, 3, 4, 5)), 2))
    println(s.removeNthFromEnd(ListNode(intArrayOf(1)), 1))
    println(s.removeNthFromEnd(ListNode(intArrayOf(1, 2)), 1))
}