import java.util.*

// https://leetcode.com/problems/flipping-an-image/
// 832. Flipping an Image
/*
Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].

To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].

Example 1:
Input: [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]

Example 2:
Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

Notes:
1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1
 */

class Solution0832 {
    fun flipAndInvertImage(A: Array<IntArray>): Array<IntArray> {
        A.forEach { row ->
            var i = 0
            var j = row.size - 1
            while(i <= j) {
                row[i] = 1 - row[j].also { row[j] = 1 - row[i]}
                ++i
                --j
            }
        }
        return A
    }
}

fun main() {
    val s = Solution0832()
    val res =
        s.flipAndInvertImage(arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 0, 1), intArrayOf(0, 0, 0)))
    res.forEach { println(Arrays.toString(it)) }
}
/*
Runtime: 184 ms, faster than 100.00% of Kotlin online submissions for Flipping an Image.
Memory Usage: 45.2 MB, less than 100.00% of Kotlin online submissions for Flipping an Image.
 */