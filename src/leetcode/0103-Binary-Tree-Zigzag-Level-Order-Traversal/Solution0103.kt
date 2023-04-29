import java.util.*

// 103. Binary Tree Zigzag Level Order Traversal
/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution0103 {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (root == null) return res
        val q = LinkedList<TreeNode>()
        q.offer(root)
        var reverse = false
        while(q.isNotEmpty()) {
            val size = q.size
            val level = mutableListOf<Int>()
            repeat(size) {
                val front = q.poll()
                level.add(front.`val`)
                front.left?.let { q.offer(it) }
                front.right?.let { q.offer(it) }
            }
            if (reverse) level.reverse()
            res.add(level)
            reverse = !reverse
        }
        return res
    }
}

fun main() {
//    val s = Solution0103()
//    val root = TreeNode(3)
//    root.left = TreeNode(9)
//    root.right = TreeNode(20)
//    root.right?.left = TreeNode(15)
//    root.right?.right = TreeNode(7)
//    val res = s.zigzagLevelOrder(root)
//    println(res)

    //[1,2,3,4,null,null,5]
    val s = Solution0103()
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.right?.right = TreeNode(5)
    val res = s.zigzagLevelOrder(root)
    println(res)
}
/*
执行用时：216 ms, 在所有 Kotlin 提交中击败了 62.96% 的用户
内存消耗：33.6 MB, 在所有 Kotlin 提交中击败了 57.89% 的用户
 */