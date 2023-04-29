import java.util.*

// https://leetcode.com/problems/sort-array-by-parity-ii/
// 922. Sort Array By Parity II
/*
Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.

Example 1:
Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

Note:
2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000
 */
class Solution0922 {
    fun sortArrayByParityII(A: IntArray): IntArray {
        var i = 0
        var j = 1
        val n = A.size
        while (true) {
            while (i < n && A[i] % 2 == 0) i += 2
            while(j < n && A[j] % 2 == 1) j += 2
            if (i >= n || j >= n) break
            A[i] = A[j].also { A[j] = A[i] }
        }
        return A
    }
}

fun main() {
    val s = Solution0922()
    s.sortArrayByParityII(intArrayOf(4, 2, 5, 7)).also { println(Arrays.toString(it)) }
}
/*
Runtime: 236 ms, faster than 100.00% of Kotlin online submissions for Sort Array By Parity II.
Memory Usage: 49.7 MB, less than 100.00% of Kotlin online submissions for Sort Array By Parity II.
 */