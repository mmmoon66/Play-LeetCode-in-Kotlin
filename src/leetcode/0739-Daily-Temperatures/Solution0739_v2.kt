import java.util.*

// https://leetcode.com/problems/daily-temperatures/
// 739. Daily Temperatures
/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
class Solution0739_v2 {
    fun dailyTemperatures(T: IntArray): IntArray {
        val res = IntArray(T.size).apply { fill(30000) }
        val bucket = Array(100 + 1) { mutableListOf<Int>() }
        T.forEachIndexed { index, t -> bucket[t].add(index) }
        T.forEachIndexed { index, t ->
            for (j in t + 1..100) {
                if (bucket[j].isNotEmpty()) {
                    if (index < bucket[j].first()) {
                        res[index] = minOf(res[index], bucket[j].first() - index)
                    } else if (index < bucket[j].last()) {
                        var l = 0
                        var r = bucket[j].size - 1
                        while (l < r - 1) {
                            val mid = l + (r - l) / 2
                            if (index < bucket[j][mid]) {
                                r = mid
                            } else {
                                l = mid
                            }
                        }
                        res[index] = minOf(res[index], bucket[j][r] - index)
                    }
                }
            }
        }
        res.forEachIndexed { index, i -> if (i == 30000) res[index] = 0 }
        return res
    }
}

fun main() {
    val s = Solution0739_v2()
    println(s.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)) contentEquals intArrayOf(1, 1, 4, 2, 1, 1, 0, 0))
//    println(Arrays.toString(s.dailyTemperatures(intArrayOf(34, 80, 80, 34, 34, 80, 80, 80, 80, 34))))
}
/*
Runtime: 584 ms, faster than 51.28% of Kotlin online submissions for Daily Temperatures.
Memory Usage: 52.4 MB, less than 100.00% of Kotlin online submissions for Daily Temperatures.
 */