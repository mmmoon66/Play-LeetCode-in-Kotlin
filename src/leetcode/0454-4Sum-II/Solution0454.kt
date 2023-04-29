// https://leetcode.com/problems/4sum-ii/
// 454. 4Sum II
/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
class Solution0454 {
    fun fourSumCount(A: IntArray, B: IntArray, C: IntArray, D: IntArray): Int {
        val map1 = mutableMapOf<Int, Int>()
        for (a in A) {
            for (b in B) {
                val sum = a + b
                map1[sum] = map1.getOrDefault(sum, 0) + 1
            }
        }

        val map2 = mutableMapOf<Int, Int>()
        for (c in C) {
            for (d in D) {
                val sum = c + d
                map2[sum] = map2.getOrDefault(sum, 0) + 1
            }
        }

        var res = 0
        for ((sum, count) in map1) {
            res += count * map2.getOrDefault(-sum, 0)
        }
        return res
    }
}
/*
Runtime: 660 ms, faster than 25.00% of Kotlin online submissions for 4Sum II.
Memory Usage: 97.4 MB, less than 100.00% of Kotlin online submissions for 4Sum II.
 */