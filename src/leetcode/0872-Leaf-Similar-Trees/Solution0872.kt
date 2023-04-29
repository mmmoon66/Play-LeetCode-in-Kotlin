// https://leetcode.com/problems/leaf-similar-trees/
// 872. Leaf-Similar Trees
class Solution0872 {
    fun __helper(root: TreeNode?, leaves: ArrayList<Int>) {
        if (root == null) return
        if (root.left == null && root.right == null) {
            leaves.add(root.`val`)
        } else {
            __helper(root.left, leaves)
            __helper(root.right, leaves)
        }
    }

    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val leaves1 = arrayListOf<Int>()
        val leaves2 = arrayListOf<Int>()
        __helper(root1, leaves1)
        __helper(root2, leaves2)
        return leaves1 == leaves2
    }
}
/*
Runtime: 84 ms, faster than 100.00% of Kotlin online submissions for Leaf-Similar Trees.
Memory Usage: 36.7 MB, less than 100.00% of Kotlin online submissions for Leaf-Similar Trees.
 */