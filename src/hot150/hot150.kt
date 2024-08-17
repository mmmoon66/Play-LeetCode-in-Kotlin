package hot150

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import kotlin.random.Random

// region 数组、字符串
class Solution0088 {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var i = m - 1
        var j = n - 1
        for (k in m + n - 1 downTo 0) {
            if (i < 0) {
                nums1[k] = nums2[j--]
            } else if (j < 0) {
                nums1[k] = nums1[i--]
            } else if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i--]
            } else {
                nums1[k] = nums2[j--]
            }
        }
    }
}

class Solution0027 {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var k = 0
        val n = nums.size
        for (i in 0..<n) {
            if (nums[i] != `val`) {
                nums[k++] = nums[i]
            }
        }
        return k
    }
}

class Solution0026 {
    fun removeDuplicates(nums: IntArray): Int {
        var prevNum: Int? = null
        var k = 0
        val n = nums.size
        for (i in 0..<n) {
            if (nums[i] != prevNum) {
                nums[k++] = nums[i]
            }
            prevNum = nums[i]
        }
        return k
    }
}

class Solution0080 {
    fun removeDuplicates(nums: IntArray): Int {
        val n = nums.size
        var prevNum: Int? = null
        var dupCount = 0
        var k = 0
        for (i in 0..<n) {
            if (nums[i] == prevNum) {
                ++dupCount
                if (dupCount <= 2) {
                    nums[k++] = nums[i]
                }
            } else {
                dupCount = 1
                nums[k++] = nums[i]
            }
            prevNum = nums[i]
        }
        return k
    }
}

class Solution0122 {
    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        var res = 0
        for (i in 1..<n) {
            res += maxOf(0, prices[i] - prices[i - 1])
        }
        return res
    }
}

class Solution0122V2 {
    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        // dp[i][0]表示第i天不持有股票的最大收益，dp[i][1]表示第i天持有股票的最大收益
        val dp = Array(n) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]
        for (i in 1..<n) {
            dp[i][0] = maxOf(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = maxOf(dp[i - 1][1], dp[i - 1][0] - prices[i])
        }
        return dp[n - 1][0]
    }
}

class Solution0274 {
    fun hIndex(citations: IntArray): Int {
        val n = citations.size
        for (i in n downTo 0) {
            var count = 0
            for (j in 0..<n) {
                if (citations[j] >= i) {
                    if (++count == i) {
                        return i
                    }
                }
            }
        }
        return 0
    }
}

class Solution0380 {
    class RandomizedSet() {

        private val data = arrayListOf<Int>()
        private val index = hashMapOf<Int, Int>()

        fun insert(`val`: Int): Boolean {
            if (`val` in index) {
                return false
            } else {
                data.add(`val`)
                index[`val`] = data.size - 1
                return true
            }
        }

        fun remove(`val`: Int): Boolean {
            if (`val` in index) {
                val i = index[`val`]!!
                index.remove(`val`)
                val lastIndex = data.size - 1
                if (i != lastIndex) {
                    data[i] = data[lastIndex].also { data[lastIndex] = data[i] }
                    index[data[i]] = i
                }
                data.removeAt(lastIndex)
                return true
            } else {
                return false
            }
        }

        fun getRandom(): Int {
            return data[Random.nextInt(data.size)]
        }

    }
}

class Solution0134 {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val n = gas.size
        var start = 0
        while (start < n) {
            var cnt = 0
            var totalGas = 0
            var totalCost = 0
            while (cnt < n) {
                val end = (start + cnt) % n
                totalGas += gas[end]
                totalCost += cost[end]
                if (totalCost > totalGas) {
                    break
                }
                ++cnt
            }
            if (cnt == n) {
                return start
            } else {
                start += cnt + 1
            }
        }
        return -1
    }
}

class Solution0013 {
    private val map = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000,
    )

    fun romanToInt(s: String): Int {
        val n = s.length
        var res = 0
        var prev = 0
        for (i in n - 1 downTo 0) {
            val c = s[i]
            val cur = map[c]!!
            if (cur >= prev) {
                res += cur
            } else {
                res -= cur
            }
            prev = cur
        }
        return res
    }
}

class Solution0012 {
    private val map = mapOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I",
    )

    fun intToRoman(num: Int): String {
        val keys = map.keys
        var res = ""
        var num = num
        for (key in keys) {
            while (num >= key) {
                res += map[key]!!
                num -= key
            }
        }
        return res
    }
}

class Solution0058 {
    fun lengthOfLastWord(s: String): Int {
        val n = s.length
        var i = n - 1
        while (i >= 0 && s[i] == ' ') {
            --i
        }
        var res = 0
        while (i >= 0 && s[i] != ' ') {
            ++res
            --i
        }
        return res
    }
}

class Solution0014 {
    fun longestCommonPrefix(strs: Array<String>): String {
        val n = strs.size
        if (n == 0) return ""
        var res = strs[0]
        for (i in 1..<n) {
            if (strs[i].length < res.length) {
                res = strs[i]
            }
        }
        while (true) {
            var allPass = true
            for (s in strs) {
                if (!s.startsWith(res)) {
                    allPass = false
                    break
                }
            }
            if (allPass) {
                return res
            } else {
                res = res.substring(0, res.length - 1)
                if (res == "") return res
            }
        }
    }
}

class Solution0151 {
    fun reverseWords(s: String): String {
        return s.trim().split("\\s+".toRegex()).reversed().joinToString(" ")
    }
}

class Solution0151V2 {
    fun reverseWords(s: String): String {
        val n = s.length
        var res = ""
        var start = 0
        while (start < n) {
            while (start < n && s[start] == ' ') {
                ++start
            }
            var end = start
            while (end < n && s[end] != ' ') {
                ++end
            }
            if (start < n) {
                res = s.substring(start, end) + if (res.isEmpty()) "" else " $res"
            }
            start = end
        }
        return res
    }
}

class Solution0028 {
    fun strStr(s: String, t: String): Int {
        val m = s.length
        val n = t.length
        for (i in 0..<m) {
            var pass = true
            for (j in 0..<n) {
                if (i + j >= m || s[i + j] != t[j]) {
                    pass = false
                    break
                }
            }
            if (pass) {
                return i
            }
        }
        return -1
    }
}

class Solution0006 {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) {
            return s
        }
        val n = s.length
        val arr = Array(numRows) { CharArray(n) { ' ' } }
        val directions = arrayOf(
            intArrayOf(1, 0),// down
            intArrayOf(-1, 1),//up-right
        )
        var i = 0
        var j = 0
        var k = 0
        arr[i][j] = s[0]
        var index = 1
        while (index < n) {
            val c = s[index]
            val newI = i + directions[k][0]
            val newJ = j + directions[k][1]
            if (newI in 0..<numRows && newJ in 0..<n) {
                i = newI
                j = newJ
                arr[i][j] = c
                ++index
            } else {
                k = (k + 1) % 2
            }
        }
        var res = ""
        for (row in arr) {
            for (item in row) {
                if (item != ' ') {
                    res += item
                }
            }
        }
        return res
    }
}

class Solution0006V2 {
    fun convert(s: String, numRows: Int): String {
        if (numRows < 2) {
            return s
        }
        val res = Array(numRows) { StringBuilder() }
        var i = 0
        var flag = -1
        for (c in s) {
            res[i].append(c)
            if (i == 0 || i == numRows - 1) {
                flag = -flag
            }
            i += flag
        }
        return res.map { it.toString() }.joinToString("")
    }
}

// 135. 分发糖果
class Solution0135 {
    fun candy(ratings: IntArray): Int {
        val n = ratings.size
        // 左规则：当ratings[i-1] < ratings[i]时，i号孩子的糖果数量将比i-1号孩子的糖果数量多
        // left[i]表示i号孩子满足左规则需要的最少糖果数量
        val left = IntArray(n) { 1 }
        for (i in 1..<n) {
            if (ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1
            }
        }
        // 右规则：当ratings[i+1] < ratings[i]时，i号孩子的糖果数量将比i+1号孩子的糖果数量多
        // right[i]表示i号满足右规则需要的最少糖果数量
        val right = IntArray(n) { 1 }
        for (i in n - 2 downTo 0) {
            if (ratings[i + 1] < ratings[i]) {
                right[i] = right[i + 1] + 1
            }
        }
        var res = 0
        for (i in 0..<n) {
            res += maxOf(left[i], right[i])
        }
        return res
    }
}

// 68. 文本左右对齐
class Solution0068 {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val n = words.size
        var right = 0
        var left = 0
        val res = mutableListOf<String>()
        while (right < n) {
            var sumLen = 0
            while (right < n && sumLen + words[right].length + (right - left)/*单词之间至少一个空格*/ <= maxWidth) {
                sumLen += words[right].length
                ++right
            }
            // 如果是最后一行
            if (right == n) {
                val sb = StringBuilder(words[left])
                for (i in left + 1..<right) {
                    sb.append(' ').append(words[i])
                }
                sb.append(" ".repeat(maxWidth - sb.length))
                res.add(sb.toString())
                break
            }
            // 如果这一行只有一个单词
            val cnt = right - left
            if (cnt == 1) {
                res.add(words[left] + " ".repeat(maxWidth - words[left].length))
                left = right
                continue
            }

            val avgCnt = (maxWidth - sumLen) / (cnt - 1)
            val remainCnt = (maxWidth - sumLen) % (cnt - 1)
            val sb = StringBuilder()
            var j = 0
            for (i in left..<right) {
                if (i == left) {
                    sb.append(words[i])
                } else {
                    val whitespaceCnt = if (j < remainCnt) avgCnt + 1 else avgCnt
                    ++j
                    sb.append(" ".repeat(whitespaceCnt)).append(words[i])
                }
            }
            res.add(sb.toString())
            left = right
        }
        return res
    }
}
// endregion

// region 双指针
class Solution0125 {
    fun isPalindrome(s: String): Boolean {
        val n = s.length
        var i = 0
        var j = n - 1
        while (i < j) {
            while (i < j && !s[i].isLetterOrDigit()) {
                ++i
            }
            while (j > i && !s[j].isLetterOrDigit()) {
                --j
            }
            if (s[i].lowercaseChar() != s[j].lowercaseChar()) {
                return false
            }
            ++i
            --j
        }
        return true
    }
}

class Solution0392 {
    fun isSubsequence(s: String, t: String): Boolean {
        var l = 0
        val r = t.length - 1
        for (c in s) {
            val index = findIFirstIndex(c, t, l, r)
            if (index == -1) {
                return false
            }
            l = index + 1
        }
        return true
    }

    private fun findIFirstIndex(c: Char, t: String, l: Int, r: Int): Int {
        for (i in l..r) {
            if (c == t[i]) {
                return i
            }
        }
        return -1
    }
}

class Solution0392V2 {
    fun isSubsequence(s: String, t: String): Boolean {
        val m = s.length
        val n = t.length
        var i = 0
        var j = 0
        while (i < m && j < n) {
            if (s[i] == t[j]) {
                ++i
            }
            ++j
        }
        return i == m
    }
}

class Solution0167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val n = numbers.size
        var i = 0
        var j = n - 1
        while (i < j) {
            val sum = numbers[i] + numbers[j]
            if (sum == target) {
                return intArrayOf(i + 1, j + 1)
            } else if (sum < target) {
                ++i
            } else {
                --j
            }
        }
        return intArrayOf()
    }
}
// endregion

// region 滑动窗口
class Solution0209 {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        val n = nums.size
        var start = 0
        var end = 0
        var sum = 0
        var minLen = Int.MAX_VALUE
        while (end < n) {
            sum += nums[end]
            while (sum >= target) {
                minLen = minOf(minLen, end - start + 1)
                sum -= nums[start]
                ++start
            }
            ++end
        }
        return if (minLen == Int.MAX_VALUE) 0 else minLen
    }
}

// 30 串联所有单词的子串
class Solution0030 {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val res = mutableListOf<Int>()
        val n = s.length
        val sumLen = words.sumOf { it.length }
        if (sumLen > n) {
            return res
        }
        val wordMap = hashMapOf<String, Int>()
        for (word in words) {
            wordMap[word] = wordMap.getOrDefault(word, 0) + 1
        }
        val wordLen = words[0].length
        var start = 0
        // end-start+1=sumLen => end = start+sumLen-1
        while (start + sumLen - 1 < n) {
            val candidate = hashMapOf<String, Int>()
            var i = start
            while (i + wordLen <= start + sumLen) {
                val sub = s.substring(i, i + wordLen)
                candidate[sub] = candidate.getOrDefault(sub, 0) + 1
                i += wordLen
            }
            if (isValidConcatenation(candidate, wordMap)) {
                res.add(start)
            }
            ++start
        }
        return res
    }

    private fun isValidConcatenation(candidate: Map<String, Int>, wordMap: Map<String, Int>): Boolean {
        for ((key, value) in wordMap) {
            if (candidate[key] != value) {
                return false
            }
        }
        return true
    }
}
// endregion

// region 区间
class Solution0228 {
    fun summaryRanges(nums: IntArray): List<String> {
        val res = mutableListOf<String>()
        val n = nums.size
        if (n == 0) {
            return res
        }
        var start = nums[0]
        var end = nums[0]
        for (i in 1..<n) {
            if (nums[i] == nums[i - 1] + 1) {
                end = nums[i]
            } else {
                if (start == end) {
                    res.add(start.toString())
                } else {
                    res.add("$start->$end")
                }
                start = nums[i]
                end = nums[i]
            }
        }
        if (start == end) {
            res.add(start.toString())
        } else {
            res.add("$start->$end")
        }
        return res
    }
}

class Solution0228V2 {
    fun summaryRanges(nums: IntArray): List<String> {
        val res = mutableListOf<String>()
        val n = nums.size
        if (n == 0) {
            return res
        }
        var i = 0
        while (i < n) {
            val start = i
            ++i
            while (i < n && nums[i] == nums[i - 1] + 1) {
                ++i
            }
            val end = i - 1
            if (start == end) {
                res.add(nums[start].toString())
            } else {
                res.add("${nums[start]}->${nums[end]}")
            }
        }
        return res
    }
}

class Solution0057 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val intervalList = intervals.toMutableList()
        intervalList.add(newInterval)
        intervalList.sortWith { a, b -> a[0] - b[0] }
        val res = mutableListOf<IntArray>()
        for (interval in intervalList) {
            if (res.isNotEmpty() && interval[0] <= res[res.size - 1][1]) {
                res[res.size - 1][1] = maxOf(res[res.size - 1][1], interval[1])
            } else {
                res.add(interval)
            }
        }
        return res.toTypedArray()
    }
}

class Solution0056 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val n = intervals.size
        if (n == 0) return emptyArray()
        intervals.sortWith { a, b -> a[0] - b[0] }
        val res = mutableListOf<IntArray>()
        for (interval in intervals) {
            if (res.isNotEmpty() && interval[0] <= res.last()[1]) {
                res.last()[1] = maxOf(res.last()[1], interval[1])
            } else {
                res.add(interval)
            }
        }
        return res.toTypedArray()
    }
}

class Solution0056V2 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val n = intervals.size
        if (n == 0) return emptyArray()
        intervals.sortWith { a, b -> a[0] - b[0] }
        val res = mutableListOf<IntArray>()
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

class Solution0452 {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        val n = points.size
        if (n == 0) {
            return 0
        }
        points.sortWith { a, b -> if (a[1] < b[1]) -1 else 1 }
        var end = points[0][1]
        var res = 1
        for (i in 1..<n) {
            val p = points[i]
            if (p[0] > end) {
                ++res
                end = p[1]
            }
        }
        return res
    }
}
// endregion

// region 链表
data class ListNode(
    var `val`: Int, var next: ListNode? = null
)

class Solution0092 {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null || left == right) return head
        val dummyHead = ListNode(-1)
        var cur: ListNode? = dummyHead
        var curHead = head
        for (i in 1..<left) {
            val node = curHead
            curHead = curHead?.next
            node?.next = null

            cur?.next = node
            cur = cur?.next
        }
        var tail: ListNode? = null
        for (i in left..right) {
            val node = curHead
            curHead = curHead?.next
            node?.next = null

            node?.next = cur?.next
            cur?.next = node
            if (i == left) {
                tail = node
            }
        }
        tail?.next = curHead
        return dummyHead.next
    }
}

class Solution0082 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) return head
        if (head.`val` == head.next!!.`val`) {
            var cur = head
            while (cur != null && cur.`val` == cur.next?.`val`) {
                cur = cur.next
            }
            return deleteDuplicates(cur?.next)
        } else {
            head.next = deleteDuplicates(head.next)
            return head
        }
    }
}

class Solution0061 {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) return null
        var cur = head
        var tail: ListNode? = null
        var n = 0
        while (cur != null) {
            ++n
            if (cur.next == null) {
                tail = cur
            }
            cur = cur.next
        }
        val r = k % n
        if (r == 0) return head

        cur = head
        for (i in 1..<n - r) {
            cur = cur?.next
        }
        val res = cur?.next
        cur?.next = null
        tail?.next = head
        return res
    }
}

// 86. 分隔链表
class Solution0086 {
    fun partition(head: ListNode?, x: Int): ListNode? {
        if (head == null || head.next == null) return head

        val ltDummyHead = ListNode(-1)
        val gtDummyHead = ListNode(-1)
        var ltCur: ListNode? = ltDummyHead
        var gtCur: ListNode? = gtDummyHead
        var cur = head
        while (cur != null) {
            val node = cur
            cur = cur.next
            node.next = null

            if (node.`val` < x) {
                ltCur?.next = node
                ltCur = node
            } else {
                gtCur?.next = node
                gtCur = node
            }
        }
        ltCur?.next = gtDummyHead.next
        gtDummyHead.next = null
        val res = ltDummyHead.next
        ltDummyHead.next = null
        return res
    }
}
// endregion

// region 矩阵
class Solution0289 {
    fun gameOfLife(board: Array<IntArray>): Unit {
        val m = board.size
        val n = board[0].size
        val aux = Array(m) { IntArray(n) }
        for (i in 0..<m) {
            for (j in 0..<n) {
                aux[i][j] = board[i][j]
            }
        }

        val directions = arrayOf(
            intArrayOf(-1, 0),//up
            intArrayOf(-1, 1),//up-right
            intArrayOf(0, 1),//right
            intArrayOf(1, 1),//right-down
            intArrayOf(1, 0),//down
            intArrayOf(1, -1),//down-left
            intArrayOf(0, -1),//left
            intArrayOf(-1, -1),//left-up
        )

        for (i in 0..<m) {
            for (j in 0..<n) {
                var deadCnt = 0
                var aliveCnt = 0
                for (d in directions) {
                    val newI = i + d[0]
                    val newJ = j + d[1]
                    if (newI in 0..<m && newJ in 0..<n) {
                        if (aux[newI][newJ] == 0) {
                            ++deadCnt
                        } else {
                            ++aliveCnt
                        }
                    }
                }
                if (aux[i][j] == 1) {
                    if (aliveCnt < 2 || aliveCnt > 3) {
                        board[i][j] = 0
                    }
                } else {
                    if (aliveCnt == 3) {
                        board[i][j] = 1
                    }
                }
            }
        }
    }
}

// 36. 有效的数独
class Solution0036 {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (num in 1..9) {
            val row = BooleanArray(9)
            val col = BooleanArray(9)
            val grid = BooleanArray(9)
            for (i in 0..<9) {
                for (j in 0..<9) {
                    if (board[i][j] - '0' == num) {
                        if (row[i]) {
                            return false
                        } else {
                            row[i] = true
                        }

                        if (col[j]) {
                            return false
                        } else {
                            col[j] = true
                        }

                        val k = (i / 3) * 3 + (j / 3)
                        if (grid[k]) {
                            return false
                        } else {
                            grid[k] = true
                        }
                    }
                }
            }
        }
        return true
    }
}
// endregion

// region 哈希表
class Solution0383 {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val a = IntArray(26)
        for (c in ransomNote) {
            ++a[c - 'a']
        }
        val b = IntArray(26)
        for (c in magazine) {
            ++b[c - 'a']
        }
        for (i in 0..<26) {
            if (a[i] > b[i]) {
                return false
            }
        }
        return true
    }
}

class Solution0205 {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val s2t = hashMapOf<Char, Char>()
        val t2s = hashMapOf<Char, Char>()
        for (i in 0..<s.length) {
            val a = s[i]
            val b = t[i]
            if (s2t[a] == null && t2s[b] == null) {
                s2t[a] = b
                t2s[b] = a
            } else if (s2t[a] != b || t2s[b] != a) {
                return false
            }
        }
        return true
    }
}

class Solution0290 {
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")
        if (pattern.length != words.size) return false
        val p2w = hashMapOf<Char, String>()
        val w2p = hashMapOf<String, Char>()
        for (i in 0..<pattern.length) {
            val p = pattern[i]
            val w = words[i]
            if (p2w[p] == null && w2p[w] == null) {
                p2w[p] = w
                w2p[w] = p
            } else if (p2w[p] != w || w2p[w] != p) {
                return false
            }
        }
        return true
    }
}

// 242. 有效的字母异位词
class Solution0242 {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val freq = IntArray(26)
        for (i in s.indices) {
            ++freq[s[i] - 'a']
            --freq[t[i] - 'a']
        }
        for (item in freq) {
            if (item != 0) return false
        }
        return true
    }
}
// endregion

// region 栈
class Solution0071 {
    fun simplifyPath(path: String): String {
        val list = path.split("/+".toRegex())
        val stack = Stack<String>()
        for (str in list) {
            if (str == "..") {
                if (stack.isNotEmpty()) {
                    stack.pop()
                }
            } else if (str == "...") {
                stack.push(str)
            } else if (str != "." && str != "") {
                stack.push(str)
            }
        }
        val res = "/" + stack.joinToString("/")
        return res
    }
}

class Solution0071V2 {
    fun simplifyPath(path: String): String {
        val n = path.length
        var i = 0
        val stack = Stack<String>()
        while (i < n) {
            val c = path[i]
            if (c == '/') {
                while (i < n && path[i] == '/') {
                    ++i
                }
            } else {
                var str = ""
                while (i < n && path[i] != '/') {
                    str += path[i]
                    ++i
                }
                if (str == "..") {
                    if (stack.isNotEmpty()) stack.pop()
                } else if (str != "" && str != ".") {
                    stack.push(str)
                }
            }
        }
        return "/" + stack.joinToString("/")
    }
}

class Solution0150 {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = Stack<Int>()
        for (t in tokens) {
            if (t != "+" && t != "-" && t != "*" && t != "/") {
                stack.push(Integer.parseInt(t))
            } else {
                val b = stack.pop()
                val a = stack.pop()
                when (t) {
                    "+" -> stack.push(a + b)
                    "-" -> stack.push(a - b)
                    "*" -> stack.push(a * b)
                    "/" -> stack.push(a / b)
                }
            }
        }
        return stack.pop()
    }
}

class Solution0224 {
    fun calculate(s: String): Int {
        val n = s.length
        var i = 0
        val ops = Stack<Char>()
        var res = 0
        while (i < n) {
            val c = s[i]
            if (c == ' ') {
                while (i < n && s[i] == ' ') {
                    ++i
                }
            } else if (c == '(') {
                var count = 1
                val start = i + 1
                var end = start
                while (count > 0) {
                    if (s[end] == '(') {
                        ++count
                    } else if (s[end] == ')') {
                        --count
                    }
                    ++end
                }
                val sub = s.substring(start, end - 1)
                var num = calculate(sub)
                val op = if (ops.isNotEmpty()) ops.pop() else '+'
                if (op == '-') {
                    num = -num
                }
                res += num
                i = end
            } else if (c == '+' || c == '-') {
                ops.push(c)
                ++i
            } else if (c.isDigit()) {
                var num = 0
                while (i < n && s[i].isDigit()) {
                    num = num * 10 + (s[i] - '0')
                    ++i
                }
                val op = if (ops.isNotEmpty()) ops.pop() else '+'
                if (op == '-') {
                    num = -num
                }
                res += num
            }
        }
        return res
    }
}
// endregion

// region 堆
// 502. IPO
class Solution0502 {
    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val n = profits.size
        val index = (0..<n).sortedWith { aIndex, bIndex -> capital[aIndex] - capital[bIndex] }
        val queue = PriorityQueue<Int> { aProfit, bProfit -> bProfit - aProfit }
        var i = 0
        var curW = w
        for (j in 0..<k) {
            while (i < n && curW >= capital[index[i]]) {
                queue.offer(profits[index[i]])
                ++i
            }
            if (queue.isEmpty()) break
            curW += queue.poll()
        }
        return curW
    }
}
// endregion

// region 二叉树
data class TreeNode(val `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

// 100. 相同二叉树
class Solution0100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p.`val` != q.`val`) return false
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
}

// 106. 从中序和后序遍历序列构造二叉树
class Solution0106 {
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        return buildTree(inorder, 0, inorder.size - 1, postorder, 0, postorder.size - 1)
    }

    private fun buildTree(
        inorder: IntArray, inL: Int, inR: Int, postorder: IntArray, postL: Int, postR: Int
    ): TreeNode? {
        if (inL > inR) {
            return null
        }
        val rootVal = postorder[postR]
        var index = inL
        while (index <= inR) {
            if (inorder[index] == rootVal) {
                break
            }
            ++index
        }
        val leftCount = index - inL
        val rightCount = inR - index

        // r-l+1=count => r = l + count - 1
        val leftChild = buildTree(inorder, inL, inL + leftCount - 1, postorder, postL, postL + leftCount - 1)
        // l = r - count + 1
        val rightChild =
            buildTree(inorder, index + 1, index + 1 + rightCount - 1, postorder, postR - 1 - rightCount + 1, postR - 1)
        return TreeNode(rootVal).apply {
            left = leftChild
            right = rightChild
        }
    }
}

//
class Solution0117 {
    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }

    fun connect(root: Node?): Node? {
        if (root == null) return root
        val queue = LinkedList<Node>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val n = queue.size
            val list = mutableListOf<Node>()
            repeat(n) {
                val node = queue.poll()
                if (list.isNotEmpty()) {
                    list[list.size - 1].next = node
                }
                list.add(node)
                node.left?.let {
                    queue.offer(it)
                }
                node.right?.let {
                    queue.offer(it)
                }
            }
        }
        return root
    }
}

// 129.求根节点到叶节点数字之和
class Solution0129 {
    private var res = 0
    fun sumNumbers(root: TreeNode?): Int {
        res = 0
        dfs(root, 0)
        return res
    }

    private fun dfs(root: TreeNode?, curSum: Int) {
        if (root == null) return
        if (root.left == null && root.right == null) {
            res += curSum * 10 + root.`val`
            return
        }
        val nextSum = curSum * 10 + root.`val`
        dfs(root.left, nextSum)
        dfs(root.right, nextSum)
    }
}

// 173. 二叉搜索树迭代器
class BSTIteratorV1(root: TreeNode?) {

    private val list = mutableListOf<Int>()
    private var index = 0

    init {
        inorder(root)
    }

    private fun inorder(node: TreeNode?) {
        if (node == null) return
        inorder(node.left)
        list.add(node.`val`)
        inorder(node.right)
    }

    fun next(): Int {
        return list[index++]
    }

    fun hasNext(): Boolean {
        return index < list.size
    }
}

// endregion

// region 图
// 210. 课程表 II
class Solution0210 {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val inDegree = IntArray(numCourses)
        val edges = Array(numCourses) { arrayListOf<Int>() }
        for (p in prerequisites) {
            val a = p[0]
            val b = p[1]
            ++inDegree[a]
            edges[b].add(a)
        }
        val queue = LinkedList<Int>()
        for (i in 0..<numCourses) {
            if (inDegree[i] == 0) {
                queue.offer(i)
            }
        }
        val res = IntArray(numCourses)
        var i = 0
        while (queue.isNotEmpty()) {
            val course = queue.poll()
            res[i++] = course
            for (next in edges[course]) {
                if (--inDegree[next] == 0) {
                    queue.offer(next)
                }
            }
        }
        return if (i == numCourses) res else intArrayOf()
    }
}

// endregion

// region 图的广度优先搜索
// 433. 最小基因变化
class Solution0433 {
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        if (startGene == endGene) {
            return 0
        }
        val bankSet = bank.toSet()
        if (endGene !in bankSet) {
            return -1
        }
        val chars = listOf('A', 'C', 'T', 'G')
        val visited = hashSetOf<String>()
        visited.add(startGene)
        val queue = LinkedList<String>()
        queue.offer(startGene)
        var step = 1
        while (queue.isNotEmpty()) {
            val n = queue.size
            repeat(n) {
                val gene = queue.poll()
                for (i in gene.indices) {
                    for (c in chars) {
                        if (c != gene[i]) {
                            val nextGene = StringBuilder(gene).apply {
                                setCharAt(i, c)
                            }.toString()
                            if (nextGene in bankSet) {
                                if (nextGene == endGene) {
                                    return step
                                }
                                if (nextGene !in visited) {
                                    visited.add(nextGene)
                                    queue.offer(nextGene)
                                }
                            }
                        }
                    }
                }
            }
            ++step
        }
        return -1
    }
}

// 127. 单词接龙
class Solution0127 {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        if (beginWord == endWord) {
            return 0
        }
        if (beginWord.length != endWord.length) {
            return 0
        }
        val wordSet = wordList.toSet()
        if (endWord !in wordSet) {
            return 0
        }
        val visited = hashSetOf<String>()
        visited.add(beginWord)
        val queue = LinkedList<String>()
        queue.offer(beginWord)
        var step = 1
        while (queue.isNotEmpty()) {
            ++step
            val n = queue.size
            repeat(n) {
                val cur = queue.poll()
                for (i in cur.indices) {
                    for (c in 'a'..'z') {
                        if (c != cur[i]) {
                            val nextWord = StringBuilder(cur).apply { setCharAt(i, c) }.toString()
                            if (nextWord in wordSet) {
                                if (nextWord == endWord) {
                                    return step
                                }
                                if (nextWord !in visited) {
                                    visited.add(nextWord)
                                    queue.offer(nextWord)
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0
    }
}
// endregion

// region 二分查找
// 162. 寻找峰值
class Solution0162V1 {
    fun findPeakElement(nums: IntArray): Int {
        val n = nums.size
        var i = 0
        while (i < n) {
            val a = if (i - 1 >= 0) nums[i] > nums[i - 1] else true
            val b = if (i + 1 < n) nums[i] > nums[i + 1] else true
            if (a && b) return i
            ++i
        }
        return -1
    }
}

class Solution0162 {
    fun findPeakElement(nums: IntArray): Int {
        val n = nums.size
        var left = 0
        var right = n - 1
        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}

// endregion

// region 二叉树层序遍历
// 637. 二叉树的层平均值
class Solution0637 {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) {
            return doubleArrayOf()
        }
        val res = mutableListOf<Double>()
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val n = queue.size
            var sum: Double = 0.0
            repeat(n) {
                val node = queue.poll()
                sum += node.`val`
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
            res.add(sum / n)
        }
        return res.toDoubleArray()
    }
}

// endregion

// region kadane算法
// 918. 环形子数组的最大和
class Solution0918V1 {
    fun maxSubarraySumCircular(nums: IntArray): Int {
        val n = nums.size
        var res = Int.MIN_VALUE
        for (i in 0..<n) {
            // sum表示以nums[i]结尾的子数组的和
            var sum = nums[i]
            var curMax = sum
            var j = i - 1
            if (j < 0) j += n
            while (j != i) {
                sum += nums[j]
                curMax = maxOf(curMax, sum)

                if (--j < 0) {
                    j += n
                }
            }
            res = maxOf(res, curMax)
        }
        return res
    }
}

class Solution0918 {
    fun maxSubarraySumCircular(nums: IntArray): Int {
        val n = nums.size
        // leftMax[i]表示nums[0..i]范围内以nums[0]开始的子数组和的最大值
        val leftMax = IntArray(n)
        leftMax[0] = nums[0]
        var leftSum = nums[0]
        var prev = nums[0]
        var res = nums[0]
        for (i in 1..<n) {
            prev = maxOf(prev + nums[i], nums[i])
            res = maxOf(res, prev)
            leftSum += nums[i]
            leftMax[i] = maxOf(leftMax[i - 1], leftSum)
        }
        var rightSum = 0
        for (i in n - 1 downTo 1) {
            rightSum += nums[i]
            res = maxOf(res, rightSum + leftMax[i - 1])
        }
        return res
    }
}
// endregion

// region 多维动态规划
// 120. 三角形最小路径
class Solution0120 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        val dp = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        dp[0][0] = triangle[0][0]
        for (i in 1..<n) {
            val m = triangle[i].size
            for (j in 0..<m) {
                dp[i][j] = triangle[i][j] + minOf(
                    if (j < m - 1) dp[i - 1][j] else Int.MAX_VALUE,
                    if (j - 1 >= 0) dp[i - 1][j - 1] else Int.MAX_VALUE,
                )
            }
        }
        return dp.last().min()
    }
}

// 63. 不同路径II
class Solution0063 {
    fun uniquePathsWithObstacles(grid: Array<IntArray>): Int {
        if (grid[0][0] == 1) return 0
        val m = grid.size
        val n = grid[0].size
        if (grid[m - 1][n - 1] == 1) return 0
        val dp = Array(m) { IntArray(n) }
        dp[0][0] = 1
        // 第一行特殊处理
        for (j in 1..<n) {
            if (grid[0][j] == 0) {
                dp[0][j] = 1
            } else {
                break
            }
        }
        // 第一列特殊处理
        for (i in 1..<m) {
            if (grid[i][0] == 0) {
                dp[i][0] = 1
            } else {
                break
            }
        }
        for (i in 1..<m) {
            for (j in 1..<n) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }
        return dp[m - 1][n - 1]
    }
}

// 97. 交错字符串
class Solution0097 {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.isEmpty()) return s2 == s3
        if (s2.isEmpty()) return s1 == s3
        if (s1.length + s2.length != s3.length) return false
        val m = s1.length
        val n = s2.length
        // dp[i][j]表示s1[0..i)与s2[0..j)是否能交错组成s3[0..i+j)
        val dp = Array(m + 1) { BooleanArray(n + 1) }
        dp[0][0] = true
        // 第一行单独处理
        for (j in 1..n) {
            dp[0][j] = s2.substring(0, j) == s3.substring(0, j)
        }
        // 第一列单独处理
        for (i in 0..m) {
            dp[i][0] = s1.substring(0, i) == s3.substring(0, i)
        }
        for (i in 1..m) {
            for (j in 1..n) {
                dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) || (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
            }
        }
        return dp[m][n]
    }
}

// 221. 最大正方形
class Solution0221 {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        // dp[i][j]表示以matrix[i-1][j-1]为右下角的最大正方形的边长
        val dp = Array(m + 1) { IntArray(n + 1) }
        var maxSide = 0
        for (i in 1..m) {
            for (j in 1..n) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
                    maxSide = maxOf(maxSide, dp[i][j])
                }
            }
        }
        return maxSide * maxSide
    }
}
// endregion

// region 数学
// 9. 回文数
class Solution0009 {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false
        if (x % 10 == 0) return false
        var reverse = 0
        var num = x
        while (num > 0) {
            reverse = reverse * 10 + (num % 10)
            num /= 10
        }
        return x == reverse
    }
}
// endregion

// region 位运算
// 67. 二进制求和
class Solution0067 {
    fun addBinary(a: String, b: String): String {
        val m = a.length
        val n = b.length
        var i = m - 1
        var j = n - 1
        var carry = 0
        var res = ""
        while (i >= 0 || j >= 0 || carry > 0) {
            var sum = carry
            if (i >= 0) {
                sum += a[i] - '0'
                --i
            }
            if (j >= 0) {
                sum += b[j] - '0'
                --j
            }
            res = "${sum and 1}$res"
            carry = sum shr 1
        }
        return res
    }
}
// endregion

// region 字典树
// 211. 添加与搜索单词 - 数据结构设计
class WordDictionary {

    private data class Node(var isWord: Boolean = false) {
        val children = Array<Node?>(26) { null }
    }

    private val root = Node()

    fun addWord(word: String) {
        var cur = root
        for (c in word) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = Node()
            }
            cur = cur.children[c - 'a']!!
        }
        cur.isWord = true
    }

    fun search(word: String): Boolean {
        return searchInner(root, word, 0)
    }

    private fun searchInner(node: Node, word: String, curIndex: Int): Boolean {
        var cur = node
        for (i in curIndex..<word.length) {
            if (word[i] == '.') {
                for (child in cur.children) {
                    if (child != null && searchInner(child, word, i + 1)) {
                        return true
                    }
                }
                return false
            } else {
                if (cur.children[word[i] - 'a'] == null) {
                    return false
                }
                cur = cur.children[word[i] - 'a']!!
            }
        }
        return cur.isWord
    }
}

// 212. 单词搜索 II
class Solution0212V1 {

    private val directions = arrayOf(
        intArrayOf(-1, 0),//up
        intArrayOf(0, 1),//right
        intArrayOf(1, 0),//down
        intArrayOf(0, -1),//left
    )

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val res = mutableListOf<String>()
        val m = board.size
        val n = board[0].size
        for (word in words) {
            if (searchWord(board, m, n, word)) {
                res.add(word)
            }
        }
        return res
    }

    private fun searchWord(board: Array<CharArray>, m: Int, n: Int, word: String): Boolean {
        if (word.length > m * n) return false
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (dfs(board, m, n, i, j, word, 0, Array(m) { BooleanArray(n) })) {
                    return true
                }
            }
        }
        return false
    }

    private fun dfs(
        board: Array<CharArray>,
        m: Int,
        n: Int,
        curI: Int,
        curJ: Int,
        word: String,
        curIndex: Int,
        used: Array<BooleanArray>,
    ): Boolean {
        if (curIndex == word.length - 1) {
            return board[curI][curJ] == word[curIndex]
        }
        if (board[curI][curJ] != word[curIndex]) return false
        used[curI][curJ] = true
        for (d in directions) {
            val newI = curI + d[0]
            val newJ = curJ + d[1]
            if (newI in 0..<m && newJ in 0..<n && !used[newI][newJ]) {
                if (dfs(board, m, n, newI, newJ, word, curIndex + 1, used)) {
                    return true
                }
            }
        }
        used[curI][curJ] = false
        return false
    }
}

class Solution0212 {
    private val directions = arrayOf(
        intArrayOf(-1, 0),//up
        intArrayOf(0, 1),//right
        intArrayOf(1, 0),//down
        intArrayOf(0, -1),//left
    )

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val trie = Trie()
        for (word in words) {
            trie.addWord(word)
        }
        val m = board.size
        val n = board[0].size
        val res = mutableSetOf<String>()
        for (i in 0..<m) {
            for (j in 0..<n) {
                dfs(board, m, n, i, j, trie, res)
            }
        }
        return res.toList()
    }

    private fun dfs(
        board: Array<CharArray>, m: Int, n: Int, curI: Int, curJ: Int, curTrie: Trie, res: MutableSet<String>
    ) {
        if (curTrie.contains(board[curI][curJ]).not()) return

        val childTrie = curTrie.get(board[curI][curJ]) ?: return

        if (childTrie.word != "") {
            res.add(childTrie.word)
        }

        val c = board[curI][curJ]
        board[curI][curJ] = '#'
        for (d in directions) {
            val newI = curI + d[0]
            val newJ = curJ + d[1]
            if (newI in 0..<m && newJ in 0..<n && board[newI][newJ] != '#') {
                dfs(board, m, n, newI, newJ, childTrie, res)
            }
        }
        board[curI][curJ] = c
    }

    private class Trie {
        var word: String = ""
        var children: HashMap<Char, Trie> = hashMapOf()

        fun addWord(word: String) {
            var cur = this
            for (c in word) {
                if (c !in cur.children) {
                    cur.children[c] = Trie()
                }
                cur = cur.children[c]!!
            }
            cur.word = word
        }

        fun contains(c: Char): Boolean {
            return children[c] != null
        }

        fun get(c: Char): Trie? {
            return children[c]
        }
    }
}
// endregion

// region 分治
// 427. 建立四叉树
class Solution0427 {
    data class Node(val `val`: Boolean, val isLeaf: Boolean) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
    }

    fun construct(grid: Array<IntArray>): Node? {
        val n = grid.size
        return constructInner(grid, 0, n - 1, 0, n - 1)
    }

    private fun constructInner(grid: Array<IntArray>, iStart: Int, iEnd: Int, jStart: Int, jEnd: Int): Node? {
        if (iStart == iEnd && jStart == jEnd) {
            return Node(`val` = grid[iStart][jStart] == 1, isLeaf = true)
        }
        var zeroCnt = 0
        for (i in iStart..iEnd) {
            for (j in jStart..jEnd) {
                if (grid[i][j] == 0) {
                    ++zeroCnt
                }
            }
        }
        val total = (iEnd - iStart + 1) * (jEnd - jStart + 1)
        val isLeaf = zeroCnt == 0 || zeroCnt == total
        if (isLeaf) {
            return Node(`val` = zeroCnt == 0, isLeaf = true)
        }
        val iMid = iStart + (iEnd - iStart) / 2
        val jMid = jStart + (jEnd - jStart) / 2
        val node = Node(`val` = true, isLeaf = false)
        node.topLeft = constructInner(grid, iStart, iMid, jStart, jMid)
        node.topRight = constructInner(grid, iStart, iMid, jMid + 1, jEnd)
        node.bottomLeft = constructInner(grid, iMid + 1, iEnd, jStart, jMid)
        node.bottomRight = constructInner(grid, iMid + 1, iEnd, jMid + 1, jEnd)
        return node
    }
}
// endregion