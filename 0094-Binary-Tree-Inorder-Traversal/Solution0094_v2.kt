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
class Solution0094_v2 {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res
        val stack = Stack<TreeNode>().apply { push(root) }
        while(stack.isNotEmpty()) {
            val top = stack.pop()
            if (top.left == null && top.right == null) {
                res.add(top.`val`)
            } else {
                if (top.right != null) {
                    stack.push(top.right)
                }
                stack.push(TreeNode(top.`val`))
                if (top.left != null) {
                    stack.push(top.left)
                }
            }
        }
        return res
    }
}
/*
Runtime: 120 ms, faster than 88.35% of Kotlin online submissions for Binary Tree Inorder Traversal.
Memory Usage: 37 MB, less than 100.00% of Kotlin online submissions for Binary Tree Inorder Traversal.
 */