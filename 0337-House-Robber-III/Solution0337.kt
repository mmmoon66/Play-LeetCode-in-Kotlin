class Solution0337 {
    private val memo = mutableMapOf<TreeNode, Int>()

    fun rob(root: TreeNode?): Int {
        memo.clear()
        return tryRob(root)
    }

    private fun tryRob(root: TreeNode?): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null) return root.`val`
        if (memo[root] != null) return memo[root]!!
        return maxOf(
            root.`val` + tryRob(root.left?.left) + tryRob(root.left?.right) + tryRob(root.right?.left) + tryRob(root.right?.right),
            tryRob(root.left) + tryRob(root.right)
        ).also { memo[root] = it }
    }
}