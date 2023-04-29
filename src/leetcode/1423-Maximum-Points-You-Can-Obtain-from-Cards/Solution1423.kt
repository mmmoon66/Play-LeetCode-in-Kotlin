// https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
// 1423. Maximum Points You Can Obtain from Cards
class Solution1423 {
    fun maxScore(cardPoints: IntArray, k: Int): Int {
        val sum = cardPoints.sum()
        val n = cardPoints.size
        val m = n - k
        var s = 0
        var i = 0
        while(i < m) {
            s += cardPoints[i]
            ++i
        }
        var min = s
        while(i < n) {
            // 维护一个长度为m的滑动窗口
            s = s + cardPoints[i] - cardPoints[i - m]
            min = minOf(min, s)
            ++i
        }
        return sum - min
    }
}

fun main() {
    val s = Solution1423()
    println(s.maxScore(intArrayOf(100, 40, 17, 9, 73, 75), 3))
    println(s.maxScore(intArrayOf(1, 2, 3, 4, 5, 6, 1), 3))
}