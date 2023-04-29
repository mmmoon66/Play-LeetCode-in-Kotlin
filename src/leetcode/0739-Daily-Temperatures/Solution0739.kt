// https://leetcode.com/problems/daily-temperatures/
// 739. Daily Temperatures
/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
class Solution0739 {
    fun dailyTemperatures(T: IntArray): IntArray {
        val res = IntArray(T.size)
        for (i in 0 until T.size) {
            for (j in i+1 until T.size) {
                if (T[j] > T[i]) {
                    res[i] = j - i
                    break
                }
            }
        }
        return res
    }
}

fun main() {
    val s = Solution0739()
    println(s.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)) contentEquals intArrayOf(1, 1, 4, 2, 1, 1, 0, 0))
}
/*
Runtime: 668 ms, faster than 46.15% of Kotlin online submissions for Daily Temperatures.
Memory Usage: 49.6 MB, less than 100.00% of Kotlin online submissions for Daily Temperatures.
 */