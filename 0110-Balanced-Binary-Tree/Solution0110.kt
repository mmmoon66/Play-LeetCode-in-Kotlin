import kotlin.math.abs

// https://leetcode.com/problems/balanced-binary-tree/
// 110. Balanced Binary Tree
/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

Example 1:
Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:
Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
 */
class Solution0110 {
    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
    }

    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true
        return abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right)
    }
}
/*
Runtime: 148 ms, faster than 95.83% of Kotlin online submissions for Balanced Binary Tree.
Memory Usage: 43.4 MB, less than 100.00% of Kotlin online submissions for Balanced Binary Tree.
 */