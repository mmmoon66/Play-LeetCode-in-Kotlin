package lcof

import TreeNode

// 剑指 Offer 07. 重建二叉树
/*
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

例如，给出
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：
    3
   / \
  9  20
    /  \
   15   7

限制：
0 <= 节点个数 <= 5000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution007 {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return buildTree(preorder, 0, preorder.lastIndex, inorder, 0, inorder.lastIndex)
    }

    private fun buildTree(
            preorder: IntArray,
            preorderL: Int,
            preorderR: Int,
            inorder: IntArray,
            inorderL: Int,
            inorderR: Int
    ): TreeNode? {
        if (preorderL > preorderR) return null
        if (preorderL == preorderR) {
            assert(inorderL == inorderR)
            return TreeNode(preorder[preorderL])
        }
        val rootVal = preorder[preorderL]
        // 找到根节点在inorder数组中的索引
        var index = inorderL
        while(index <= inorderR && inorder[index] != rootVal) {
            ++index
        }
        // 左子树节点个数
        val leftCount = index - inorderL
        // 右子树节点个数
        val rightCount = inorderR - index

        val rootNode = TreeNode(rootVal)
        val leftNode = buildTree(preorder, preorderL + 1, preorderL + leftCount, inorder, inorderL, inorderL + leftCount - 1)
        val rightNode = buildTree(preorder, preorderL + leftCount + 1, preorderR, inorder, index + 1, inorderR)
        rootNode.left = leftNode
        rootNode.right = rightNode
        return rootNode
    }
}