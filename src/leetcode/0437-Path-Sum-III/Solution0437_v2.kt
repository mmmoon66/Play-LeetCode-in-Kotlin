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
class Solution0437_v2 {
    fun pathSum(root: TreeNode?, sum: Int): Int {
        if (root == null) return 0
        return __findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum)
    }

    // 经过root并且和等于sum的路径数
    private fun __findPath(root: TreeNode?, sum: Int): Int {
        if (root == null) return 0
        var res = 0
        if (root.`val` == sum) ++res
        res += __findPath(root.left, sum - root.`val`)
        res += __findPath(root.right, sum - root.`val`)
        return res
    }
}
/*
Runtime: 164 ms, faster than 100.00% of Kotlin online submissions for Path Sum III.
Memory Usage: 42.7 MB, less than 100.00% of Kotlin online submissions for Path Sum III.
 */