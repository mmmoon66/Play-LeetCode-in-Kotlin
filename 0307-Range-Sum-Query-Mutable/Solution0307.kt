import java.awt.font.NumericShaper
import java.lang.IllegalArgumentException

// https://leetcode.com/problems/range-sum-query-mutable/
// 307. Range Sum Query - Mutable
/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:
Given nums = [1, 3, 5]
sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 */
class Solution0307 {
    class NumArray(nums: IntArray) {
        private class Node(var `val`: Int, var startIndex: Int, var endIndex: Int, var left: Node?, var right: Node?)
        private var root = buildSegmentTree(nums, 0, nums.size - 1)
        private var data = nums

        private fun buildSegmentTree(nums: IntArray, startIndex: Int, endIndex: Int): Node? {
            if (startIndex > endIndex) {
                return null
            }
            if (startIndex == endIndex) {
                return Node(nums[startIndex], startIndex, endIndex, null, null)
            }
            val mid = startIndex + (endIndex - startIndex) / 2
            val left = buildSegmentTree(nums, startIndex, mid)
            val right = buildSegmentTree(nums, mid + 1, endIndex)
            val sum = left!!.`val` + right!!.`val`
            return Node(sum, startIndex, endIndex, left, right)
        }

        fun update(i: Int, `val`: Int) {
            if (i < 0 || i >= data.size) {
                return
            }
            data[i] = `val`
            update(i, `val`, root!!)
        }

        private fun update(i: Int, `val`: Int, node: Node) {
            if (node.startIndex == node.endIndex && node.startIndex == i) {
                node.`val` = `val`
                return
            }
            val mid = node.startIndex + (node.endIndex - node.startIndex) / 2
            if (i <= mid) {
                update(i, `val`, node.left!!)
            } else {
                update(i, `val`, node.right!!)
            }
            node.`val` = node.left!!.`val` + node.right!!.`val`
        }

        fun sumRange(i: Int, j: Int): Int {
            if (i < 0 || j >= data.size || i > j) {
                throw IllegalArgumentException()
            }
            return sumRange(i, j, root!!)
        }

        private fun sumRange(i: Int, j: Int, node: Node): Int {
            if (i == node.startIndex && j == node.endIndex) {
                return node.`val`
            }
            val mid = node.startIndex + (node.endIndex - node.startIndex) / 2
            if (j <= mid) {
                return sumRange(i, j, node.left!!)
            } else if (i > mid) {
                return sumRange(i, j, node.right!!)
            } else {
                return sumRange(i, mid, node.left!!) + sumRange(mid + 1, j, node.right!!)
            }
        }
    }
}

fun main() {
    val numArray = Solution0307.NumArray(intArrayOf(1, 3, 5))
    println(numArray.sumRange(0, 2))
    numArray.update(1, 2)
    println(numArray.sumRange(0, 2))
}
/*
Runtime: 280 ms, faster than 100.00% of Kotlin online submissions for Range Sum Query - Mutable.
Memory Usage: 46.1 MB, less than 100.00% of Kotlin online submissions for Range Sum Query - Mutable.
 */
