import java.util.*

// https://leetcode.com/problems/sort-array-by-parity/
// 905. Sort Array By Parity
/*
Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
You may return any answer array that satisfies this condition.

Example 1:
Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Note:
1 <= A.length <= 5000
0 <= A[i] <= 5000
 */
class Solution0905 {
    fun sortArrayByParity(A: IntArray): IntArray {
        var i = 0
        var j = A.size - 1
        while(true) {
            while(i < j && A[i] % 2 == 0) ++i
            while(j > i && A[j] % 2 == 1) --j
            if (i >= j) break
            A[i] = A[j].also { A[j] = A[i]}
        }
        return A
    }
}

fun main() {
    val s = Solution0905()
    s.sortArrayByParity(intArrayOf(3, 1, 2, 4)).also { println(Arrays.toString(it))}
}
/*
Runtime: 200 ms, faster than 100.00% of Kotlin online submissions for Sort Array By Parity.
Memory Usage: 46.9 MB, less than 100.00% of Kotlin online submissions for Sort Array By Parity.
 */