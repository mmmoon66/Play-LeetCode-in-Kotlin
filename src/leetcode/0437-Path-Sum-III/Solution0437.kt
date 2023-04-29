// https://leetcode.com/problems/path-sum-iii/
// 437. Path Sum III
/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */
class Solution0437 {

    private var count = 0

    private fun __pathSumStartAtRoot(root: TreeNode?, sum: Int): Int {
        if (root == null) return 0
        if (root.`val` == sum) {
            return 1 + __pathSumStartAtRoot(root.left, sum - root.`val`) + __pathSumStartAtRoot(root.right, sum - root.`val`)
        } else {
            return __pathSumStartAtRoot(root.left, sum - root.`val`) + __pathSumStartAtRoot(root.right, sum - root.`val`)
        }
    }

    private fun __traverseTree(root: TreeNode?, sum: Int) {
        if (root == null) return
        count += __pathSumStartAtRoot(root, sum)
        __traverseTree(root.left, sum)
        __traverseTree(root.right, sum)
    }

    fun pathSum(root: TreeNode?, sum: Int): Int {
        count = 0
        __traverseTree(root, sum)
        return count
    }
}
/*
Runtime: 168 ms, faster than 100.00% of Kotlin online submissions for Path Sum III.
Memory Usage: 42.6 MB, less than 100.00% of Kotlin online submissions for Path Sum III.
 */