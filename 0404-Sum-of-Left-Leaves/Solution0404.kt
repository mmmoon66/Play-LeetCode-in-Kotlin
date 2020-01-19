// https://leetcode.com/problems/sum-of-left-leaves/
// 404. Sum of Left Leaves
/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
class Solution0404 {
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        if (root == null) return 0
        val left = root.left
        var sum = 0
        if (left != null) {
            if (left.left == null && left.right == null) {
                sum += left.`val`
            } else {
                sum += sumOfLeftLeaves(left)
            }
        }
        sum += sumOfLeftLeaves(root.right)
        return sum
    }
}
/*
Runtime: 100 ms, faster than 100.00% of Kotlin online submissions for Sum of Left Leaves.
Memory Usage: 36.7 MB, less than 100.00% of Kotlin online submissions for Sum of Left Leaves.
 */