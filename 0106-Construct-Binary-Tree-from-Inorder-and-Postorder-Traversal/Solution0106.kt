// https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// 106. Construct Binary Tree from Inorder and Postorder Traversal
/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0106 {
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        return buildTree(inorder, 0, inorder.lastIndex, postorder, 0, postorder.lastIndex)
    }

    private fun buildTree(
        inorder: IntArray,
        inorderL: Int,
        inorderR: Int,
        postorder: IntArray,
        postorderL: Int,
        postorderR: Int
    ): TreeNode? {
        if (inorderL > inorderR) return null
        if (inorderL == inorderR) return TreeNode(inorder[inorderL])
        val rootValue = postorder[postorderR]
        var inorderRootIndex = inorderL
        while (inorderRootIndex <= inorderR && inorder[inorderRootIndex] != rootValue) ++inorderRootIndex
        val leftCount = inorderRootIndex - inorderL
        val root = TreeNode(rootValue)
        root.left = buildTree(inorder, inorderL, inorderRootIndex - 1, postorder, postorderL, postorderL + leftCount - 1)
        root.right = buildTree(inorder, inorderRootIndex + 1, inorderR, postorder, postorderL + leftCount, postorderR - 1)
        return root
    }
}