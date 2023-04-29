// https://leetcode.com/problems/excel-sheet-column-number/
// 171. Excel Sheet Column Number
/*
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...

Example 1:
Input: "A"
Output: 1

Example 2:
Input: "AB"
Output: 28

Example 3:
Input: "ZY"
Output: 701
 */
class Solution0171 {
    fun titleToNumber(s: String): Int {
        var res = 0
        s.forEach { c ->
            res = res * 26 + (c - 'A' + 1)
        }
        return res
    }
}

fun main() {
    val s = Solution0171()
    println(s.titleToNumber("A"))
    println(s.titleToNumber("AB"))
    println(s.titleToNumber("ZY"))
}
/*
Runtime: 128 ms, faster than 96.00% of Kotlin online submissions for Excel Sheet Column Number.
Memory Usage: 38.4 MB, less than 100.00% of Kotlin online submissions for Excel Sheet Column Number.
 */