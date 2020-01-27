// https://leetcode.com/problems/path-sum/
// 112. Path Sum
/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
class Solution0112 {
    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        if (root == null) return false
        if (root.`val` == sum && root.left == null && root.right == null) return true
        return hasPathSum(root.left, sum - root.`val`) || hasPathSum(root.right, sum - root.`val`)
    }
}
/*
Runtime: 148 ms, faster than 98.25% of Kotlin online submissions for Path Sum.
Memory Usage: 39.7 MB, less than 100.00% of Kotlin online submissions for Path Sum.
 */