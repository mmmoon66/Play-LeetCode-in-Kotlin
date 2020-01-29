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
class Solution0145_v2 {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res
        val stack = Stack<TreeNode>().apply { push(root) }
        while(stack.isNotEmpty()) {
            val top = stack.pop()
            if (top.left == null && top.right == null) {
                res.add(top.`val`)
            } else {
                stack.push(TreeNode(top.`val`))
                if (top.right != null) stack.push(top.right)
                if (top.left != null) stack.push(top.left)
            }
        }
        return res
    }
}
/*
Runtime: 164 ms, faster than 42.31% of Kotlin online submissions for Binary Tree Postorder Traversal.
Memory Usage: 39 MB, less than 100.00% of Kotlin online submissions for Binary Tree Postorder Traversal.
 */