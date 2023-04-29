import java.lang.StringBuilder

// https://leetcode-cn.com/problems/multiply-strings/
// 43. Multiply Strings
/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Constraints:
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/multiply-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0043 {
    fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") return "0"
        var res = "0"
        var j = num2.lastIndex
        while (j >= 0) {
            val n2: Int = num2[j] - '0'
            var i = num1.lastIndex
            var carry = 0
            val sb = StringBuilder()
            repeat(num2.lastIndex - j) {
                sb.append(0)
            }
            while (i >= 0 || carry > 0) {
                val product = if (i >= 0) {
                    val n1: Int = num1[i--] - '0'
                    n1 * n2
                } else 0
                val sum = product + carry
                carry = sum / 10
                sb.append(sum % 10)
            }
            res = addStrings(res, sb.reverse().toString())
            --j
        }
        return res
    }

    private fun addStrings(s1: String, s2: String): String {
        val sb = StringBuilder()
        var carry = 0
        var i = s1.lastIndex
        var j = s2.lastIndex
        while (i >= 0 || j >= 0 || carry > 0) {
            var sum = carry
            if (i >= 0) sum += s1[i--] - '0'
            if (j >= 0) sum += s2[j--] - '0'
            carry = sum / 10
            sb.append(sum % 10)
        }
        return sb.reverse().toString()
    }
}

fun main() {
    val s = Solution0043()
    println(s.multiply("2", "3"))
    println(s.multiply("123", "456"))
}