import java.lang.StringBuilder

// https://leetcode-cn.com/problems/add-strings/
// 415. Add Strings
/*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0415 {
    fun addStrings(num1: String, num2: String): String {
        if (num1.isEmpty()) return num2
        if (num2.isEmpty()) return num1
        val sb = StringBuilder()
        var i = num1.lastIndex
        var j = num2.lastIndex
        var carry = 0
        while (i >= 0 || j >= 0 || carry > 0) {
            var sum = 0
            if (i >= 0) sum += num1[i--] - '0'
            if (j >= 0) sum += num2[j--] - '0'
            sum += carry
            sb.append(sum % 10)
            carry = sum / 10
        }
        return sb.reversed().toString()
    }
}

fun main() {
    val s = Solution0415()
    println(s.addStrings("789", "345") == (789 + 345).toString())
}