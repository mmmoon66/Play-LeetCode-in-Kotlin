// https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
// 95. Unique Binary Search Trees II
class Solution0095 {
    fun generateTrees(n: Int): List<TreeNode?> {
        return generateTrees(1, n)
    }

    private fun generateTrees(l: Int, r: Int): List<TreeNode?> {
        val res = mutableListOf<TreeNode?>()
        if (l > r) {
            res.add(null)
            return res
        }
        if (l == r) {
            res.add(TreeNode(l))
            return res
        }
        for (i in l..r) {
            val leftNodes = generateTrees(l, i - 1)
            val rightNodes = generateTrees(i + 1, r)
            for (left in leftNodes) {
                for (right in rightNodes) {
                    val root = TreeNode(i).apply {
                        this.left = left
                        this.right = right
                    }
                    res.add(root)
                }
            }
        }
        return res
    }
}