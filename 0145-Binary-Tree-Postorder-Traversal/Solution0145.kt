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