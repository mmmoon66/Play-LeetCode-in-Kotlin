import java.util.*

// https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
// 111. Minimum Depth of Binary Tree
class Solution0111_v2 {
    fun minDepth(root: TreeNode?): Int {
        root ?: return 0
        val queue: LinkedList<TreeNode> = LinkedList()
        queue.offer(root)
        var depth = 0
        while(queue.isNotEmpty()) {
            ++depth
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                val isLeaf = node.left == null && node.right == null
                if (isLeaf) return depth
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
        }
        return depth
    }
}