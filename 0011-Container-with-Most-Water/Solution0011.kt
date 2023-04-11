class Solution0011 {
    fun maxArea(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var ret = 0
        while (l < r) {
            val area = (r - l) * minOf(height[l], height[r])
            ret = maxOf(ret, area)
            if (height[l] < height[r]) {
                ++l
            } else {
                --r
            }
        }
        return ret
    }
}

fun main() {
    val s = Solution0011()
    println(s.maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)) == 49)
    println(s.maxArea(intArrayOf(1, 1)) == 1)
}