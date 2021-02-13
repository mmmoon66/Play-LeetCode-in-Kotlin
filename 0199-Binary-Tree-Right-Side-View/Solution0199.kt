import java.util.*

// https://leetcode-cn.com/problems/binary-tree-right-side-view/
// 199. Binary Tree Right Side View
/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0199 {
    fun rightSideView(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        root?:return res
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while(queue.isNotEmpty()) {
            val count = queue.size
            for (i in 1..count) {
                val front = queue.poll()
                if (i == count) res.add(front.`val`)
                front.left?.let { queue.add(it) }
                front.right?.let { queue.add(it) }
            }
        }
        return res
    }
}