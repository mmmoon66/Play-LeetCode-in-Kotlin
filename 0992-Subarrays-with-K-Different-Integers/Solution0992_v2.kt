// https://leetcode-cn.com/problems/subarrays-with-k-different-integers/
// 992. Subarrays with K Different Integers
/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.


Example 1:
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:
Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Note:
1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0992_v2 {
    fun subarraysWithKDistinct(A: IntArray, K: Int): Int {
        return atMostWithKDistinct(A, K) - atMostWithKDistinct(A, K - 1)
    }

    private fun atMostWithKDistinct(A: IntArray, K: Int): Int {
        val len = A.size
        var res = 0
        val freq = IntArray(len + 1)
        var left = 0
        var right = 0
        var count = 0
        while (right < len) {
            if (freq[A[right]] == 0) ++count
            ++freq[A[right]]
            if (count > K) {
                while (count > K) {
                    --freq[A[left]]
                    if (freq[A[left]] == 0) --count
                    ++left
                }
            }
            res += right - left + 1
            ++right
        }
        return res
    }
}

fun main() {
    val s = Solution0992_v2()
    println(s.subarraysWithKDistinct(intArrayOf(1, 2, 1, 2, 3), 2))
    println(s.subarraysWithKDistinct(intArrayOf(1, 2, 1, 3, 4), 3))
}
// 暴力解法时间超出限制