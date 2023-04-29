// https://leetcode-cn.com/problems/pascals-triangle-ii/
// 119. Pascal's Triangle II
class Solution0119_v2 {
    fun getRow(rowIndex: Int): List<Int> {
        val res = mutableListOf<Int>()
        for (i in 0..rowIndex) {
            for (j in i downTo 0) {
                var num = 0
                if (i - 1 >= 0 && j - 1 >= 0) num += res[j - 1]
                if (i - 1 >= 0 && j <= i - 1) num += res[j]
                if (num == 0) num = 1
                if (j < res.size) res[j] = num else res.add(num)
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0119_v2()
    println(s.getRow(5).joinToString())
}