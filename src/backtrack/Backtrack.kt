package backtrack

import java.util.LinkedList

class Solution17 {
    private val lettersMap = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
    fun letterCombinations(digits: String): List<String> {
        val res = mutableListOf<String>()
        if (digits.isEmpty()) return res
        findCombination(digits, 0, mutableListOf(), res)
        return res
    }

    private fun findCombination(digits: String, index: Int, path: MutableList<Char>, res: MutableList<String>) {
        if (index == digits.length) {
            res.add(path.joinToString(""))
            return
        }
        for (c in lettersMap[digits[index] - '0']) {
            path.add(c)
            findCombination(digits, index + 1, path, res)
            path.removeAt(path.lastIndex)
        }
    }
}

// https://leetcode.cn/problems/restore-ip-addresses/
class Solution93 {
    fun restoreIpAddresses(s: String): List<String> {
        val res = mutableListOf<String>()
        if (s.length !in 4..12) return res
        helper(s, 0, mutableListOf(), res)
        return res
    }

    private fun helper(s: String, index: Int, path: MutableList<String>, res: MutableList<String>) {
        if (index == s.length && path.size == 4) {
            res.add(path.joinToString("."))
            return
        }
        if (index == s.length || path.size == 4) {
            return
        }
        if (index + 1 <= s.length) {
            path.add(s.substring(index, index + 1))
            helper(s, index + 1, path, res)
            path.removeAt(path.lastIndex)
        }
        if (index + 2 <= s.length) {
            val sub = s.substring(index, index + 2)
            if (sub.toInt() in 10..99) {
                path.add(sub)
                helper(s, index + 2, path, res)
                path.removeAt(path.lastIndex)
            }
        }
        if (index + 3 <= s.length) {
            val sub = s.substring(index, index + 3)
            if (sub.toInt() in 100..255) {
                path.add(sub)
                helper(s, index + 3, path, res)
                path.removeAt(path.lastIndex)
            }
        }
    }
}

// https://leetcode.cn/problems/palindrome-partitioning/
class Solution131 {
    fun partition(s: String): List<List<String>> {
        val res = mutableListOf<List<String>>()
        partition(s, 0, mutableListOf(), res)
        return res
    }

    private fun partition(s: String, index: Int, path: MutableList<String>, res: MutableList<List<String>>) {
        if (index == s.length) {
            res.add(path.toList())
            return
        }
        for (r in index until s.length) {
            if (isPalindrome(s, index, r)) {
                path.add(s.substring(index, r + 1))
                partition(s, r + 1, path, res)
                path.removeAt(path.lastIndex)
            }
        }
    }

    private fun isPalindrome(s: String, l: Int, r: Int): Boolean {
        var i = l
        var j = r
        while (i < j) {
            if (s[i] != s[j]) return false
            ++i
            --j
        }
        return true
    }
}

class Solution46 {
    fun permute(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        findPermutation(nums, BooleanArray(nums.size), mutableListOf(), res)
        return res
    }

    private fun findPermutation(
        nums: IntArray,
        used: BooleanArray,
        path: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        if (path.size == nums.size) {
            res.add(path.toList())
            return
        }
        for (i in nums.indices) {
            if (used[i].not()) {
                used[i] = true
                path.add(nums[i])
                findPermutation(nums, used, path, res)
                path.removeAt(path.lastIndex)
                used[i] = false
            }
        }
    }
}

class Solution47 {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        findPermutation(nums.sorted().toIntArray(), BooleanArray(nums.size), mutableListOf(), res)
        return res.toSet().toList()
    }

    private fun findPermutation(
        nums: IntArray,
        used: BooleanArray,
        path: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        if (path.size == nums.size) {
            res.add(path.toList())
            return
        }
        for (i in nums.indices) {
            if (used[i].not()) {
                used[i] = true
                path.add(nums[i])
                findPermutation(nums, used, path, res)
                path.removeAt(path.lastIndex)
                used[i] = false
            }
        }
    }
}

class Solution77 {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        findCombination(n, 1, k, mutableListOf(), res)
        return res
    }

    private fun findCombination(n: Int, startNum: Int, k: Int, path: MutableList<Int>, res: MutableList<List<Int>>) {
        if (path.size == k) {
            res.add(path.toList())
            return
        }
        if (n - startNum + 1 < k - path.size) return
        for (i in startNum..n) {
            path.add(i)
            findCombination(n, i + 1, k, path, res)
            path.removeAt(path.lastIndex)
        }
    }
}


class Solution78 {
    fun subsets(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        for (count in 0..nums.size) {
            findSubset(nums, 0, count, mutableListOf(), res)
        }
        return res
    }

    private fun findSubset(
        nums: IntArray,
        index: Int,
        count: Int,
        path: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        if (path.size == count) {
            res.add(path.toList())
            return
        }
        if (index >= nums.size) return
        for (i in index until nums.size) {
            path.add(nums[i])
            findSubset(nums, i + 1, count, path, res)
            path.removeAt(path.lastIndex)
        }
    }
}

class Solution90 {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        nums.sort()
        for (count in 0..nums.size) {
            findSubset(nums, 0, count, mutableListOf(), res)
        }
        return res
    }

    private fun findSubset(
        nums: IntArray,
        startIndex: Int,
        count: Int,
        path: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        if (path.size == count) {
            res.add(path.toList())
            return
        }
        for (i in startIndex until nums.size) {
            if (i - 1 >= startIndex && nums[i] == nums[i - 1]) continue
            path.add(nums[i])
            findSubset(nums, i + 1, count, path, res)
            path.removeAt(path.lastIndex)
        }
    }
}

class Solution401 {
    fun readBinaryWatch(turnedOn: Int): List<String> {
        val hourBits = intArrayOf(1, 2, 4, 8)
        val minuteBits = intArrayOf(1, 2, 4, 8, 16, 32)
        val hourRange = 0..11
        val minuteRange = 0..59
        val res = mutableListOf<String>()
        for (i in 0..turnedOn) {
            val hourList = helper(i, hourRange, hourBits)
            if (hourList.isEmpty() && i != 0) continue
            val j = turnedOn - i
            val minuteList = helper(j, minuteRange, minuteBits)
            if (minuteList.isEmpty() && j != 0) continue
            for (hour in hourList) {
                for (minute in minuteList) {
                    res.add("$hour:${minute.padStart(2, '0')}")
                }
            }
        }
        return res
    }


    private fun helper(count: Int, range: IntRange, bits: IntArray): List<String> {
        val res = mutableListOf<String>()
        helper(count, range, bits, 0, 0, mutableListOf(), res)
        return res
    }

    private fun helper(
        count: Int,
        range: IntRange,
        bits: IntArray,
        startIndex: Int,
        sum: Int,
        path: MutableList<Int>,
        res: MutableList<String>
    ) {
        if (path.size == count) {
            if (sum in range) {
                res.add(sum.toString())
            }
            return
        }
        if (startIndex >= bits.size || sum > range.last) return
        for (i in startIndex until bits.size) {
            path.add(bits[i])
            helper(count, range, bits, i + 1, sum + bits[i], path, res)
            path.removeAt(path.lastIndex)
        }
    }
}

class Solution79 {
    private var m = 0
    private var n = 0
    private val directions = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    private var visited: Array<BooleanArray> = emptyArray()

    fun exist(board: Array<CharArray>, word: String): Boolean {
        m = board.size
        if (m == 0) return false
        n = board[0].size
        if (n == 0) return false
        visited = Array(m) { BooleanArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (dfs(board, word, 0, i, j)) return true
            }
        }
        return false
    }

    private fun inArea(i: Int, j: Int): Boolean {
        return i in 0 until m && j in 0 until n
    }

    private fun dfs(board: Array<CharArray>, word: String, startIndex: Int, startX: Int, startY: Int): Boolean {
        if (startIndex == word.lastIndex) {
            return board[startX][startY] == word.last()
        }
        if (board[startX][startY] == word[startIndex]) {
            visited[startX][startY] = true
            for (d in directions) {
                val newX = startX + d[0]
                val newY = startY + d[1]
                if (inArea(newX, newY) && !visited[newX][newY] && dfs(board, word, startIndex + 1, newX, newY)) {
                    return true
                }
            }
            visited[startX][startY] = false
        }
        return false
    }
}

class Solution200 {
    private var m = 0
    private var n = 0
    private var visited: Array<BooleanArray> = emptyArray()
    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(-1, 0)
    )

    fun numIslands(grid: Array<CharArray>): Int {
        m = grid.size
        if (m == 0) return 0
        n = grid[0].size
        if (n == 0) return 0
        var res = 0
        visited = Array(m) { BooleanArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == '1' && visited[i][j].not()) {
                    ++res
                    dfs(grid, i, j)
                }
            }
        }
        return res
    }

    private fun inArea(x: Int, y: Int) = x in 0 until m && y in 0 until n

    private fun dfs(grid: Array<CharArray>, startX: Int, startY: Int) {
        visited[startX][startY] = true
        for (d in directions) {
            val newX = startX + d[0]
            val newY = startY + d[1]
            if (inArea(newX, newY) && grid[newX][newY] == '1' && visited[newX][newY].not()) {
                dfs(grid, newX, newY)
            }
        }
    }
}

class Solution130 {
    private var m = 0
    private var n = 0
    private var visited: Array<BooleanArray> = emptyArray()
    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(-1, 0)
    )

    fun solve(board: Array<CharArray>): Unit {
        m = board.size
        if (m == 0) return
        n = board[0].size
        if (n == 0) return
        visited = Array(m) { BooleanArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (board[i][j] == 'O' && visited[i][j].not()) {
                    val record = mutableListOf<Pair<Int, Int>>()
                    if (bfs(board, i, j, record)) {
                        for ((x, y) in record) {
                            board[x][y] = 'X'
                        }
                    }
                }
            }
        }
    }

    private fun inArea(x: Int, y: Int) = x in 0 until m && y in 0 until n

    private fun bfs(board: Array<CharArray>, startX: Int, startY: Int, record: MutableList<Pair<Int, Int>>): Boolean {
        visited[startX][startY] = true
        val queue = LinkedList<Pair<Int, Int>>().apply { offer(startX to startY) }
        var res = true
        while (queue.isNotEmpty()) {
            val front = queue.poll()
            record.add(front)
            for (d in directions) {
                val newX = front.first + d[0]
                val newY = front.second + d[1]
                if (inArea(newX, newY).not()) {
                    res = false
                } else {
                    if (board[newX][newY] == 'O' && visited[newX][newY].not()) {
                        visited[newX][newY] = true
                        queue.offer(newX to newY)
                    }
                }
            }
        }
        return res
    }
}

fun main() {
//    val s17 = Solution17()
//    println(s17.letterCombinations("23") == listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
//    println(s17.letterCombinations(""))
//
//    val s93 = Solution93()
//    println(s93.restoreIpAddresses("25525511135"))
//    println(s93.restoreIpAddresses("101023"))

//    val s = Solution131()
//    println(s.partition("aab"))

//    val s = Solution46()
//    println(s.permute(intArrayOf()))
//    println(s.permute(intArrayOf(1)))
//    println(s.permute(intArrayOf(0, 1)))
//    println(s.permute(intArrayOf(1, 2, 3)))

    val s = Solution47()
    println(s.permuteUnique(intArrayOf(1, 1, 2)))
}