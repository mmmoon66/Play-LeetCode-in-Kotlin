// https://leetcode-cn.com/problems/pascals-triangle/
// 118. Pascal's Triangle
class Solution0118 {
    fun generate(numRows: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        for (i in 0 until numRows) {
            val row = mutableListOf<Int>()
            for (j in 0..i) {
                var num = 0
                if (i - 1 >= 0 && j - 1 >= 0) num += res[i - 1][j - 1]
                if (i - 1 >= 0 && j <= i - 1) num += res[i - 1][j]
                if (num == 0) num = 1
                row.add(num)
            }
            res.add(row)
        }
        return res
    }
}

fun main() {
    val s = Solution0118()
    val res = s.generate(5)
    res.forEach {
        println(it.joinToString())
    }
}