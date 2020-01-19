// https://leetcode.com/problems/convert-bst-to-greater-tree/
// 538. Convert BST to Greater Tree
/*
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */
class Solution0538 {
    var sum = 0

    fun __postOrder(root: TreeNode?) {
        if (root == null) {
            return
        }
        __postOrder(root.right)
        sum += root.`val`
        root.`val` = sum
        __postOrder(root.left)
    }

    fun convertBST(root: TreeNode?): TreeNode? {
        sum = 0
        __postOrder(root)
        return root
    }
}
/*
Runtime: 192 ms, faster than 100.00% of Kotlin online submissions for Convert BST to Greater Tree.
Memory Usage: 43.3 MB, less than 100.00% of Kotlin online submissions for Convert BST to Greater Tree.
 */