// https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
// 129. Sum Root to Leaf Numbers
/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0129 {
    private var sum = 0

    fun sumNumbers(root: TreeNode?): Int {
        sum = 0
        preorder(root, 0)
        return sum
    }

    private fun preorder(node: TreeNode?, base: Int) {
        node ?: return
        val newBase = 10 * base + node.`val`
        if (node.left == null && node.right == null) {
            sum += newBase
            return
        }
        preorder(node.left, newBase)
        preorder(node.right, newBase)
    }
}
/*
执行用时：204 ms, 在所有 Kotlin 提交中击败了28.57%的用户
内存消耗：32.5 MB, 在所有 Kotlin 提交中击败了100.00%的用户
 */