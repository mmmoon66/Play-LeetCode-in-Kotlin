import java.io.InputStreamReader

// https://leetcode.com/problems/number-of-islands/
// 200. Number of Islands
/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input:
11110
11010
11000
00000
Output: 1

Example 2:
Input:
11000
11000
00100
00011
Output: 3
 */
class Solution0200 {
    private var m = 0
    private var n = 0
    private lateinit var visited: Array<BooleanArray>
    private val direction = arrayOf(
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1)
    )

    private fun inArea(x: Int, y: Int) = x in 0 until m && y in 0 until n

    private fun dfs(grid: Array<CharArray>, x: Int, y: Int) {
        visited[x][y] = true
        for (d in direction) {
            val newX = x + d[0]
            val newY = y + d[1]
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {
                dfs(grid, newX, newY)
            }
        }
    }

    fun numIslands(grid: Array<CharArray>): Int {
        m = grid.size
        if (m == 0) return 0
        n = grid[0].size
        if (n == 0) return 0
        visited = Array(m) { BooleanArray(n) }
        var res = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    ++res
                    dfs(grid, i, j)
                }
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0200()
    s.numIslands(
        arrayOf(
            "11110".toCharArray(),
            "11010".toCharArray(),
            "11000".toCharArray(),
            "00000".toCharArray()
        )
    ).also(::println)
    s.numIslands(
        arrayOf(
            "11000".toCharArray(),
            "11000".toCharArray(),
            "00100".toCharArray(),
            "00011".toCharArray()
        )
    ).also(::println)
}
/*
Runtime: 180 ms, faster than 65.00% of Kotlin online submissions for Number of Islands.
Memory Usage: 37.3 MB, less than 100.00% of Kotlin online submissions for Number of Islands.
 */