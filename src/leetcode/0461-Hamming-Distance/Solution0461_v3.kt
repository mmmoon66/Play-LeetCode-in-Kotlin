// https://leetcode.com/problems/hamming-distance/
// 461. Hamming Distance
/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 2^31.

Example:
Input: x = 1, y = 4
Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
 */

class Solution0461_v3 {
    fun hammingDistance(x: Int, y: Int): Int {
        return Integer.bitCount(x xor y)
    }
}

fun main() {
    val s = Solution0461_v3()
    println(s.hammingDistance(1, 4))
}
/*
Runtime: 80 ms, faster than 100.00% of Kotlin online submissions for Hamming Distance.
Memory Usage: 36.3 MB, less than 100.00% of Kotlin online submissions for Hamming Distance.
 */