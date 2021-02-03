import java.lang.StringBuilder

// https://leetcode-cn.com/problems/excel-sheet-column-title/
// 168. Excel Sheet Column Title
/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...

Example 1:
Input: 1
Output: "A"

Example 2:
Input: 28
Output: "AB"

Example 3:
Input: 701
Output: "ZY"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/excel-sheet-column-title
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0168 {
    fun convertToTitle(n: Int): String {
        var num = n
        val sb = StringBuilder()
        while (num > 0) {
            var r = num % 26
            if (r == 0) {
                r = 26
                --num
            }
            sb.append('A' + r - 1)
            num /= 26
        }
        return sb.reversed().toString()
    }

    fun convertToTitle2(n: Int): String {
        var num = n
        val sb = StringBuilder()
        while (num > 0) {
            val r = num % 26
            if (r == 0) {
                sb.append('Z')
                num = num / 26 - 1
            } else {
                sb.append('A' + (r - 1))
                num /= 26
            }
        }
        return sb.reversed().toString()
    }
}

fun main() {
    val s = Solution0168()
    // 28 == 1 * 26^1 + 2 * 26^0
    println(s.convertToTitle(28) == "AB")
    // 27 = 1 * 26^1 + 1 * 26^0
    println(s.convertToTitle(27) == "AA")
    // 26 = 26 * 26^0
    println(s.convertToTitle(26) == "Z")
    // 1 = 1 * 26^0
    println(s.convertToTitle(1) == "A")
    // 701 = 26 & 26^1 + 25 * 26^0
    println(s.convertToTitle(701) == "ZY")
}