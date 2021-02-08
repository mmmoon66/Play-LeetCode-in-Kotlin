import java.util.*

// https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
// 116. Populating Next Right Pointers in Each Node
class Solution0116 {
    fun connect(root: Node?): Node? {
        root?: return root
        val queue: LinkedList<Node> = LinkedList()
        queue.offer(root)
        while(queue.isNotEmpty()) {
            val count = queue.size
            for (i in 0 until count) {
                val front = queue.poll()
                if (i == count - 1) {
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
执行用时：256 ms, 在所有 Kotlin 提交中击败了35.29%的用户
内存消耗：36.3 MB, 在所有 Kotlin 提交中击败了44.12%的用户
 */




