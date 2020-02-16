import java.util.*

// https://leetcode.com/problems/validate-binary-search-tree/
// 98. Validate Binary Search Tree
class Solution0098 {
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true
        val values = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        stack.push(root)
        while(stack.isNotEmpty()) {
            val top = stack.pop()
            if (top.left == null && top.right == null) {
                values.add(top.`val`)
            } else {
                if (top.right != null) {
                    stack.push(root.right)
                }
                stack.push(TreeNode(top.`val`))
                if (top.left != null) {
                    stack.push(root.left)
                }
            }
        }
        if (values.size <= 1) {
            return true
        }
        for (i in 0 until values.size - 1) {
            if (values[i] > values[i + 1]) {
                return false
            }
        }
        return true
    }
}
// Memory Limit Exceeded