import java.lang.StringBuilder

// https://leetcode.com/problems/add-binary/
// 67. Add Binary
/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"

Constraints:
Each string consists only of '0' or '1' characters.
1 <= a.length, b.length <= 10^4
Each string is either "0" or doesn't contain any leading zero.
 */
class Solution0067 {
    fun addBinary(a: String, b: String): String {
        val res = StringBuilder()
        var i = a.length - 1
        var j = b.length - 1
        var carry = 0
        while (i >= 0 || j >= 0 || carry > 0) {
            var sum = carry
            if (i >= 0) {
                sum += a[i] - '0'
                --i
            }
            if (j >= 0) {
                sum += b[j] - '0'
                --j
            }
            res.append(sum % 2)
            carry = sum / 2
        }
        return res.reverse().toString()
    }
}

fun main() {
    val s = Solution0067()
    println(s.addBinary("11", "1"))
    println(s.addBinary("1010", "1011"))
}
/*
Runtime: 184 ms, faster than 82.76% of Kotlin online submissions for Add Binary.
Memory Usage: 34.7 MB, less than 46.31% of Kotlin online submissions for Add Binary.
 */