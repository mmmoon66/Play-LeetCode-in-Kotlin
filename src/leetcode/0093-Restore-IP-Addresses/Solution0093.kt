// https://leetcode-cn.com/problems/restore-ip-addresses/
// 93. Restore IP Addresses
/*
Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.

A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses. 


Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:
Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:
Input: s = "1111"
Output: ["1.1.1.1"]

Example 4:
Input: s = "010010"
Output: ["0.10.0.10","0.100.1.0"]

Example 5:
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

Constraints:
0 <= s.length <= 3000
s consists of digits only.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/restore-ip-addresses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0093 {
    fun restoreIpAddresses(s: String): List<String> {
        return helper(s, 0, 4)
            .map { it.reversed().joinToString(".") }
    }

    private fun helper(s: String, start: Int, n: Int): List<List<String>> {
        val res = mutableListOf<List<String>>()
        if (start == s.length && n == 0) {
            res.add(emptyList())
            return res
        }
        if (start >= s.length) return res
        if ((s.length - start) !in n..(3 * n)) return res
        for (len in 1..3) {
            val end = start + len - 1
            if (end < s.length && isValid(s, start, end)) {
                helper(s, end + 1, n - 1).forEach {
                    res.add(it + s.substring(start, start + len))
                }
            }
        }
        return res
    }

    private fun isValid(s: String, start: Int, end: Int): Boolean {
        val len = end - start + 1
        if (len == 1) {
            return true
        } else if (len == 2) {
            return s[start] != '0'
        } else {
            var num = 0
            for(i in start..end) {
                num = 10 * num + (s[i] - '0')
            }
            return num in 100..255
        }
    }
}

fun main() {
    val s = Solution0093()
    println(s.restoreIpAddresses("25525511135"))
    println(s.restoreIpAddresses("0000"))
    println(s.restoreIpAddresses("010010"))
    println(s.restoreIpAddresses("101023"))
}
/*
执行用时：220 ms, 在所有 Kotlin 提交中击败了81.82%的用户
内存消耗：35.5 MB, 在所有 Kotlin 提交中击败了18.18%的用户
 */
