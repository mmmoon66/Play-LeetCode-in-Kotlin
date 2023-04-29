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
class Solution0093_v2 {
    companion object {
        private const val SEG_COUNT = 4
    }
    private var ans = mutableListOf<String>()
    private var segments = intArrayOf()

    fun restoreIpAddresses(s: String): List<String> {
        ans = mutableListOf()
        segments = IntArray(SEG_COUNT)
        dfs(s, 0, 0)
        return ans
    }

    private fun dfs(s: String, segId: Int, segStart: Int) {
        if (segId == SEG_COUNT && segStart == s.length) {
            ans.add(segments.joinToString("."))
            return
        }
        if (segId == SEG_COUNT || segStart == s.length) return
        if (s[segStart] == '0') {
            segments[segId] = 0
            dfs(s, segId + 1, segStart + 1)
        } else {
            var num = 0
            for (i in segStart until s.length) {
                num = 10 * num + (s[i] - '0')
                if (num <= 255) {
                    segments[segId] = num
                    dfs(s, segId + 1, i + 1)
                } else {
                    break
                }
            }
        }
    }
}

fun main() {
    val s = Solution0093_v2()
    println(s.restoreIpAddresses("25525511135"))
    println(s.restoreIpAddresses("0000"))
    println(s.restoreIpAddresses("010010"))
    println(s.restoreIpAddresses("101023"))
}
/*
执行用时：220 ms, 在所有 Kotlin 提交中击败了100%的用户
内存消耗：35.5 MB, 在所有 Kotlin 提交中击败了18.18%的用户
 */
