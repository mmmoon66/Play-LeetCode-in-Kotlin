import java.lang.StringBuilder

// https://leetcode.com/problems/zigzag-conversion/
// 6. ZigZag Conversion
/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
 */
class Solution0006 {
    private val down = intArrayOf(1, 0)
    private val rightUp = intArrayOf(-1, 1)
    fun convert(s: String, numRows: Int): String {
        if (numRows <= 0) return ""
        if (numRows == 1) return s
        if (s.isEmpty()) return s
        val arr = Array(numRows) { CharArray(s.length) }
        var d = down
        var curX = 0
        var curY = 0
        for (c in s) {
            arr[curX][curY] = c

            val newX = curX + d[0]
            if (newX < 0) {
                d = down
            } else if (newX >= numRows) {
                d = rightUp
            }
            curX += d[0]
            curY += d[1]
        }
        val res = StringBuilder()
        arr.forEach { row ->
            row.forEach { c ->
                if (c != 0.toChar()) {
                    res.append(c)
                }
            }
        }
        return res.toString()
    }
}

fun main() {
    val s = Solution0006()
    println(s.convert("PAYPALISHIRING", 3))
}
/*
Runtime: 488 ms, faster than 18.26% of Kotlin online submissions for ZigZag Conversion.
Memory Usage: 67.5 MB, less than 10.53% of Kotlin online submissions for ZigZag Conversion.
 */