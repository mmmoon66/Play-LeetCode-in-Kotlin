class Solution0337_v2 {
    fun rob(root: TreeNode?): Int {
        val (includeRoot, excludeRoot) = tryRob(root)
        return maxOf(includeRoot, excludeRoot)
    }

    private fun tryRob(root: TreeNode?): Pair<Int, Int> {
        if (root == null) return 0 to 0
        val (includeLeft, excludeLeft) = tryRob(root.left)
        val (includeRight, excludeRight) = tryRob(root.right)
        val includeRoot = root.`val` + excludeLeft + excludeRight
        val excludeRoot = maxOf(includeLeft, excludeLeft) + maxOf(includeRight, excludeRight)
        return includeRoot to excludeRoot
    }
}