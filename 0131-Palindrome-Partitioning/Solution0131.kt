// https://leetcode-cn.com/problems/palindrome-partitioning/
// 131. Palindrome Partitioning
/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.
 

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]
 

Constraints:
1 <= s.length <= 16
s contains only lowercase English letters.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-partitioning
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0131 {
    private val ans = mutableListOf<List<String>>()

    fun partition(s: String): List<List<String>> {
        ans.clear()
        partition(s, 0, mutableListOf())
        return ans
    }

    private fun partition(s: String, start: Int, solution: MutableList<String>) {
        if (start == s.length) {
            ans.add(solution.toList())
            return
        }
        var end = start
        while (end < s.length) {
            if (isPalindrome(s, start, end)) {
                solution.add(s.substring(start, end + 1))
                partition(s, end + 1, solution)
                solution.removeAt(solution.size - 1)
            }
            ++end
        }
    }

    private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var i = start
        var j = end
        while (i <= j) {
            if (s[i] == s[j]) {
                ++i
                --j
            } else {
                return false
            }
        }
        return true
    }
}

fun main() {
    val s = Solution0131()
    println(s.partition("aab"))
}
/*
执行用时：756 ms, 在所有 Kotlin 提交中击败了82.35%的用户
内存消耗：60.4 MB, 在所有 Kotlin 提交中击败了76.47%的用户
 */