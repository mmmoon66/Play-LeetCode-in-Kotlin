import java.util.*

// https://leetcode.com/problems/binary-tree-preorder-traversal/
// 144. Binary Tree Preorder Traversal
/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
class Solution0144_v2 {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res
        val stack = Stack<TreeNode>().apply { push(root) }
        while(stack.isNotEmpty()) {
            val top = stack.pop()
            if (top.left == null && top.right == null) {
                res.add(top.`val`)
            } else {
                if (top.right != null) stack.push(top.right)
                if (top.left != null) stack.push(top.left)
                stack.push(TreeNode(top.`val`))
            }
        }
        return res
    }
}
/*
Runtime: 124 ms, faster than 87.69% of Kotlin online submissions for Binary Tree Preorder Traversal.
Memory Usage: 36.7 MB, less than 100.00% of Kotlin online submissions for Binary Tree Preorder Traversal.
 */