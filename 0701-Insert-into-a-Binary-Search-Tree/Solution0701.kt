// https://leetcode.com/problems/insert-into-a-binary-search-tree/
// 701. Insert into a Binary Search Tree
/*
Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example,

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \
    1   3
         \
          4
 */
class Solution0701 {
    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) {
            return TreeNode(`val`)
        }
        if (`val` < root.`val`) {
            root.left = insertIntoBST(root.left, `val`)
        } else {
            root.right = insertIntoBST(root.right, `val`)
        }
        return root
    }
}
/*
Runtime: 284 ms, faster than 31.25% of Kotlin online submissions for Insert into a Binary Search Tree.
Memory Usage: 49.2 MB, less than 100.00% of Kotlin online submissions for Insert into a Binary Search Tree.
 */