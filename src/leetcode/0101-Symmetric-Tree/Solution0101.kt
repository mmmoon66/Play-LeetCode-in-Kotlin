// https://leetcode.com/problems/symmetric-tree/
// 101. Symmetric Tree
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3


Note:
Bonus points if you could solve it both recursively and iteratively.
 */
class Solution0101 {
    fun __isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        return p.`val` == q.`val` && __isSameTree(p.left, q.left) && __isSameTree(p.right, q.right)
    }

    fun __invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null
        root.left = __invertTree(root.right).also { root.right = __invertTree(root.left) }
        return root
    }


    fun isSymmetric(root: TreeNode?): Boolean {
        return root == null || __isSameTree(root.left, __invertTree(root.right))
    }
}
/*
Runtime: 136 ms, faster than 98.78% of Kotlin online submissions for Symmetric Tree.
Memory Usage: 42.2 MB, less than 100.00% of Kotlin online submissions for Symmetric Tree.
 */