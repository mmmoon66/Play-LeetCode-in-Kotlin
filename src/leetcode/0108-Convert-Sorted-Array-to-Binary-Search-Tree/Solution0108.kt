// https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
// 108. Convert Sorted Array to Binary Search Tree
/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0108 {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return sortedArrayToBST(nums, 0, nums.size - 1)
    }

    private fun sortedArrayToBST(nums: IntArray, l: Int, r: Int): TreeNode? {
        if (l > r) return null
        val mid = l + (r - l) / 2
        val root = TreeNode(nums[mid])
        root.left = sortedArrayToBST(nums, l, mid - 1)
        root.right = sortedArrayToBST(nums, mid + 1, r)
        return root
    }
}