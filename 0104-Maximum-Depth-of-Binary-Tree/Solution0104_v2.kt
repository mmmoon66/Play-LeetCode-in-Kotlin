import java.util.*

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
class Solution0104_v2 {
    // DFS
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val nodeStack = Stack<TreeNode>()
        val depthStack = Stack<Int>()
        nodeStack.push(root)
        depthStack.push(1)
        var maxDepth = 0
        while(nodeStack.isNotEmpty()) {
            val node = nodeStack.pop()
            val depth = depthStack.pop()
            maxDepth = maxOf(maxDepth, depth)

            if (node.left != null) {
                nodeStack.push(node.left)
                depthStack.push(depth + 1)
            }
            if (node.right != null) {
                nodeStack.push(node.right)
                depthStack.push(depth + 1)
            }
        }
        return maxDepth
    }
}
/*
Runtime: 200 ms, faster than 17.54% of Kotlin online submissions for Maximum Depth of Binary Tree.
Memory Usage: 34.3 MB, less than 37.18% of Kotlin online submissions for Maximum Depth of Binary Tree.
 */