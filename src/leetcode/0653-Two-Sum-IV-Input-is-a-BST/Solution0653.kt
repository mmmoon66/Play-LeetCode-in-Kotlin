// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
// 653. Two Sum IV - Input is a BST
/*
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input:
    5
   / \
  3   6
 / \   \
2   4   7
Target = 9
Output: True

Example 2:
Input:
    5
   / \
  3   6
 / \   \
2   4   7
Target = 28
Output: False
 */
class Solution0653 {
    fun __inOrder(root: TreeNode?, nums: MutableList<Int>) {
        if (root == null) {
            return
        }
        __inOrder(root.left, nums)
        nums.add(root.`val`)
        __inOrder(root.right, nums)
    }

    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val nums = mutableListOf<Int>()
        __inOrder(root, nums)
        val n = nums.size
        var i = 0
        var j = n - 1
        while(i < j) {
            val sum = nums[i] + nums[j]
            if (sum == k) {
                return true
            } else if (sum < k) {
                ++i
            } else {
                --j
            }
        }
        return false
    }
}
/*
Runtime: 208 ms, faster than 100.00% of Kotlin online submissions for Two Sum IV - Input is a BST.
Memory Usage: 51 MB, less than 100.00% of Kotlin online submissions for Two Sum IV - Input is a BST.
 */