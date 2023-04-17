import java.util.*

// https://leetcode.com/problems/binary-tree-postorder-traversal/
// 145. Binary Tree Postorder Traversal
/*
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
class Solution0145 {
    private fun __postorder(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return
        __postorder(root.left, list)
        __postorder(root.right, list)
        list.add(root.`val`)
    }

    fun postorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        __postorder(root, res)
        return res
    }
}
/*
Runtime: 120 ms, faster than 86.54% of Kotlin online submissions for Binary Tree Postorder Traversal.
Memory Usage: 36.2 MB, less than 100.00% of Kotlin online submissions for Binary Tree Postorder Traversal.
 */

class Solution0145V2 {
    fun postorderTraversal(root: TreeNode?): List<Int> {
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
                // left->right->node => node->right->left
                stack.push(node)
                right?.let { stack.push(it) }
                left?.let { stack.push(it) }
            }
        }
        return res
    }
}

fun main() {
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3)
    }
    val s = Solution0145V2()
    println(s.postorderTraversal(root) == listOf(4, 5, 2, 3, 1))
}