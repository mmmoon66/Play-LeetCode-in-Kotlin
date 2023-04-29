// https://leetcode.com/problems/binary-tree-pruning/
// 814. Binary Tree Pruning
class Solution0814 {
    fun pruneTree(root: TreeNode?): TreeNode? {
        if (root == null) return null
        root.left = pruneTree(root.left)
        root.right = pruneTree(root.right)
        if (root.`val` == 0 && root.left == null && root.right == null) return null
        return root
    }
}
/*
Runtime: 92 ms, faster than 100.00% of Kotlin online submissions for Binary Tree Pruning.
Memory Usage: 36.2 MB, less than 100.00% of Kotlin online submissions for Binary Tree Pruning.
 */