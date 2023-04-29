class Solution0120_v2 {

    private var memo: Array<Array<Int?>> = emptyArray()

    // 定义f(i,j)为triangle[i][j]到底边的最小路径和
    // 则有: f(i,j)=minOf(f(i+1,j),f(i+1,j+1)) + triangle[i][j]
    fun minimumTotal(triangle: List<List<Int>>): Int {
        memo = Array(triangle.size) { Array(triangle.size) { null as Int? } }
        return dfs(triangle, 0, 0)
    }

    private fun dfs(triangle: List<List<Int>>, i: Int, j: Int): Int {
        if (i == triangle.size) return 0
        if (memo[i][j] == null) {
            memo[i][j] = minOf(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle[i][j]
        }
        return memo[i][j]!!
    }
}

fun main() {
    val s = Solution0120_v2()
    val triangle = listOf(
        listOf(2),
        listOf(3, 4),
        listOf(6, 5, 7),
        listOf(4, 1, 8, 3)
    )
    println(s.minimumTotal(triangle) == 11)
}