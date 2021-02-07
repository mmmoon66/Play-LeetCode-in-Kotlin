// https://leetcode-cn.com/problems/set-matrix-zeroes/
// 73. Set Matrix Zeroes
class Solution0073_v3 {
    fun setZeroes(matrix: Array<IntArray>) {
        val m = matrix.size
        val n = matrix[0].size
        var firstRowNeedSetZero = false
        for (j in 0 until n) {
            if (matrix[0][j] == 0) {
                firstRowNeedSetZero = true
            }
        }
        var firstColumnNeedSetZero = false
        for (i in 0 until m) {
            if (matrix[i][0] == 0) {
                firstColumnNeedSetZero = true
            }
        }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }
        if (firstRowNeedSetZero) {
            for (j in 0 until n) {
                matrix[0][j] = 0
            }
        }
        if (firstColumnNeedSetZero) {
            for (i in 0 until m) {
                matrix[i][0] = 0
            }
        }
    }
}

fun main() {
    val s = Solution0073_v3()
//    val matrix = arrayOf(intArrayOf(1,1,1), intArrayOf(1,0,1), intArrayOf(1,1,1))
    val matrix = arrayOf(intArrayOf(0,1,2,0), intArrayOf(3,4,5,2), intArrayOf(1,3,1,5))
    s.setZeroes(matrix)
    matrix.forEach { println(it.joinToString()) }
}