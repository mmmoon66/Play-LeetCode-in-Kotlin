import java.util.*

// https://leetcode.com/problems/binary-tree-level-order-traversal/
// 102. Binary Tree Level Order Traversal
/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
class Solution0102 {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (root == null) return res
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val count = queue.size
            val level = mutableListOf<Int>()
            for (i in 1..count) {
                val front = queue.poll()
                level.add(front.`val`)
                if (front.left != null) queue.offer(front.left)
                if (front.right != null) queue.offer(front.right)
            }
            res.add(level)
        }
        return res
    }
}
/*
Runtime: 136 ms, faster than 100.00% of Kotlin online submissions for Binary Tree Level Order Traversal.
Memory Usage: 38.2 MB, less than 100.00% of Kotlin online submissions for Binary Tree Level Order Traversal.
 */