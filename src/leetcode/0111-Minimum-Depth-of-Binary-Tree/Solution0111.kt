// https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
// 111. Minimum Depth of Binary Tree
class Solution0111 {
    fun minDepth(root: TreeNode?): Int {
        root ?: return 0
        root.left ?: return 1 + minDepth(root.right)
        root.right ?: return 1 + minDepth(root.left)
        return 1 + minOf(minDepth(root.left), minDepth(root.right))
    }
}