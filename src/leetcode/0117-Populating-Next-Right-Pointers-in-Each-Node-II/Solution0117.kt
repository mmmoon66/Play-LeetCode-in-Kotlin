import java.util.*

// https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
// 117. Populating Next Right Pointers in Each Node II
class Solution0117 {
    fun connect(root: Node?): Node? {
        root?:return root
        val queue = LinkedList<Node>()
        queue.offer(root)
        while(queue.isNotEmpty()) {
            val count = queue.size
            for (i in 1..count) {
                val front = queue.poll()
                if (i == count) {
                    front.next = null
                } else {
                    front.next = queue.peek()
                }
                front.left?.let { queue.offer(it) }
                front.right?.let { queue.offer(it) }
            }
        }
        return root
    }

    // Definition for a Node.
    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }
}
/*
执行用时：244 ms, 在所有 Kotlin 提交中击败了42.86%的用户
内存消耗：36.4 MB, 在所有 Kotlin 提交中击败了28.57%的用户
 */