// https://leetcode.com/problems/invert-binary-tree/
// 226. Invert Binary Tree
/*
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 */
class Solution0226 {
    fun invertTree(root: TreeNode?): TreeNode? {
        return if (root == null) {
            null
        } else {
            root.left = invertTree(root.right).also { root.right = invertTree(root.left) }
            root
        }
    }
}
/*
Runtime: 96 ms, faster than 100.00% of Kotlin online submissions for Invert Binary Tree.
Memory Usage: 36.4 MB, less than 100.00% of Kotlin online submissions for Invert Binary Tree.
 */