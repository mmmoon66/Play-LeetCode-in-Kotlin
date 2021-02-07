// https://leetcode-cn.com/problems/search-a-2d-matrix/
// 74. Search a 2D Matrix
class Solution0074 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size
        var i = 0
        var j = n - 1
        while(i < m && j >= 0) {
            if (matrix[i][j] == target) return true
            if (matrix[i][j] < target) ++i else --j
        }
        return false
    }
}