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
class Solution0992 {
    fun subarraysWithKDistinct(A: IntArray, K: Int): Int {
        var res = 0
        for (i in A.indices) {
            val count = helper(A, i, K)
            res += count
            if (count == 0) break
        }
        return res
    }

    private fun helper(A: IntArray, start: Int, K: Int): Int {
        // A[start..A.size)区间内元素个数少于K个
        if (A.size - start < K) return 0
        var count = 0
        val freq = hashMapOf<Int, Int>()
        for (i in start until A.size) {
            freq[A[i]] = freq.getOrDefault(A[i], 0) + 1
            if (freq.size == K) {
                ++count
            } else if (freq.size > K || A.size - i < K - freq.size) {
                break
            }
        }
        return count
    }
}

fun main() {
    val s = Solution0992()
    println(s.subarraysWithKDistinct(intArrayOf(1, 2, 1, 2, 3), 2))
    println(s.subarraysWithKDistinct(intArrayOf(1, 2, 1, 3, 4), 3))
}
// 暴力解法时间超出限制