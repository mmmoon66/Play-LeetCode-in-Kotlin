// https://leetcode.com/problems/range-sum-of-bst/
// 938. Range Sum of BST
/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
The binary search tree is guaranteed to have unique values.

Example 1:
Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2:
Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23

Note:
The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
 */

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution0938 {
    var sum = 0

    fun __inOrder(root: TreeNode?, L: Int, R: Int) {
        if (root == null) {
            return
        }
        __inOrder(root.left, L, R)
        if (root.`val` in L..R) sum += root.`val`
        __inOrder(root.right, L, R)
    }

    fun rangeSumBST(root: TreeNode?, L: Int, R: Int): Int {
        sum = 0
        __inOrder(root, L, R)
        return sum
    }
}
/*
Runtime: 332 ms, faster than 23.60% of Kotlin online submissions for Range Sum of BST.
Memory Usage: 54.7 MB, less than 25.00% of Kotlin online submissions for Range Sum of BST.
 */