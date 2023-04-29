// https://leetcode.com/problems/rotate-image/
// 48. Rotate Image
/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],
rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],
rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */
class Solution0048 {
    fun rotate(matrix: Array<IntArray>): Unit {
        // [1,2,3]
        // [4,5,6]
        // [7,8,9]
        // 先上下翻转得到
        // [7,8,9]
        // [4,5,6]
        // [1,2,3]
        val n = matrix.size
        if (n <= 1) return
        var i = 0
        var j = n - 1
        while (i < j) {
            matrix[i] = matrix[j].also { matrix[j] = matrix[i] }
            ++i
            --j
        }
        // 再沿着左上到右下的对角线翻转
        // [7,4,1]
        // [8,5,2]
        // [9,6,3]
        for (u in 0 until n) {
            for (v in 0 until u) {
                matrix[u][v] = matrix[v][u].also { matrix[v][u] = matrix[u][v] }
            }
        }
    }
}

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    println(Solution0048().rotate(matrix))
    matrix.forEach { println(it.contentToString()) }
}
/*
Runtime: 140 ms, faster than 91.38% of Kotlin online submissions for Rotate Image.
Memory Usage: 37.5 MB, less than 100.00% of Kotlin online submissions for Rotate Image.
 */