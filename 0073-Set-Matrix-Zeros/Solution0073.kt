// https://leetcode-cn.com/problems/set-matrix-zeroes/
// 73. Set Matrix Zeroes
class Solution0073 {
    fun setZeroes(matrix: Array<IntArray>) {
        val m = matrix.size
        val n = matrix[0].size
        val changed = Array(m) { BooleanArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0 && changed[i][j].not()) {
                    // 将第i行元素设置为0
                    for (y in 0 until n) {
                        if (matrix[i][y] != 0) {
                            matrix[i][y] = 0
                            changed[i][y] = true
                        }
                    }
                    // 将第j列元素设置为0
                    for (x in 0 until m) {
                        if (matrix[x][j] != 0) {
                            matrix[x][j] = 0
                            changed[x][j] = true
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    val s = Solution0073()
//    val matrix = arrayOf(intArrayOf(1,1,1), intArrayOf(1,0,1), intArrayOf(1,1,1))
    val matrix = arrayOf(intArrayOf(0,1,2,0), intArrayOf(3,4,5,2), intArrayOf(1,3,1,5))
    s.setZeroes(matrix)
    matrix.forEach { println(it.joinToString()) }
}