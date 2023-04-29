import java.util.*

// https://leetcode.com/problems/binary-tree-preorder-traversal/
// 144. Binary Tree Preorder Traversal
/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
class Solution0144 {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        __preorder(root, res)
        return res
    }

    private fun __preorder(root: TreeNode?, res: MutableList<Int>) {
        if (root == null) return
        res.add(root.`val`)
        __preorder(root.left, res)
        __preorder(root.right, res)
    }
}
/*
Runtime: 116 ms, faster than 90.77% of Kotlin online submissions for Binary Tree Preorder Traversal.
Memory Usage: 36.8 MB, less than 100.00% of Kotlin online submissions for Binary Tree Preorder Traversal.
 */

class Solution0144V2 {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res
        val stack = Stack<TreeNode>().apply { push(root) }
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            val left = node.left
            val right = node.right
            node.left = null
            node.right = null
            if (left == null && right == null) {
                res.add(node.`val`)
            } else {
                // 正常操作顺序为：node -> left -> right, 使用栈来模拟的话，需要反过来, right -> left -> node 依次推入栈中
                right?.let { stack.push(it) }
                left?.let { stack.push(it) }
                stack.push(node)
            }
        }
        return res
    }
}
/*
执行用时：160 ms, 在所有 Kotlin 提交中击败了48.44%的用户
内存消耗：33.3 MB, 在所有 Kotlin 提交中击败了50.00%的用户
 */

fun main() {
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3)
    }
    val s = Solution0144V2()
    println(s.preorderTraversal(root) == listOf(1, 2, 4, 5, 3))
}