import java.util.*

// https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
// 107. Binary Tree Level Order Traversal II
/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution0107 {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        root ?: return res
        val queue = LinkedList<TreeNode>().apply { offer(root) }
        while (queue.isNotEmpty()) {
            val size = queue.size
            val level = mutableListOf<Int>()
            repeat(size) {
                val node = queue.poll()
                level.add(node.`val`)
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
            res.add(0, level)
        }
        return res
    }
}
/*
执行用时：192 ms, 在所有 Kotlin 提交中击败了100.00%的用户
内存消耗：34.6 MB, 在所有 Kotlin 提交中击败了85.19%的用户
 */