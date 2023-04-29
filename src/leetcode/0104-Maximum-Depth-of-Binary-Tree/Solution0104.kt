import kotlin.math.max

// https://leetcode.com/problems/maximum-depth-of-binary-tree/
// 104. Maximum Depth of Binary Tree
/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
 */
class Solution0104 {
    fun maxDepth(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else {
            1 + max(maxDepth(root.left), maxDepth(root.right))
        }
    }
}
/*
Runtime: 136 ms, faster than 98.37% of Kotlin online submissions for Maximum Depth of Binary Tree.
Memory Usage: 41.8 MB, less than 100.00% of Kotlin online submissions for Maximum Depth of Binary Tree.
 */