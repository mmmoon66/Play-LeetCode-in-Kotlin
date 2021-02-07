// https://leetcode-cn.com/problems/set-matrix-zeroes/
// 73. Set Matrix Zeroes
class Solution0073_v2 {
    fun setZeroes(matrix: Array<IntArray>) {
        val m = matrix.size
        val n = matrix[0].size
        val rowSet = hashSetOf<Int>()
        val colSet = hashSetOf<Int>()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i)
                    colSet.add(j)
                }
            }
        }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i in rowSet || j in colSet) {
                    matrix[i][j] = 0
                }
            }
        }
    }
}

fun main() {
    val s = Solution0073_v2()
//    val matrix = arrayOf(intArrayOf(1,1,1), intArrayOf(1,0,1), intArrayOf(1,1,1))
    val matrix = arrayOf(intArrayOf(0,1,2,0), intArrayOf(3,4,5,2), intArrayOf(1,3,1,5))
    s.setZeroes(matrix)
    matrix.forEach { println(it.joinToString()) }
}