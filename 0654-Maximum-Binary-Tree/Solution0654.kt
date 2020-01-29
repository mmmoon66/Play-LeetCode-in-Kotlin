// https://leetcode.com/problems/maximum-binary-tree/
// 654. Maximum Binary Tree
/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:
      6
    /   \
   3     5
    \    /
     2  0
       \
        1
Note:
The size of the given array will be in the range [1,1000].
 */
class Solution0654 {
    private fun __constructMaximumBinaryTree(nums: IntArray, L: Int, R: Int): TreeNode? {
        if (L > R) {
            return null
        }
        if (L == R) {
            return TreeNode(nums[L])
        }
        var maxIndex = L
        for (i in L+1..R) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i
            }
        }
        val root = TreeNode(nums[maxIndex])
        root.left = __constructMaximumBinaryTree(nums, L, maxIndex - 1)
        root.right = __constructMaximumBinaryTree(nums, maxIndex + 1, R)
        return root
    }

    fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
        return __constructMaximumBinaryTree(nums, 0, nums.size - 1)
    }
}
/*
Runtime: 248 ms, faster than 77.78% of Kotlin online submissions for Maximum Binary Tree.
Memory Usage: 45.8 MB, less than 100.00% of Kotlin online submissions for Maximum Binary Tree.
 */