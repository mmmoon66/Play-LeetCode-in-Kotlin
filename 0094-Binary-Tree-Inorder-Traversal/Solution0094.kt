import java.util.*

// https://leetcode.com/problems/binary-tree-inorder-traversal/
// 94. Binary Tree Inorder Traversal
/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
class Solution0094 {
    private fun __inorder(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return
        __inorder(root.left, list)
        list.add(root.`val`)
        __inorder(root.right, list)
    }

    fun inorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        __inorder(root, res)
        return res
    }
}
/*
Runtime: 124 ms, faster than 87.38% of Kotlin online submissions for Binary Tree Inorder Traversal.
Memory Usage: 36.8 MB, less than 100.00% of Kotlin online submissions for Binary Tree Inorder Traversal.
 */

class Solution0094V2 {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        root ?: return res
        val stack = Stack<TreeNode>().apply { push(root) }
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            val left = node.left
            val right = node.right
            node.left = null
            node.right = null
            if (left == null && right == null) {
                res.add(node.`val`)
            } else {
                // left->node->right => right->node->left
                right?.let { stack.push(it) }
                stack.push(node)
                left?.let { stack.push(it) }
            }
        }
        return res
    }
}