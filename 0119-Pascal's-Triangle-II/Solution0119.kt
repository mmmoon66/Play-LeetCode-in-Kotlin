// https://leetcode-cn.com/problems/pascals-triangle-ii/
// 119. Pascal's Triangle II
class Solution0119 {
    fun getRow(rowIndex: Int): List<Int> {
        var triangle = mutableListOf<List<Int>>()
        for (i in 0..rowIndex) {
            val row = mutableListOf<Int>()
            for (j in 0..i) {
                var num = 0
                if (i - 1 >= 0 && j - 1 >= 0) num += triangle[i - 1][j - 1]
                if (i - 1 >= 0 && j <= i - 1) num += triangle[i - 1][j]
                if (num == 0) num = 1
                row.add(num)
            }
            triangle.add(row)
        }
        return triangle.lastOrNull() ?: emptyList()
    }
}

fun main() {
    val s = Solution0119()
    println(s.getRow(5).joinToString())
}