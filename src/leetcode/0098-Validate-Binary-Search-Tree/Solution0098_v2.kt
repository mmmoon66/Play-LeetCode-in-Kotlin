// https://leetcode.com/problems/validate-binary-search-tree/
// 98. Validate Binary Search Tree
class Solution0098_v2 {
    fun isValidBST(root: TreeNode?): Boolean {
        val values = mutableListOf<Int>()
        inOrder(root, values)
        for (i in 0 until values.size - 1) {
            if (values[i] >= values[i + 1]) return false
        }
        return true
    }

    private fun inOrder(root: TreeNode?, values: MutableList<Int>) {
        if (root == null) {
            return
        }
        inOrder(root.left, values)
        values.add(root.`val`)
        inOrder(root.right, values)
    }
}
/*
Runtime: 180 ms, faster than 47.20% of Kotlin online submissions for Validate Binary Search Tree.
Memory Usage: 36.2 MB, less than 100.00% of Kotlin online submissions for Validate Binary Search Tree.
 */