// https://leetcode.com/problems/subtree-of-another-tree/
// 572. Subtree of Another Tree
/*
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.

Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 */
class Solution0572_v2 {
    private fun __isSameTree(p: TreeNode?, q:TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        return p.`val` == q.`val` && __isSameTree(p.left, q.left) && __isSameTree(p.right, q.right)
    }

    fun isSubtree(s: TreeNode?, t: TreeNode?): Boolean {
        if (__isSameTree(s, t)) {
            return true
        }
        if (s != null && (isSubtree(s.left, t) || isSubtree(s.right, t))) {
            return true
        }
        return false
    }
}
/*
Runtime: 168 ms, faster than 100.00% of Kotlin online submissions for Subtree of Another Tree.
Memory Usage: 46.5 MB, less than 100.00% of Kotlin online submissions for Subtree of Another Tree.
 */