import java.util.*

// https://leetcode.com/problems/average-of-levels-in-binary-tree/
// 637. Average of Levels in Binary Tree
/*
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].

Note:
The range of node's value is in the range of 32-bit signed integer.
 */
class Solution0637 {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        val res = arrayListOf<Double>()
        val queue = LinkedList<TreeNode>()
        if (root != null) {
            queue.add(root)
        }
        while(queue.isNotEmpty()) {
            val n = queue.size
            var sum = 0L
            for (i in 1..n) {
                val front = queue.removeFirst()
                sum += front.`val`
                if (front.left != null) {
                    queue.add(front.left!!)
                }
                if (front.right != null) {
                    queue.add(front.right!!)
                }
            }
            res.add(sum * 1.0 / n)
        }
        return res.toDoubleArray()
    }
}
/*
Runtime: 224 ms, faster than 95.65% of Kotlin online submissions for Average of Levels in Binary Tree.
Memory Usage: 48.6 MB, less than 100.00% of Kotlin online submissions for Average of Levels in Binary Tree.
 */