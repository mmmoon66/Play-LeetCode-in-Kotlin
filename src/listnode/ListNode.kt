package listnode

import ListNode
import java.util.*

class Solution160 {
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var a = headA
        var b = headB
        while (a != b) {
            a = if (a == null) headB else a.next
            b = if (b == null) headA else b.next
        }
        return a
    }
}

// https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
class Solution19 {
    // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummyHead = ListNode(-1).apply { next = head }
        var fast: ListNode? = dummyHead
        for (i in 1..n) {
            fast = fast?.next
        }
        var slow: ListNode? = dummyHead
        while (fast?.next != null) {
            fast = fast.next
            slow = slow?.next
        }
        val delNode = slow?.next
        slow?.next = delNode?.next
        delNode?.next = null
        return dummyHead.next
    }
}

class Solution21 {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        val dummyHead = ListNode(-1)
        var a = l1
        var b = l2
        var cur = dummyHead
        while (a != null && b != null) {
            if (a.`val` < b.`val`) {
                val node = a
                a = a.next
                node.next = null
                cur.next = node
                cur = cur.next!!
            } else {
                val node = b
                b = b.next
                node.next = null
                cur.next = node
                cur = cur.next!!
            }
        }
        if (a != null) cur.next = a
        if (b != null) cur.next = b
        return dummyHead.next
    }
}

class Solution23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val dummyHead = ListNode(-1)
        var cur: ListNode? = dummyHead
        while (true) {
            var min = Int.MAX_VALUE
            var index = -1
            lists.forEachIndexed { i, list ->
                if (list != null && list.`val` < min) {
                    min = list.`val`
                    index = i
                }
            }
            if (index == -1) break
            var list = lists[index]
            val node = list
            list = list?.next
            node?.next = null
            lists[index] = list

            cur?.next = node
            cur = cur?.next
        }
        return dummyHead.next
    }
}

class Solution23V2 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val dummyHead = ListNode(-1)
        var cur: ListNode? = dummyHead
        val pq = PriorityQueue<ListNode> { a, b -> a.`val` - b.`val` }
        lists.forEach {
            if (it != null) pq.offer(it)
        }
        while (pq.isNotEmpty()) {
            var list = pq.poll()
            // 一个小优化，如果list是当前唯一的链表，则加到后面直接结束
            if (pq.isEmpty()) {
                cur?.next = list
                break
            }

            val node = list
            list = list.next
            node.next = null
            list?.let { pq.offer(it) }

            cur?.next = node
            cur = cur?.next
        }
        return dummyHead.next
    }
}

class Solution24 {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) return head
        val next = head.next
        head.next = swapPairs(next?.next)
        next?.next = null
        next?.next = head
        return next
    }
}

class Solution25 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        var h = head
        repeat(k - 1) {
            h = h?.next
        }
        if (h == null) return head
        val next = reverseKGroup(h?.next, k)
        h?.next = null
        val dummyHead = ListNode(-1)
        h = head
        while (h != null) {
            val node = h
            h = h?.next
            node?.next = dummyHead.next ?: next//如果dummyHead.next等于null，说明这是头节点，则将后面的部分加在这个节点后面
            dummyHead.next = node
        }
        return dummyHead.next
    }
}

class Solution61 {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null || k == 0) return head
        var node = head
        var count = 0
        var end: ListNode? = null
        while (node != null) {
            if (node.next == null) end = node
            node = node.next
            ++count
        }
        val k = k % count
        if (k == 0) return head
        var prev = head
        repeat(count - k - 1) { prev = prev?.next }
        val newHead = prev?.next
        prev?.next = null
        end?.next = head
        return newHead
    }
}

class Solution82 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        val freq = mutableMapOf<Int, Int>()
        var node = head
        while (node != null) {
            freq[node.`val`] = freq.getOrDefault(node.`val`, 0) + 1
            node = node.next
        }
        val dummyHead = ListNode(-1)
        var cur = dummyHead
        node = head
        while (node != null) {
            val n = node
            node = node.next
            n.next = null

            if (freq.getOrDefault(n.`val`, 0) == 1) {
                cur.next = n
                cur = cur.next!!
            }
        }
        return dummyHead.next
    }
}

class Solution82V2 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        return if (head.`val` == head.next!!.`val`) {
            var prev = head.next
            while (prev?.next?.`val` == head.`val`) {
                prev = prev.next
            }
            deleteDuplicates(prev?.next)
        } else {
            head.next = deleteDuplicates(head.next)
            head
        }
    }
}


class Solution83 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        return if (head.`val` == head.next?.`val`) {
            deleteDuplicates(head.next)
        } else {
            head.next = deleteDuplicates(head.next)
            head
        }
    }
}

class Solution86 {
    fun partition(head: ListNode?, x: Int): ListNode? {
        val dummy1 = ListNode(-1).apply { next = head }
        val dummy2 = ListNode(-1)
        var prev: ListNode? = dummy1
        var cur: ListNode? = dummy2
        while (prev?.next != null) {
            if (prev.next!!.`val` < x) {
                val node = prev.next
                prev.next = node?.next
                node?.next = null

                cur?.next = node
                cur = cur?.next
            } else {
                prev = prev.next
            }
        }
        cur?.next = dummy1.next
        return dummy2.next
    }
}