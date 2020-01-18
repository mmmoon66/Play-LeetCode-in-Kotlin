// https://leetcode.com/problems/trim-a-binary-search-tree/
// 669. Trim a Binary Search Tree
/*
Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2

Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1
 */
class Solution0669 {
    fun trimBST(root: TreeNode?, L: Int, R: Int): TreeNode? {
        if (root == null) return null
        if (root.`val` < L) {
            return trimBST(root.right, L, R)
        } else if (root.`val` > R) {
            return trimBST(root.left, L, R)
        } else {
            root.left = trimBST(root.left, L, R)
            root.right = trimBST(root.right, L, R)
            return root
        }
    }
}
/*
Runtime: 168 ms, faster than 100.00% of Kotlin online submissions for Trim a Binary Search Tree.
Memory Usage: 44.4 MB, less than 33.33% of Kotlin online submissions for Trim a Binary Search Tree.
 */