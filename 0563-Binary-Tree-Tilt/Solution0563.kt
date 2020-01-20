import kotlin.math.abs

// https://leetcode.com/problems/binary-tree-tilt/
// 563. Binary Tree Tilt
/*
Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input:
         1
       /   \
      2     3
Output: 1
Explanation:
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1

Note:
The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.
 */
class Solution0563 {
    private var tilt = 0

    fun __sumOfBinaryTree(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val leftSum = __sumOfBinaryTree(root.left)
        val rightSum = __sumOfBinaryTree(root.right)
        tilt += abs(leftSum - rightSum)
        return root.`val` + leftSum + rightSum
    }

    fun findTilt(root: TreeNode?): Int {
        tilt = 0
        __sumOfBinaryTree(root)
        return tilt
    }
}
/*
Runtime: 188 ms, faster than 88.89% of Kotlin online submissions for Binary Tree Tilt.
Memory Usage: 45.4 MB, less than 100.00% of Kotlin online submissions for Binary Tree Tilt.
 */