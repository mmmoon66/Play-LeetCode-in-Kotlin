package hot100

import java.math.BigInteger
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import kotlin.random.Random

// region 哈希
class Solution0001 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            val other = target - num
            if (other in map) {
                return intArrayOf(map[other]!!, index)
            }
            map[num] = index
        }
        return intArrayOf()
    }
}

class Solution0049 {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = hashMapOf<String, MutableList<String>>()
        for (str in strs) {
            val sorted = str.toCharArray().sorted().joinToString("")
            if (sorted !in map) {
                map[sorted] = mutableListOf()
            }
            map[sorted]?.add(str)
        }
        return map.values.toList()
    }
}

class Solution0128 {
    fun longestConsecutive(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return n
        nums.sort()
        var res = 1
        var prev = nums[0]
        var count = 1
        var i = 1
        while (i < n) {
            val cur = nums[i]
            if (cur == prev + 1) {
                ++count
                res = maxOf(res, count)
                prev = cur
            } else if (cur == prev) {
                // do nothing
            } else {
                count = 1
                prev = cur
            }
            ++i
        }
        return res
    }
}
// endregion

// region 双指针
class Solution0283 {
    fun moveZeroes(nums: IntArray): Unit {
        var i = 0
        var k = 0
        val n = nums.size
        while (i < n) {
            if (nums[i] != 0) {
                nums[k++] = nums[i]
            }
            ++i
        }
        while (k < n) {
            nums[k++] = 0
        }
    }
}

class Solution0011 {
    fun maxArea(height: IntArray): Int {
        val n = height.size
        var i = 0
        var j = n - 1
        var res = 0
        while (i < j) {
            val area = (j - i) * minOf(height[i], height[j])
            res = maxOf(res, area)
            if (height[i] < height[j]) {
                ++i
            } else {
                --j
            }
        }
        return res
    }
}

class Solution0015 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val n = nums.size
        if (n < 3) {
            return res
        }
        nums.sort()
        var i = 0
        while (i < n) {
            var j = i + 1
            var k = n - 1
            while (j < k) {
                val sum = nums[i] + nums[j] + nums[k]
                if (sum == 0) {
                    res.add(listOf(nums[i], nums[j], nums[k]))
                    j = nextIndex(nums, j)
                    k = prevIndex(nums, k)
                } else if (sum < 0) {
                    j = nextIndex(nums, j)
                } else {
                    k = prevIndex(nums, k)
                }
            }
            i = nextIndex(nums, i)
        }
        return res
    }

    private fun prevIndex(nums: IntArray, index: Int): Int {
        var i = index - 1
        while (i >= 0) {
            if (nums[i] != nums[index]) {
                return i
            }
            --i
        }
        return -1
    }

    private fun nextIndex(nums: IntArray, index: Int): Int {
        var i = index + 1
        while (i < nums.size) {
            if (nums[i] != nums[index]) {
                return i
            }
            ++i
        }
        return nums.size
    }
}

class Solution0042 {
    fun trap(height: IntArray): Int {
        val n = height.size
        if (n < 2) {
            return 0
        }
        // leftMax[i]表示height[0..i]范围内最大值
        val leftMax = IntArray(n)
        leftMax[0] = height[0]
        for (i in 1..<n) {
            leftMax[i] = maxOf(leftMax[i - 1], height[i])
        }
        // rightMax[i]表示height[i..n-1]范围内最大值
        val rightMax = IntArray(n)
        rightMax[n - 1] = height[n - 1]
        for (i in n - 2 downTo 1) {
            rightMax[i] = maxOf(rightMax[i + 1], height[i])
        }
        var res = 0
        for (i in 1..<n - 1) {
            res += maxOf(0, minOf(leftMax[i - 1], rightMax[i + 1]) - height[i])
        }
        return res
    }
}
// endregion

// region 滑动窗口
class Solution0003 {
    fun lengthOfLongestSubstring(s: String): Int {
        val freq = IntArray(128)
        val n = s.length
        var i = 0
        var j = -1
        var res = 0
        while (j + 1 < n) {
            ++j
            ++freq[s[j].code]
            while (freq[s[j].code] > 1) {
                --freq[s[i].code]
                ++i
            }
            res = maxOf(res, j - i + 1)
        }
        return res
    }
}

class Solution0438 {
    fun findAnagrams(s: String, p: String): List<Int> {
        val sLen = s.length
        val pLen = p.length
        val res = mutableListOf<Int>()
        if (sLen < pLen) {
            return res
        }
        val sFreq = IntArray(26)
        val pFreq = IntArray(26)
        for (i in 0..<pLen) {
            ++sFreq[s[i] - 'a']
            ++pFreq[p[i] - 'a']
        }
        var start = 0
        var end = start + pLen - 1
        while (end < sLen) {
            if (isAnagram(sFreq, pFreq)) {
                res.add(start)
            }
            --sFreq[s[start] - 'a']
            ++start
            if (end + 1 < sLen) {
                ++end
                ++sFreq[s[end] - 'a']
            } else {
                ++end
            }
        }
        return res
    }

    private fun isAnagram(s: IntArray, p: IntArray): Boolean {
        for (i in 0..<26) {
            if (s[i] != p[i]) {
                return false
            }
        }
        return true
    }
}
// endregion

// region 子串
class Solution0560 {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val map = hashMapOf<Int, Int>()
        map[0] = 1
        var sum = 0
        var res = 0
        for (num in nums) {
            sum += num
            // sum - prev = k
            val prev = sum - k
            map[prev]?.let {
                res += it
            }
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return res
    }
}

class Solution0239 {

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        val res = IntArray(n - k + 1)
        val queue = PriorityQueue<Int> { a, b -> if (nums[a] == nums[b]) a - b else nums[b] - nums[a] }
        for (i in 0..<k) {
            queue.offer(i)
        }
        var j = 0
        res[j++] = nums[queue.peek()]
        for (i in k..<n) {
            queue.offer(i)
            // i - start + 1 = k ==> start = i - k + 1
            while (queue.peek() < i - k + 1) {
                queue.poll()
            }
            res[j++] = nums[queue.peek()]
        }
        return res
    }
}

class Solution0076 {
    fun minWindow(s: String, t: String): String {
        val sLen = s.length
        val tLen = t.length
        if (sLen < tLen) return ""
        val a = IntArray(128)
        val b = IntArray(128)
        for (i in 0..<tLen) {
            ++a[s[i].code]
            ++b[t[i].code]
        }
        if (isCover(a, b)) {
            return s.substring(0, tLen)
        }
        var start = 0
        var end = tLen - 1
        var minLen = Int.MAX_VALUE
        var res = ""
        while (end + 1 < sLen) {
            ++end
            ++a[s[end].code]
            while (isCover(a, b) && start <= end) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1
                    res = s.substring(start, end + 1)
                }
                --a[s[start].code]
                ++start
            }
        }
        return res
    }

    private fun isCover(a: IntArray, b: IntArray): Boolean {
        for (i in a.indices) {
            if (a[i] < b[i]) {
                return false
            }
        }
        return true
    }
}
// endregion

// region 普通数组
class Solution0053 {
    fun maxSubArray(nums: IntArray): Int {
        var res = Int.MIN_VALUE
        var curMax = 0
        for (num in nums) {
            curMax = maxOf(curMax + num, num)
            res = maxOf(res, curMax)
        }
        return res
    }
}

class Solution0053V2 {
    data class Node(
        val leftSum: Int, val rightSum: Int, val midSum: Int, val sum: Int
    )

    fun maxSubArray(nums: IntArray): Int {
        return node(nums, 0, nums.size - 1).midSum
    }

    private fun node(nums: IntArray, l: Int, r: Int): Node {
        if (l == r) {
            return Node(leftSum = nums[l], rightSum = nums[l], midSum = nums[l], sum = nums[l])
        }
        val mid = (l + r) shr 1
        val leftNode = node(nums, l, mid)
        val rightNode = node(nums, mid + 1, r)
        val leftSum = maxOf(leftNode.leftSum, leftNode.sum + rightNode.leftSum)
        val rightSum = maxOf(rightNode.rightSum, rightNode.sum + leftNode.rightSum)
        val midSum = maxOf(leftNode.midSum, rightNode.midSum, leftNode.rightSum + rightNode.leftSum)
        val sum = leftNode.sum + rightNode.sum
        return Node(leftSum = leftSum, rightSum = rightSum, midSum = midSum, sum = sum)
    }
}

class Solution0056 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val n = intervals.size
        if (n == 0) return emptyArray()
        val res = mutableListOf<IntArray>()
        intervals.sortWith { a, b -> if (a[0] == b[0]) a[1] - b[1] else a[0] - b[0] }
        var start = intervals[0][0]
        var end = intervals[0][1]
        for (i in 1..<n) {
            val interval = intervals[i]
            if (interval[0] <= end) {
                end = maxOf(end, interval[1])
            } else {
                res.add(intArrayOf(start, end))
                start = interval[0]
                end = interval[1]
            }
        }
        res.add(intArrayOf(start, end))
        return res.toTypedArray()
    }
}

class Solution0189 {
    fun rotate(nums: IntArray, k: Int): Unit {
        val n = nums.size
        if (k % n == 0) return
        val i = k % n
        reverse(nums, 0, n - 1)
        reverse(nums, 0, i - 1)
        reverse(nums, i, n - 1)
    }

    fun reverse(nums: IntArray, l: Int, r: Int) {
        var start = l
        var end = r
        while (start < end) {
            nums[start] = nums[end].also { nums[end] = nums[start] }
            ++start
            --end
        }
    }
}

class Solution0238 {
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        // leftProduct[i]表示nums[0..i]范围内元素的乘积
        val leftProduct = IntArray(n)
        // rightProduct[i]表示nums[i,n-1]范围内元素的乘积
        val rightProduct = IntArray(n)

        leftProduct[0] = nums[0]
        for (i in 1..<n) {
            leftProduct[i] = nums[i] * leftProduct[i - 1]
        }
        rightProduct[n - 1] = nums[n - 1]
        for (i in n - 2 downTo 0) {
            rightProduct[i] = nums[i] * rightProduct[i + 1]
        }
        val res = IntArray(n)
        res[0] = rightProduct[1]
        res[n - 1] = leftProduct[n - 2]
        for (i in 1..n - 2) {
            res[i] = leftProduct[i - 1] * rightProduct[i + 1]
        }
        return res
    }
}

class Solution0041 {
    fun firstMissingPositive(nums: IntArray): Int {
        val freq = hashMapOf<Int, Int>()
        for (num in nums) {
            freq[num] = freq.getOrDefault(num, 0) + 1
        }
        val n = nums.size
        for (i in 1..n) {
            if (i !in freq) {
                return i
            }
        }
        return n + 1
    }
}

class Solution1995 {
    fun countQuadruplets(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        val n = nums.size
        var res = 0
        for (c in n - 2 downTo 0) {
            map[nums[c + 1]] = map.getOrDefault(nums[c + 1], 0) + 1
            for (a in 0..<c) {
                for (b in a + 1..<c) {
                    val sum = nums[a] + nums[b] + nums[c]
                    res += map.getOrDefault(sum, 0)
                }
            }
        }
        return res
    }
}

class Solution0031 {
    fun nextPermutation(nums: IntArray): Unit {
        val n = nums.size
        if (n < 2) {
            return
        }
        var i = n - 2
        var j = n - 1
        var k = n - 1
        // 从后向前查找第一个升序对 nums[i] > nums[j]
        while (i >= 0 && nums[i] >= nums[j]) {
            --i
            --j
        }
        if (i >= 0) {
            // 从后向前查找第一个比nums[i]大的元素nums[k]
            while (nums[k] <= nums[i]) {
                --k
            }
            // 交换nums[i]和nums[k]
            nums[i] = nums[k].also { nums[k] = nums[i] }
        }
        // 将nums[j..end]之间的元素从降序反转为升序
        i = j
        j = n - 1
        while (i < j) {
            nums[i] = nums[j].also { nums[j] = nums[i] }
            ++i
            --j
        }
    }
}

fun main() {
    val s = Solution1995()
    val nums = intArrayOf(28, 8, 49, 85, 37, 90, 20, 8)
    println(s.countQuadruplets(nums))
}
// endregion

// region 矩阵
class Solution0073 {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        if (m == 0) return
        val n = matrix[0].size
        if (n == 0) return
        val row = BooleanArray(m)
        val col = BooleanArray(n)
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (matrix[i][j] == 0) {
                    row[i] = true
                    col[j] = true
                }
            }
        }
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0
                }
            }
        }
    }
}

class Solution0073V2 {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        if (m == 0) return
        val n = matrix[0].size
        if (n == 0) return
        var flagRow0 = false
        var flagCol0 = false
        for (j in 0..<n) {
            if (matrix[0][j] == 0) {
                flagRow0 = true
                break
            }
        }
        for (i in 0..<m) {
            if (matrix[i][0] == 0) {
                flagCol0 = true
                break
            }
        }
        for (i in 1..<m) {
            for (j in 1..<n) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0
                    matrix[i][0] = 0
                }
            }
        }
        for (i in 1..<m) {
            for (j in 1..<n) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }
        if (flagRow0) {
            for (j in 0..<n) {
                matrix[0][j] = 0
            }
        }
        if (flagCol0) {
            for (i in 0..<m) {
                matrix[i][0] = 0
            }
        }
    }
}

class Solution0054 {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val res = mutableListOf<Int>()
        val m = matrix.size
        if (m == 0) return res
        val n = matrix[0].size
        if (n == 0) return res
        var left = 0
        var right = n - 1
        var up = 0
        var down = m - 1
        while (true) {
            // 从左向右遍历
            for (j in left..right) {
                res.add(matrix[up][j])
            }
            if (++up > down) break
            // 从上向下遍历
            for (i in up..down) {
                res.add(matrix[i][right])
            }
            if (--right < left) break
            // 从右向左遍历
            for (j in right downTo left) {
                res.add(matrix[down][j])
            }
            if (--down < up) break
            // 从下向上遍历
            for (i in down downTo up) {
                res.add(matrix[i][left])
            }
            if (++left > right) break
        }
        return res
    }
}

class Solution0054V2 {
    private val DIRECTIONS = arrayOf(
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
        intArrayOf(-1, 0), // up
    )
    private val VISITED = Int.MAX_VALUE

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val res = mutableListOf<Int>()
        val m = matrix.size
        if (m == 0) return res
        val n = matrix[0].size
        if (n == 0) return res
        var i = 0
        var j = 0
        var k = 0
        res.add(matrix[0][0])
        matrix[0][0] = VISITED
        while (res.size < m * n) {
            val d = DIRECTIONS[k]
            val newI = i + d[0]
            val newJ = j + d[1]
            if (newI in 0..<m && newJ in 0..<n && matrix[newI][newJ] != VISITED) {
                i = newI
                j = newJ
                res.add(matrix[i][j])
                matrix[i][j] = VISITED
            } else {
                k = (k + 1) % 4
            }
        }
        return res
    }
}

class Solution0048 {
    fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.size
        var u = 0
        var d = n - 1
        // 先上下翻转
        while (u < d) {
            matrix[u] = matrix[d].also { matrix[d] = matrix[u] }
            ++u
            --d
        }
        // 再沿着左上角到右下角的对角线翻转
        for (i in 0..n - 2) {
            for (j in i + 1..<n) {
                matrix[i][j] = matrix[j][i].also { matrix[j][i] = matrix[i][j] }
            }
        }
    }
}

class Solution0240 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        if (m == 0) return false
        val n = matrix[0].size
        if (n == 0) return false
        var i = 0
        var j = n - 1
        while (i < m && j >= 0) {
            if (target == matrix[i][j]) {
                return true
            } else if (target < matrix[i][j]) {
                --j
            } else {
                ++i
            }
        }
        return false
    }
}
// endregion

// region 图论
class Solution0200 {
    private val directions = arrayOf(
        intArrayOf(-1, 0),// up
        intArrayOf(0, 1),// right
        intArrayOf(1, 0),// down
        intArrayOf(0, -1),// left
    )

    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        if (m == 0) return 0
        val n = grid[0].size
        if (n == 0) return 0
        var count = 0
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] == '1') {
                    ++count
                    dfs(grid, i, j, m, n)
                }
            }
        }
        return count
    }

    private fun dfs(grid: Array<CharArray>, curI: Int, curJ: Int, m: Int, n: Int) {
        grid[curI][curJ] = '2'
        for (d in directions) {
            val newI = curI + d[0]
            val newJ = curJ + d[1]
            if (newI in 0..<m && newJ in 0..<n && grid[newI][newJ] == '1') {
                dfs(grid, newI, newJ, m, n)
            }
        }
    }
}

class Solution0130 {

    private val directions = arrayOf(
        intArrayOf(-1, 0), // up
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
    )

    private val MARKED = 'M'
    fun solve(board: Array<CharArray>): Unit {
        val m = board.size
        val n = board[0].size
        for (i in 0..<m) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0, m, n)
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1, m, n)
            }
        }
        for (j in 0..<n) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j, m, n)
            }
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j, m, n)
            }
        }
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (board[i][j] == MARKED) {
                    board[i][j] = 'O'
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X'
                }
            }
        }
    }

    private fun dfs(board: Array<CharArray>, curI: Int, curJ: Int, m: Int, n: Int) {
        val stack = Stack<Pair<Int, Int>>()
        stack.push(curI to curJ)
        board[curI][curJ] = MARKED
        while (stack.isNotEmpty()) {
            val (i, j) = stack.pop()
            for (d in directions) {
                val newI = i + d[0]
                val newJ = j + d[1]
                if (newI in 0..<m && newJ in 0..<n && board[newI][newJ] == 'O') {
                    stack.push(newI to newJ)
                    board[newI][newJ] = MARKED
                }
            }
        }
    }
}

class Solution0130V2 {

    private val directions = arrayOf(
        intArrayOf(-1, 0), // up
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
    )

    private val MARKED = 'M'
    fun solve(board: Array<CharArray>): Unit {
        val m = board.size
        val n = board[0].size
        for (j in 0..<n) {
            if (board[0][j] == 'O') {
                bfs(board, 0, j, m, n)
            }
            if (board[m - 1][j] == 'O') {
                bfs(board, m - 1, j, m, n)
            }
        }
        for (i in 1..<m - 1) {
            if (board[i][0] == 'O') {
                bfs(board, i, 0, m, n)
            }
            if (board[i][n - 1] == 'O') {
                bfs(board, i, n - 1, m, n)
            }
        }
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (board[i][j] == MARKED) {
                    board[i][j] = 'O'
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X'
                }
            }
        }
    }

    private fun bfs(board: Array<CharArray>, curI: Int, curJ: Int, m: Int, n: Int) {
        val queue = LinkedList<Pair<Int, Int>>()
        queue.offer(curI to curJ)
        board[curI][curJ] = MARKED
        while (queue.isNotEmpty()) {
            val (i, j) = queue.poll()
            for (d in directions) {
                val newI = i + d[0]
                val newJ = j + d[1]
                if (newI in 0..<m && newJ in 0..<n && board[newI][newJ] == 'O') {
                    queue.offer(newI to newJ)
                    board[newI][newJ] = MARKED
                }
            }
        }
    }
}

class Solution0130V3 {

    private val directions = arrayOf(
        intArrayOf(-1, 0), // up
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
    )

    private val MARKED = 'M'
    fun solve(board: Array<CharArray>): Unit {
        val m = board.size
        val n = board[0].size
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (board[i][j] == 'O') {
                    bfs(board, i, j, m, n)
                }
            }
        }
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (board[i][j] == MARKED) {
                    board[i][j] = 'O'
                }
            }
        }
    }

    private fun bfs(board: Array<CharArray>, curI: Int, curJ: Int, m: Int, n: Int) {
        val queue = LinkedList<Pair<Int, Int>>()
        val visited = mutableListOf<Pair<Int, Int>>()
        queue.offer(curI to curJ)
        visited.add(curI to curJ)
        board[curI][curJ] = MARKED
        var isEdge = false
        while (queue.isNotEmpty()) {
            val (i, j) = queue.poll()
            for (d in directions) {
                val newI = i + d[0]
                val newJ = j + d[1]
                if (newI !in 0..<m || newJ !in 0..<n) {
                    isEdge = true
                } else if (board[newI][newJ] == 'O') {
                    queue.offer(newI to newJ)
                    visited.add(newI to newJ)
                    board[newI][newJ] = MARKED
                }
            }
        }
        if (!isEdge) {
            for ((i, j) in visited) {
                board[i][j] = 'X'
            }
        }
    }
}

class Solution0994 {
    data class Node(
        val i: Int,
        val j: Int,
        val depth: Int,
    )

    private val directions = arrayOf(
        intArrayOf(-1, 0), // up
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
    )

    fun orangesRotting(grid: Array<IntArray>): Int {
        val queue = LinkedList<Node>()
        val m = grid.size
        val n = grid[0].size
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] == 2) {
                    queue.offer(Node(i, j, 0))
                }
            }
        }
        var maxDepth = 0
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            maxDepth = maxOf(maxDepth, node.depth)
            for (d in directions) {
                val newI = node.i + d[0]
                val newJ = node.j + d[1]
                val newDepth = node.depth + 1
                if (newI in 0..<m && newJ in 0..<n && grid[newI][newJ] == 1) {
                    grid[newI][newJ] = 2
                    queue.offer(Node(newI, newJ, newDepth))
                }
            }
        }
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] == 1) {
                    return -1
                }
            }
        }
        return maxDepth
    }
}

class Solution0207 {
    fun canFinish(n: Int, arr: Array<IntArray>): Boolean {
        val inDegrees = IntArray(n)
        val edges = Array(n) { arrayListOf<Int>() }
        for (item in arr) {
            ++inDegrees[item[0]]
            edges[item[1]].add(item[0])
        }
        val queue = LinkedList<Int>()
        for (i in 0..<n) {
            if (inDegrees[i] == 0) {
                queue.offer(i)
            }
        }
        var count = 0
        while (queue.isNotEmpty()) {
            ++count
            val cur = queue.poll()
            edges[cur].forEach {
                if (--inDegrees[it] == 0) {
                    queue.offer(it)
                }
            }
        }
        return count == n
    }
}

class Solution0208 {
    class Trie() {

        data class Node(
            var isWord: Boolean = false
        ) {
            var children = Array<Node?>(26) { null }
        }

        private val root = Node()

        fun insert(word: String) {
            var cur = root
            for (c in word) {
                val node = Node()
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = node
                }
                cur = cur.children[c - 'a']!!
            }
            cur.isWord = true
        }

        fun search(word: String): Boolean {
            var cur = root
            for (c in word) {
                val node = cur.children[c - 'a'] ?: return false
                cur = node
            }
            return cur.isWord
        }

        fun startsWith(prefix: String): Boolean {
            var cur = root
            for (c in prefix) {
                val node = cur.children[c - 'a'] ?: return false
                cur = node
            }
            return true
        }

    }
}

class Solution0463 {
    private val directions = arrayOf(
        intArrayOf(-1, 0), // up
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
    )

    fun islandPerimeter(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val queue = LinkedList<Pair<Int, Int>>()

        outLoop@ for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] == 1) {
                    queue.offer(i to j)
                    grid[i][j] = -1
                    break@outLoop
                }
            }
        }
        var res = 0
        while (queue.isNotEmpty()) {
            val (i, j) = queue.poll()
            for (d in directions) {
                val newI = i + d[0]
                val newJ = j + d[1]
                if (newI in 0..<m && newJ in 0..<n && grid[newI][newJ] == 1) {
                    queue.offer(newI to newJ)
                    grid[newI][newJ] = -1
                } else if (newI !in 0..<m || newJ !in 0..<n || grid[newI][newJ] == 0) {
                    ++res
                }
            }
        }
        return res
    }
}
// endregion

// region 链表
data class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution0160 {
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var a = headA
        var b = headB
        while (a != b) {
            a = if (a == null) headB else a.next
            b = if (b == null) headA else b.next
        }
        return a
    }
}

class Solution0206 {
    fun reverseList(head: ListNode?): ListNode? {
        val dummyHead = ListNode(0)
        var cur = head
        while (cur != null) {
            val node = cur
            cur = cur.next
            node.next = dummyHead.next
            dummyHead.next = node
        }
        return dummyHead.next
    }
}

class Solution0206V2 {
    fun reverseList(head: ListNode?): ListNode? {
        return reverseInner(head).head
    }

    private data class Pair(
        var head: ListNode? = null,
        var tail: ListNode? = null,
    )

    private fun reverseInner(head: ListNode?): Pair {
        if (head == null) return Pair()
        if (head.next == null) return Pair(head, head)
        val next = head.next
        head.next = null
        val pair = reverseInner(next)
        pair.tail?.next = head
        pair.tail = head
        return pair
    }
}

class Solution0234 {
    fun isPalindrome(head: ListNode?): Boolean {
        if (head?.next == null) return true
        // 使用快慢指针找到链表的中点
        var slow = head
        var fast = head
        while (fast?.next?.next != null) {
            slow = slow?.next
            fast = fast?.next?.next
        }
        // 从中点将链表分为两段
        var right = slow?.next
        slow?.next = null
        val left = head
        // 将right反转
        val dummyHead = ListNode(0)
        var cur = right
        while (cur != null) {
            val node = cur
            cur = cur.next
            node.next = dummyHead.next
            dummyHead.next = node
        }
        right = dummyHead.next
        dummyHead.next = null
        // 检查是否回文串
        var curLeft = left
        var curRight = right
        while (curLeft != null && curRight != null) {
            if (curLeft.`val` != curRight.`val`) {
                return false
            }
            curLeft = curLeft.next
            curRight = curRight.next
        }
        return true
    }
}

class Solution0141 {
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while (slow?.next != null) {
            slow = slow?.next
            fast = fast?.next?.next
            if (slow == fast) {
                return true
            }
        }
        return false
    }
}

class Solution0142 {
    fun detectCycle(head: ListNode?): ListNode? {
        var slow = head
        var fast = head
        var hasCycle = false
        while (fast?.next?.next != null) {
            slow = slow?.next
            fast = fast?.next?.next
            if (slow == fast) {
                hasCycle = true
                break
            }
        }
        if (hasCycle.not()) return null
        slow = head
        while (slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }
        return slow
    }
}

class Solution0021 {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1
        var a = list1
        var b = list2
        val dummyHead = ListNode(0)
        var cur: ListNode? = dummyHead
        while (a != null && b != null) {
            if (a.`val` < b.`val`) {
                val node = a
                a = a.next
                node.next = null
                cur?.next = node
                cur = cur?.next
            } else {
                val node = b
                b = b.next
                node.next = null
                cur?.next = node
                cur = cur?.next
            }
        }
        if (a != null) {
            cur?.next = a
        }
        if (b != null) {
            cur?.next = b
        }
        return dummyHead.next
    }
}

class Solution0002 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        var a = l1
        var b = l2
        var carry = 0
        val dummyHead = ListNode(0)
        var cur: ListNode? = dummyHead
        while (a != null || b != null || carry > 0) {
            var sum = 0
            if (a != null) {
                sum += a.`val`
                a = a.next
            }
            if (b != null) {
                sum += b.`val`
                b = b.next
            }
            sum += carry
            carry = sum / 10
            val node = ListNode(sum % 10)
            cur?.next = node
            cur = cur?.next
        }
        return dummyHead.next
    }
}

class Solution0019 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummyHead = ListNode(0).apply { next = head }
        var fast: ListNode? = dummyHead
        for (i in 0..<n) {
            fast = fast?.next
        }
        var slow: ListNode? = dummyHead
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next
        }
        val delNode = slow?.next
        slow?.next = delNode?.next
        delNode?.next = null
        return dummyHead.next
    }
}

class Solution0024 {
    fun swapPairs(head: ListNode?): ListNode? {
        val node3 = head?.next?.next
        val node2 = head?.next
        node2?.next = null
        val node1 = head
        node1?.next = null
        if (node1 == null) return null
        if (node2 == null) return head
        node2.next = node1
        node1.next = swapPairs(node3)
        return node2
    }
}

class Solution0025 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (k == 1) return head
        var cur = head
        var count = 0
        while (cur != null) {
            ++count
            cur = cur.next
        }
        if (count < k) return head
        val dummyHead = ListNode(0)
        cur = head
        for (i in 0..<k) {
            val node = cur
            cur = cur?.next
            node?.next = dummyHead.next
            dummyHead.next = node
        }
        head?.next = reverseKGroup(cur, k)
        return dummyHead.next
    }
}

class Solution0138 {
    data class Node(val `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null
        var cur = node
        while (cur != null) {
            val copyNode = Node(cur.`val`)
            copyNode.next = cur.next
            cur.next = copyNode
            cur = copyNode.next
        }
        cur = node
        while (cur != null) {
            val copyNode = cur.next
            copyNode?.random = cur.random?.next
            cur = cur.next?.next
        }
        val dummyHead = Node(0)
        cur = node
        var cur2: Node? = dummyHead
        while (cur != null) {
            val copyNode = cur.next
            cur.next = copyNode?.next
            cur = cur.next
            copyNode?.next = null

            cur2?.next = copyNode
            cur2 = cur2?.next
        }
        return dummyHead.next
    }
}

class Solution0148 {
    fun sortList(head: ListNode?): ListNode? {
        var count = 0
        var cur = head
        while (cur != null) {
            ++count
            cur = cur.next
        }
        if (count < 2) return head
        count = (count - 1) / 2
        cur = head
        while (count > 0) {
            cur = cur?.next
            --count
        }
        var right = cur?.next
        cur?.next = null
        var left = head
        left = sortList(left)
        right = sortList(right)
        val dummyHead = ListNode(0)
        cur = dummyHead
        while (left != null && right != null) {
            if (left.`val` < right.`val`) {
                val node = left
                left = left.next
                node.next = null
                cur?.next = node
                cur = cur?.next
            } else {
                val node = right
                right = right.next
                node.next = null
                cur?.next = node
                cur = cur?.next
            }
        }
        if (left != null) {
            cur?.next = left
        }
        if (right != null) {
            cur?.next = right
        }
        return dummyHead.next
    }
}

class Solution0023 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val queue = PriorityQueue<ListNode> { a, b -> a.`val` - b.`val` }
        for (l in lists) {
            if (l != null) {
                queue.offer(l)
            }
        }
        val dummyHead = ListNode(0)
        var cur: ListNode? = dummyHead
        while (queue.isNotEmpty()) {
            var list = queue.poll()
            if (queue.isEmpty()) {
                cur?.next = list
                break
            }
            val node = list
            list = list.next
            node.next = null
            if (list != null) {
                queue.offer(list)
            }
            cur?.next = node
            cur = cur?.next
        }
        return dummyHead.next
    }
}

class Solution0146 {
    class LRUCache(val capacity: Int) {
        data class Node(
            var key: Int,
            var value: Int,
            var prev: Node? = null,
            var next: Node? = null,
        )

        private val head = Node(-1, -1)
        private val tail = Node(-1, -1)
        private val map = HashMap<Int, Node>()

        init {
            head.next = tail
            tail.prev = head
        }

        fun get(key: Int): Int {
            if (key !in map) {
                return -1
            }
            val node = map[key] ?: return -1
            if (head.next != node) {
                deleteNode(node)
                addToHead(node)
            }
            return node.value
        }

        fun put(key: Int, value: Int) {
            if (key in map) {
                val node = map[key] ?: return
                node.value = value
                if (head.next != node) {
                    deleteNode(node)
                    addToHead(node)
                }
            } else {
                val node = Node(key, value)
                addToHead(node)
                map[key] = node
                while (map.size > capacity) {
                    val delNode = tail.prev!!
                    deleteNode(delNode)
                    map.remove(delNode.key)
                }
            }
        }

        private fun deleteNode(node: Node) {
            node.prev?.next = node.next
            node.next?.prev = node.prev
            node.next = null
            node.prev = null
        }

        private fun addToHead(node: Node) {
            val next = head.next
            head.next = null
            next?.prev = null

            head.next = node
            node.next = next
            next?.prev = node
            node.prev = head
        }
    }
}
// endregion

// region 二叉树
data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
)

class Solution0094 {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        inorder(root, res)
        return res
    }

    private fun inorder(root: TreeNode?, res: MutableList<Int>) {
        if (root == null) return
        inorder(root.left, res)
        res.add(root.`val`)
        inorder(root.right, res)
    }
}

class Solution0104 {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
    }
}

class Solution0226 {
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null
        val left = root.left
        val right = root.right
        root.left = null
        root.right = null

        root.left = invertTree(right)
        root.right = invertTree(left)
        return root
    }
}

class Solution0101 {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return isSame(root.left, invertTree(root.right))
    }

    private fun invertTree(node: TreeNode?): TreeNode? {
        if (node == null) return null
        val left = node.left
        val right = node.right
        node.left = null
        node.right = null
        node.left = invertTree(right)
        node.right = invertTree(left)
        return node
    }

    private fun isSame(a: TreeNode?, b: TreeNode?): Boolean {
        if (a == null && b == null) return true
        if (a == null || b == null) return false
        return a.`val` == b.`val` && isSame(a.left, b.left) && isSame(a.right, b.right)
    }
}

class Solution0543 {

    private var maxDiameter = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        maxDiameter = 0
        maxDepth(root)
        return maxDiameter
    }

    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)
        maxDiameter = maxOf(maxDiameter, leftDepth + rightDepth)
        return 1 + maxOf(leftDepth, rightDepth)
    }
}

class Solution0102 {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (root == null) return res
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val n = queue.size
            val list = mutableListOf<Int>()
            for (i in 0..<n) {
                val node = queue.poll()
                list.add(node.`val`)

                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
            res.add(list)
        }
        return res
    }
}

class Solution0108 {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return sortedArrayToBST(nums, 0, nums.size - 1)
    }

    private fun sortedArrayToBST(nums: IntArray, start: Int, end: Int): TreeNode? {
        if (start > end) return null
        if (start == end) return TreeNode(nums[start])
        val mid = start + (end - start) / 2
        return TreeNode(nums[mid]).apply {
            left = sortedArrayToBST(nums, start, mid - 1)
            right = sortedArrayToBST(nums, mid + 1, end)
        }
    }
}

class Solution0098 {
    fun isValidBST(root: TreeNode?): Boolean {
        val values = mutableListOf<Int>()
        inorder(root, values)
        return isSorted(values)
    }

    private fun isSorted(values: List<Int>): Boolean {
        var i = 0
        val n = values.size
        while (i + 1 < n) {
            if (values[i] >= values[i + 1]) return false
            ++i
        }
        return true
    }

    private fun inorder(root: TreeNode?, values: MutableList<Int>) {
        if (root == null) return
        inorder(root.left, values)
        values.add(root.`val`)
        inorder(root.right, values)
    }
}

class Solution0230 {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        root ?: throw IllegalArgumentException()
        val stack = Stack<TreeNode>()
        stack.push(root)
        var count = 0
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            val left = node.left
            val right = node.right
            if (left == null && right == null) {
                if (++count == k) {
                    return node.`val`
                }
            } else {
                node.left = null
                node.right = null
                right?.let { stack.push(it) }
                stack.push(node)
                left?.let { stack.push(it) }
            }
        }
        throw IllegalArgumentException()
    }
}

class Solution0199 {
    fun rightSideView(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val n = queue.size
            for (i in 1..n) {
                val node = queue.poll()
                if (i == n) {
                    res.add(node.`val`)
                }
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
        }
        return res
    }
}

class Solution0114 {
    fun flatten(root: TreeNode?): Unit {
        flattenInner(root)
    }

    private data class Pair(
        var head: TreeNode? = null, var tail: TreeNode? = null
    ) {
        fun isEmpty(): Boolean = head == null && tail == null
    }

    private fun flattenInner(root: TreeNode?): Pair {
        val res = Pair()
        if (root == null) return res
        val left = root.left
        val right = root.right
        root.left = null
        root.right = null
        val leftRes = flattenInner(left)
        val rightRes = flattenInner(right)

        var cur = root
        if (leftRes.isEmpty().not()) {
            cur.right = leftRes.head
            cur = leftRes.tail
        }
        if (rightRes.isEmpty().not()) {
            cur?.right = rightRes.head
            cur = rightRes.tail
        }
        res.head = root
        res.tail = cur
        return res
    }
}

class Solution0105 {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return build(preorder, 0, preorder.size - 1, inorder, 0, inorder.size - 1)
    }

    private fun build(
        preorder: IntArray, preL: Int, preR: Int, inorder: IntArray, inL: Int, inR: Int
    ): TreeNode? {
        if (preL > preR) return null
        if (preL == preR) return TreeNode(preorder[preL])
        val rootVal = preorder[preL]
        var i = inL
        while (i <= inR) {
            if (inorder[i] == rootVal) {
                break
            }
            ++i
        }
        val leftCount = i - inL
        val rightCount = inR - i
        val root = TreeNode(rootVal)
        // r - l + 1 = count => r = l + count - 1
        root.left = build(preorder, preL + 1, (preL + 1) + leftCount - 1, inorder, inL, i - 1)
        // r - l + 1 = count => l = r - count + 1
        root.right = build(preorder, preR - rightCount + 1, preR, inorder, i + 1, inR)
        return root
    }
}

class Solution0437 {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        return pathSumInner(root, targetSum.toLong())
    }

    private fun pathSumInner(root: TreeNode?, targetSum: Long): Int {
        if (root == null) return 0
        return rootSum(root, targetSum) + pathSumInner(root.left, targetSum) + pathSumInner(root.right, targetSum)
    }

    private fun rootSum(root: TreeNode?, targetSum: Long): Int {
        if (root == null) return 0
        var res = 0
        if (root.`val`.toLong() == targetSum) res += 1
        res += rootSum(root.left, targetSum - root.`val`) + rootSum(root.right, targetSum - root.`val`)
        return res
    }
}

class Solution0236 {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null
        if (p == null || q == null) return null
        val parentMap = mutableMapOf<TreeNode, TreeNode>()
        var pVisited = false
        var qVisited = false
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            listOfNotNull(node.left, node.right).forEach {
                parentMap[it] = node
                if (it.`val` == p.`val`) {
                    pVisited = true
                }
                if (it.`val` == q.`val`) {
                    qVisited = true
                }
            }
            if (pVisited && qVisited) {
                break
            }
        }
        val pPrentSet = hashSetOf<TreeNode>()
        var cur = p
        while (cur != null) {
            pPrentSet.add(cur)
            cur = parentMap[cur]
        }
        cur = q
        while (cur != null) {
            if (cur in pPrentSet) {
                return cur
            }
            cur = parentMap[cur]
        }
        return null
    }
}

class Solution0124 {
    private var res = Int.MIN_VALUE

    fun maxPathSum(root: TreeNode?): Int {
        res = Int.MIN_VALUE
        rootSum(root)
        return res
    }

    private fun rootSum(root: TreeNode?): Int {
        if (root == null) return 0
        val leftRootSum = maxOf(0, rootSum(root.left))
        val rightRootSum = maxOf(0, rootSum(root.right))
        res = maxOf(res, root.`val` + leftRootSum + rightRootSum)
        return root.`val` + maxOf(leftRootSum, rightRootSum)
    }
}

class Solution0111 {
    fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val queue = LinkedList<Pair<TreeNode, Int>>()
        queue.offer(root to 1)
        while (queue.isNotEmpty()) {
            val (node, depth) = queue.poll()
            if (node.left == null && node.right == null) {
                return depth
            }
            node.left?.let {
                queue.offer(it to depth + 1)
            }
            node.right?.let {
                queue.offer(it to depth + 1)
            }
        }
        return -1
    }
}
// endregion

// region 二分查找
class Solution0035 {
    fun searchInsert(nums: IntArray, target: Int): Int {
        val n = nums.size
        var l = 0
        var r = n - 1
        while (l <= r) {
            val mid = l + (r - l) / 2
            if (target == nums[mid]) {
                return mid
            } else if (target < nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return l
    }
}

class Solution0074 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size
        var i = m - 1
        var j = 0
        while (i >= 0 && j < n) {
            if (target == matrix[i][j]) {
                return true
            } else if (target < matrix[i][j]) {
                --i
            } else {
                ++j
            }
        }
        return false
    }
}

class Solution0034 {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val lower = searchLower(nums, target)
        if (lower == -1) {
            return intArrayOf(-1, -1)
        } else {
            return intArrayOf(lower, searchUpper(nums, target))
        }
    }

    private fun searchUpper(nums: IntArray, target: Int): Int {
        val n = nums.size
        var l = 0
        var r = n - 1
        while (l <= r) {
            val mid = l + (r - l) / 2
            if (target == nums[mid]) {
                if (nums.getOrNull(mid + 1) != nums[mid]) {
                    return mid
                } else {
                    l = mid + 1
                }
            } else if (target < nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return -1
    }

    private fun searchLower(nums: IntArray, target: Int): Int {
        val n = nums.size
        var l = 0
        var r = n - 1
        while (l <= r) {
            val mid = l + (r - l) / 2
            if (target == nums[mid]) {
                if (nums.getOrNull(mid - 1) != nums[mid]) {
                    return mid
                } else {
                    r = mid - 1
                }
            } else if (target < nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return -1
    }
}

class Solution0033 {
    fun search(nums: IntArray, target: Int): Int {
        val n = nums.size
        var l = 0
        var r = n - 1
        while (l <= r) {
            val mid = l + (r - l) / 2
            if (target == nums[mid]) {
                return mid
            }
            // 左半部分有序
            if (nums[l] <= nums[mid]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1
                } else {
                    l = mid + 1
                }
            }
            // 右半部分有序
            else {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
        }
        return -1
    }
}

class Solution0153 {
    fun findMin(nums: IntArray): Int {
        val n = nums.size
        var l = 0
        var r = n - 1
        while (l <= r) {
            if (nums[l] <= nums[r]) {
                return nums[l]
            }
            val mid = l + (r - l) / 2
            if (nums[l] <= nums[mid]) {
                l = mid
            } else {
                r = mid
            }
        }
        return -1
    }
}

class Solution0004 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size
        val aux = IntArray(m + n)
        var i = 0
        var j = 0
        var k = 0
        while (i < m || j < n) {
            if (i == m) {
                aux[k] = nums2[j]
                ++j
                ++k
            } else if (j == n) {
                aux[k] = nums1[i]
                ++i
                ++k
            } else if (nums1[i] < nums2[j]) {
                aux[k] = nums1[i]
                ++i
                ++k
            } else {
                aux[k] = nums2[j]
                ++j
                ++k
            }
        }
        if ((m + n) % 2 == 1) {
            return aux[(m + n) / 2].toDouble()
        } else {
            return (aux[(m + n) / 2] + aux[(m + n) / 2 - 1]) / 2.0
        }
    }
}
// endregion

// region 回溯
class Solution0046 {
    fun permute(nums: IntArray): List<List<Int>> {
        val n = nums.size
        val used = BooleanArray(n)
        val res = mutableListOf<List<Int>>()
        generatePermutations(nums, used, mutableListOf(), res)
        return res
    }

    private fun generatePermutations(
        nums: IntArray, used: BooleanArray, path: MutableList<Int>, res: MutableList<List<Int>>
    ) {
        if (path.size == nums.size) {
            res.add(path.toList())
            return
        }
        for (i in 0..<nums.size) {
            if (used[i].not()) {
                used[i] = true
                path.add(nums[i])
                generatePermutations(nums, used, path, res)
                path.removeAt(path.size - 1)
                used[i] = false
            }
        }
    }
}

class Solution0078 {
    fun subsets(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        findSubsets(nums, 0, mutableListOf(), res)
        return res
    }

    private fun findSubsets(nums: IntArray, curIndex: Int, path: MutableList<Int>, res: MutableList<List<Int>>) {
        if (curIndex == nums.size) {
            res.add(path.toList())
            return
        }
        // 不将nums[curIndex]加入path
        findSubsets(nums, curIndex + 1, path, res)

        // 将nums[curIndex]加入path
        path.add(nums[curIndex])
        findSubsets(nums, curIndex + 1, path, res)
        path.removeAt(path.size - 1)
    }
}

class Solution0017 {
    private val letters = arrayOf(
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        val res = mutableListOf<String>()
        if (digits.isEmpty()) return res
        generateCombinations(digits, 0, CharArray(digits.length), res)
        return res
    }

    private fun generateCombinations(digits: String, curIndex: Int, path: CharArray, res: MutableList<String>) {
        if (curIndex == digits.length) {
            res.add(String(path))
            return
        }
        for (c in letters[digits[curIndex] - '0']) {
            path[curIndex] = c
            generateCombinations(digits, curIndex + 1, path, res)
            path[curIndex] = ' '
        }
    }
}

class Solution0039 {
    fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val res = mutableListOf<List<Int>>()
        generateCombinations(nums, target, 0, 0, mutableListOf(), res)
        return res
    }

    private fun generateCombinations(
        nums: IntArray, target: Int, curIndex: Int, curSum: Int, path: MutableList<Int>, res: MutableList<List<Int>>
    ) {
        if (curIndex == nums.size || curSum > target) {
            return
        }
        if (curSum == target) {
            res.add(path.toList())
            return
        }

        // 不将nums[curIndex]加入path
        generateCombinations(nums, target, curIndex + 1, curSum, path, res)

        // 将nums[curIndex]加入path
        path.add(nums[curIndex])
        generateCombinations(nums, target, curIndex, curSum + nums[curIndex], path, res)
        path.removeAt(path.size - 1)
    }
}

class Solution0022 {
    fun generateParenthesis(n: Int): List<String> {
        if (n == 0) return emptyList()
        return generate(n)
    }

    private val memo = hashMapOf<Int, List<String>>()

    private fun generate(n: Int): List<String> {
        if (n == 0) return listOf("")
        if (n == 1) return listOf("()")
        if (n in memo) return memo[n]!!
        val res = mutableListOf<String>()
        for (i in 0..<n) {
            val j = n - i - 1
            for (a in generate(i)) {
                for (b in generate(j)) {
                    res.add("($a)$b")
                }
            }
        }
        memo[n] = res
        return res
    }
}

class Solution0079 {
    private val directions = arrayOf(
        intArrayOf(-1, 0), // up
        intArrayOf(0, 1), // right
        intArrayOf(1, 0), // down
        intArrayOf(0, -1), // left
    )

    fun exist(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (dfs(board, m, n, i, j, word, 0)) return true
            }
        }
        return false
    }

    private fun dfs(
        board: Array<CharArray>, m: Int, n: Int, curI: Int, curJ: Int, word: String, curIndex: Int
    ): Boolean {
        if (curIndex == word.length - 1) {
            return board[curI][curJ] == word[word.length - 1]
        }
        if (board[curI][curJ] != word[curIndex]) {
            return false
        }
        val temp = board[curI][curJ]
        board[curI][curJ] = ' '
        for (d in directions) {
            val newI = curI + d[0]
            val newJ = curJ + d[1]
            if (newI in 0..<m && newJ in 0..<n && board[newI][newJ] != ' ') {
                if (dfs(board, m, n, newI, newJ, word, curIndex + 1)) {
                    return true
                }
            }
        }
        board[curI][curJ] = temp
        return false
    }
}

class Solution0131 {
    fun partition(s: String): List<List<String>> {
        val res = mutableListOf<List<String>>()
        if (s.isEmpty()) return res
        val n = s.length
        // isPalindrome[i][j]表示s[i..j]是否为回文字符串
        val isPalindrome = Array(n) { BooleanArray(n) { true } }
        // 外层倒序遍历
        for (i in n - 1 downTo 0) {
            // 内层顺序遍历
            for (j in i + 1..<n) {
                isPalindrome[i][j] = s[i] == s[j] && isPalindrome[i + 1][j - 1]
            }
        }
        dfs(s, 0, isPalindrome, mutableListOf(), res)
        return res
    }

    private fun dfs(
        s: String, i: Int, isPalindrome: Array<BooleanArray>, path: MutableList<String>, res: MutableList<List<String>>
    ) {
        if (i == s.length) {
            res.add(path.toList())
            return
        }
        for (j in i..<s.length) {
            if (isPalindrome[i][j]) {
                path.add(s.substring(i, j + 1))
                dfs(s, j + 1, isPalindrome, path, res)
                path.removeAt(path.size - 1)
            }
        }
    }
}

class Solution0051 {
    private lateinit var col: BooleanArray
    private lateinit var dia1: BooleanArray
    private lateinit var dia2: BooleanArray
    private val res = mutableListOf<List<String>>()

    private fun generateBoard(n: Int, row: List<Int>): List<String> =
        row.map { index -> String(CharArray(n) { if (it == index) 'Q' else '.' }) }

    private fun putQueen(n: Int, i: Int, solution: MutableList<Int>) {
        if (i == n) {
            res.add(generateBoard(n, solution))
            return
        }
        for (j in 0 until n) {
            if (!col[j] && !dia1[i + j] && !dia2[i - j + n - 1]) {
                col[j] = true
                dia1[i + j] = true
                dia2[i - j + n - 1] = true
                solution.add(j)
                putQueen(n, i + 1, solution)
                solution.removeAt(solution.size - 1)
                col[j] = false
                dia1[i + j] = false
                dia2[i - j + n - 1] = false
            }
        }
    }

    fun solveNQueens(n: Int): List<List<String>> {
        res.clear()
        if (n == 0) return res
        col = BooleanArray(n)
        dia1 = BooleanArray(2 * n - 1)
        dia2 = BooleanArray(2 * n - 1)
        putQueen(n, 0, mutableListOf())
        return res
    }
}
// endregion

// region 贪心算法
class Solution0121 {
    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        if (n < 2) return 0
        var res = 0
        var curMin = prices[0]
        for (i in 1..<n) {
            res = maxOf(res, prices[i] - curMin)
            curMin = minOf(curMin, prices[i])
        }
        return res
    }
}

class Solution0055 {
    fun canJump(nums: IntArray): Boolean {
        val n = nums.size
        var maxPos = 0
        var i = 0
        while (i <= maxPos) {
            maxPos = maxOf(maxPos, i + nums[i])
            if (maxPos >= n - 1) return true
            ++i
        }
        return false
    }
}

class Solution0045 {
    fun jump(nums: IntArray): Int {
        val n = nums.size
        val res = IntArray(n) { Int.MAX_VALUE }
        res[0] = 0
        for (i in 0..<n) {
            var j = 1
            while (j <= nums[i] && i + j < n) {
                res[i + j] = minOf(res[i + j], res[i] + 1)
                ++j
            }
        }
        return res[n - 1]
    }
}

class Solution0763 {
    fun partitionLabels(s: String): List<Int> {
        val n = s.length
        val lastIndex = IntArray(26)
        for (i in 0..<n) {
            lastIndex[s[i] - 'a'] = i
        }
        val res = mutableListOf<Int>()
        var start = 0
        var end = 0
        for (i in 0..<n) {
            end = maxOf(end, lastIndex[s[i] - 'a'])
            if (i == end) {
                res.add(end - start + 1)
                start = end + 1
            }
        }
        return res
    }
}
// endregion

// region 动态规划
class Solution0070 {
    fun climbStairs(n: Int): Int {
        if (n == 0 || n == 1) return 1
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }
}

class Solution0118 {
    fun generate(numRows: Int): List<List<Int>> {
        val res = mutableListOf(listOf(1))
        for (i in 1..<numRows) {
            val list = mutableListOf<Int>()
            for (j in 0..i) {
                list.add((res[i - 1].getOrNull(j - 1) ?: 0) + (res[i - 1].getOrNull(j) ?: 0))
            }
            res.add(list)
        }
        return res
    }
}

class Solution0198 {
    fun rob(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        if (n == 1) return nums[0]
        var prevMax = nums[0]
        var curMax = maxOf(nums[0], nums[1])
        for (i in 2..<n) {
            val temp = curMax
            curMax = maxOf(nums[i] + prevMax, curMax)
            prevMax = temp
        }
        return curMax
    }
}

class Solution0198V2 {
    fun rob(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        if (n == 1) return nums[0]
        val dp = IntArray(n)
        dp[0] = nums[0]
        dp[1] = maxOf(nums[0], nums[1])
        for (i in 2..<n) {
            dp[i] = maxOf(nums[i] + dp[i - 2], dp[i - 1])
        }
        return dp[n - 1]
    }
}

class Solution0279 {
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { i -> i }
        for (i in 1..n) {
            var j = 1
            while (j * j <= i) {
                dp[i] = minOf(dp[i], 1 + dp[i - j * j])
                ++j
            }
        }
        return dp[n]
    }
}

class Solution0322 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        for (c in 1..amount) {
            for (coin in coins) {
                if (c >= coin) {
                    dp[c] = minOf(dp[c], 1 + dp[c - coin])
                }
            }
        }
        return if (dp[amount] > amount) -1 else dp[amount]
    }
}

class Solution0139 {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length
        // dp[i]表示s[0..i)是否能够由字典中的单词组成
        val dp = BooleanArray(n + 1)
        val wordSet = wordDict.toSet()
        dp[0] = true
        for (i in 0..n) {
            if (dp[i]) {
                for (j in i + 1..n) {
                    if (s.substring(i, j) in wordSet) {
                        dp[j] = true
                        if (j == n) return true
                    }
                }
            }
        }
        return dp[n]
    }
}

class Solution0300 {
    fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size

        // dp[i]表示以nums[i]结尾的最长递增子序列的长度
        val dp = IntArray(n) { 1 }
        for (i in 0..<n) {
            for (j in 0..<i) {
                if (nums[i] > nums[j]) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
        }
        return dp.max()
    }
}

class Solution0152 {
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        val minProduct = IntArray(n)
        val maxProduct = IntArray(n)
        minProduct[0] = nums[0]
        maxProduct[0] = nums[0]
        for (i in 1..<n) {
            val a = nums[i]
            val b = nums[i] * minProduct[i - 1]
            val c = nums[i] * maxProduct[i - 1]
            minProduct[i] = minOf(a, b, c)
            maxProduct[i] = maxOf(a, b, c)
        }
        return maxProduct.max()
    }
}

class Solution0152V2 {
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        val minProduct = mutableListOf<BigInteger>()
        val maxProduct = mutableListOf<BigInteger>()
        minProduct.add(BigInteger.valueOf(nums[0].toLong()))
        maxProduct.add(BigInteger.valueOf(nums[0].toLong()))
        for (i in 1..<n) {
            val a = BigInteger.valueOf(nums[i].toLong())
            val b = a.multiply(minProduct[i - 1])
            val c = a.multiply(maxProduct[i - 1])
            minProduct.add(minOf(a, b, c))
            maxProduct.add(maxOf(a, b, c))
        }
        return maxProduct.max().toInt()
    }
}

class Solution0152V3 {
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        var prevMin = nums[0]
        var prevMax = nums[0]
        var res = nums[0]
        for (i in 1..<n) {
            val a = nums[i]
            val b = nums[i] * prevMin
            val c = nums[i] * prevMax
            val curMin = minOf(a, b, c)
            val curMax = maxOf(a, b, c)
            res = maxOf(res, curMax)
            prevMin = curMin
            prevMax = curMax
        }
        return res
    }
}

class Solution0152V4 {
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        val first = BigInteger.valueOf(nums[0].toLong())
        var prevMin = first
        var prevMax = first
        var res = first
        for (i in 1..<n) {
            val a = BigInteger.valueOf(nums[i].toLong())
            val b = a.multiply(prevMin)
            val c = a.multiply(prevMax)
            val curMin = minOf(a, b, c)
            val curMax = maxOf(a, b, c)
            res = maxOf(res, curMax)
            prevMin = curMin
            prevMax = curMax
        }
        return res.toInt()
    }
}

class Solution0416 {
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) sum += num
        if (sum % 2 == 1) return false
        val C = sum / 2
        val n = nums.size
        // dp[i][j]表示能够取出nums[0..i]范围的若干元素，使得它们的和等于j
        val dp = Array(n) { BooleanArray(C + 1) }
        // 第一行单独处理
        for (j in 0..C) {
            dp[0][j] = j == nums[0]
        }
        for (i in 1..<n) {
            for (j in 0..C) {
                // 不取出nums[i]
                val a = dp[i - 1][j]
                // 取出nums[i]
                val b = j >= nums[i] && dp[i - 1][j - nums[i]]
                dp[i][j] = a || b
            }
        }
        return dp[n - 1][C]
    }
}

class Solution0416V2 {
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) sum += num
        if (sum % 2 == 1) return false
        val C = sum / 2
        val n = nums.size
        // dp[i][j]表示从nums[0..i)范围内取出若干个元素，使得它们的和等于j
        val dp = Array(n + 1) { BooleanArray(C + 1) }
        dp[0][0] = true
        for (i in 1..n) {
            for (j in 0..C) {
                dp[i][j] = dp[i - 1][j] || (j > nums[i - 1] && dp[i - 1][j - nums[i - 1]])
            }
        }
        return dp[n][C]
    }
}

class Solution0032 {
    fun longestValidParentheses(s: String): Int {
        val n = s.length
        val dp = IntArray(n) // dp[i]表示以s[i]结尾的合法子串的最大长度
        var res = 0
        for (i in 1..<n) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = 2 + (dp.getOrNull(i - 2) ?: 0)
                } else {
                    val index = i - dp[i - 1] - 1
                    if (index >= 0 && s[index] == '(') {
                        dp[i] = 2 + dp[i - 1] + (dp.getOrNull(index - 1) ?: 0)
                    }
                }
                res = maxOf(res, dp[i])
            }
        }
        return res
    }
}
// endregion

// region 多维动态规划
class Solution0062 {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) }
        for (i in 0..<m) {
            dp[i][0] = 1
        }
        for (j in 0..<n) {
            dp[0][j] = 1
        }
        for (i in 1..<m) {
            for (j in 1..<n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp[m - 1][n - 1]
    }
}

class Solution0064 {
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) }
        dp[0][0] = grid[0][0]
        for (j in 1..<n) {
            dp[0][j] = dp[0][j - 1] + grid[0][j]
        }
        for (i in 1..<m) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        }
        for (i in 1..<m) {
            for (j in 1..<n) {
                dp[i][j] = grid[i][j] + minOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
        return dp[m - 1][n - 1]
    }
}

class Solution0005 {
    fun longestPalindrome(s: String): String {
        val n = s.length
        // isPalindrome[i][j]表示s[i..j]是否为回文串
        val isPalindrome = Array(n) { BooleanArray(n) { true } }
        // 外层倒序遍历
        for (i in n - 1 downTo 0) {
            //内存顺序遍历
            for (j in i + 1..<n) {
                isPalindrome[i][j] = s[i] == s[j] && isPalindrome[i + 1][j - 1]
            }
        }
        var res = ""
        var maxLen = 0
        for (i in 0..<n) {
            for (j in i..<n) {
                if (isPalindrome[i][j]) {
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1
                        res = s.substring(i, j + 1)
                    }
                }
            }
        }
        return res
    }
}

class Solution1143 {
    fun longestCommonSubsequence(s: String, t: String): Int {
        val sLen = s.length
        val tLen = t.length
        // dp[i][j]表示s[0..i)与t[0..j)的最长公共子序列的长度
        val dp = Array(sLen + 1) { IntArray(tLen + 1) }
        for (i in 1..sLen) {
            for (j in 1..tLen) {
                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[sLen][tLen]
    }
}

class Solution0072 {
    fun minDistance(s: String, t: String): Int {
        val m = s.length
        val n = t.length
        // dp[i][j] 表示s[0..i)与t[0..j)的编辑距离
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (j in 0..n) {
            dp[0][j] = j
        }
        for (i in 0..m) {
            dp[i][0] = i
        }
        for (i in 1..m) for (j in 1..n) {
            val left = dp[i][j - 1] + 1
            val up = dp[i - 1][j] + 1
            val leftUp = dp[i - 1][j - 1] + if (s[i - 1] == t[j - 1]) 0 else 1
            dp[i][j] = minOf(left, up, leftUp)
        }
        return dp[m][n]
    }
}
// endregion

// region 栈
class Solution0020 {
    fun isValid(s: String): Boolean {
        val n = s.length
        if (n % 2 == 1) return false
        val stack = ArrayDeque<Char>()
        for (c in s) {
            if (c == '(' || c == '[' || c == '{') {
                stack.addLast(c)
            } else {
                if (stack.isEmpty()) return false
                val last = stack.removeLast()
                if (last == '(' && c != ')') return false
                if (last == '[' && c != ']') return false
                if (last == '{' && c != '}') return false
            }
        }
        return stack.isEmpty()
    }
}

class Solution0155 {
    class MinStack() {

        private data class Node(val value: Int, val min: Int)

        private val nodes = mutableListOf<Node>()

        fun push(`val`: Int) {
            val node = Node(value = `val`, min = if (nodes.isEmpty()) `val` else minOf(`val`, nodes.last().min))
            nodes.add(node)
        }

        fun pop() {
            if (nodes.isNotEmpty()) {
                nodes.removeLast()
            }
        }

        fun top(): Int {
            return nodes.last().value
        }

        fun getMin(): Int {
            return nodes.last().min
        }

    }
}

class Solution0394 {
    fun decodeString(s: String): String {
        val count = Stack<Int>()
        val stack = Stack<String>()
        var i = 0
        val n = s.length
        while (i < n) {
            val c = s[i]
            if (c in 'a'..'z') {
                stack.push(c.toString())
                ++i
            } else if (c in '0'..'9') {
                var curCount = 0
                while (i < n && s[i] in '0'..'9') {
                    curCount = 10 * curCount + (s[i] - '0')
                    ++i
                }
                count.push(curCount)
            } else if (c == '[') {
                stack.push(c.toString())
                ++i
            } else if (c == ']') {
                var curStr = ""
                while (stack.peek() != "[") {
                    curStr = stack.pop() + curStr
                }
                stack.pop()//pop "["
                val curCount = count.pop()
                curStr = curStr.repeat(curCount)
                stack.push(curStr)
                ++i
            } else {
                throw IllegalArgumentException()
            }
        }
        return stack.joinToString("")
    }
}

class Solution0739 {
    fun dailyTemperatures(nums: IntArray): IntArray {
        val n = nums.size
        val res = IntArray(n)
        val stack = Stack<Int>()
        var i = 0
        while (i < n) {
            val num = nums[i]
            while (stack.isNotEmpty() && nums[stack.peek()] < num) {
                val prevIndex = stack.pop()
                res[prevIndex] = i - prevIndex
            }
            stack.push(i)
            ++i
        }
        return res
    }
}


// endregion

// region 堆
class Solution0215 {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        var l = 0
        var r = nums.size - 1
        val resIndex = nums.size - k
        while (l <= r) {
            val (lt, gt) = partition(nums, l, r)
            if (resIndex <= lt) {
                r = lt
            } else if (resIndex >= gt) {
                l = gt
            } else {
                return nums[lt + 1]
            }
        }
        throw IllegalArgumentException()
    }

    private fun partition(nums: IntArray, l: Int, r: Int): Pair<Int, Int> {
        val randomIndex = l + Random.nextInt(r - l + 1)
        val e = nums[randomIndex]
        var lt = l - 1 // nums[l..lt] < 0
        var gt = r + 1 // nums[gt..r] > 0
        var i = l // nums[lt+1..i) == e
        while (i < gt) {
            if (nums[i] < e) {
                nums[lt + 1] = nums[i].also { nums[i] = nums[lt + 1] }
                ++lt
                ++i
            } else if (nums[i] > e) {
                nums[i] = nums[gt - 1].also { nums[gt - 1] = nums[i] }
                --gt
            } else {
                ++i
            }
        }
        return lt to gt
    }
}

class Solution0347 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val freq = hashMapOf<Int, Int>()
        for (num in nums) {
            freq[num] = freq.getOrDefault(num, 0) + 1
        }
        val queue = PriorityQueue<Int> { a, b -> freq.getOrDefault(a, 0) - freq.getOrDefault(b, 0) }
        for (e in freq) {
            queue.offer(e.key)
            if (queue.size > k) {
                queue.poll()
            }
        }
        val res = IntArray(k)
        for (i in 0..<k) {
            res[i] = queue.poll()
        }
        return res
    }
}

class Solution0295 {
    class MedianFinder() {
        private val big = PriorityQueue<Int> { a, b -> a - b }
        private val small = PriorityQueue<Int> { a, b -> b - a }

        fun addNum(num: Int) {
            if (small.isEmpty()) {
                small.offer(num)
            } else {
                if (num <= small.peek()) {
                    small.offer(num)
                    if (big.size + 1 < small.size) {
                        big.offer(small.poll())
                    }
                } else {
                    big.offer(num)
                    if (big.size > small.size) {
                        small.offer(big.poll())
                    }
                }
            }
        }

        fun findMedian(): Double {
            if (small.size > big.size) {
                return small.peek().toDouble()
            } else {
                return (small.peek() + big.peek()) / 2.0
            }
        }

    }
}
// endregion

// region 技巧
class Solution0136 {
    fun singleNumber(nums: IntArray): Int {
        var res = 0
        for (num in nums) {
            res = res xor num
        }
        return res
    }
}

class Solution0169 {
    fun majorityElement(nums: IntArray): Int {
        var candidate: Int? = null
        var vote = 0
        for (num in nums) {
            if (vote == 0) {
                candidate = num
            }
            vote += if (num == candidate) 1 else -1
        }
        return candidate!!
    }
}

class Solution0075 {
    fun sortColors(nums: IntArray): Unit {
        val n = nums.size
        var red = -1 // nums[0..red]为红色
        var blue = n // nums[blue..n-1]为蓝色
        var i = 0 // nums[red+1..i)为白色
        while (i < blue) {
            if (nums[i] == 0) {
                ++red
                nums[red] = nums[i].also { nums[i] = nums[red] }
                ++i
            } else if (nums[i] == 2) {
                --blue
                nums[i] = nums[blue].also { nums[blue] = nums[i] }
            } else {
                ++i
            }
        }
    }
}
// endregion
