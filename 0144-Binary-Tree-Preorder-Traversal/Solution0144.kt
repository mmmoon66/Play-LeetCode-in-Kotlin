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
class Solution0144 {
    private fun __preorder(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return
        list.add(root.`val`)
        __preorder(root.left, list)
        __preorder(root.right, list)
    }

    fun preorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        __preorder(root, res)
        return res
    }
}
/*
Runtime: 116 ms, faster than 90.77% of Kotlin online submissions for Binary Tree Preorder Traversal.
Memory Usage: 36.8 MB, less than 100.00% of Kotlin online submissions for Binary Tree Preorder Traversal.
 */