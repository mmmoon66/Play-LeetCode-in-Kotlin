import kotlin.math.abs
import kotlin.math.absoluteValue

// https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
// 1208. Get Equal Substrings Within Budget
/*
You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th
character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.

You are also given an integer maxCost.
Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with
a cost less than or equal to maxCost.

If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 

Example 1:
Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.

Example 2:
Input: s = "abcd", t = "cdef", maxCost = 3
Output: 1
Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.

Example 3:
Input: s = "abcd", t = "acde", maxCost = 0
Output: 1
Explanation: You can't make any change, so the maximum length is 1.
 

Constraints:
1 <= s.length, t.length <= 10^5
0 <= maxCost <= 10^6
s and t only contain lower case English letters.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution1208 {
    fun equalSubstring(s: String, t: String, maxCost: Int): Int {
        val diff = mutableListOf<Int>()
        for (i in s.indices) {
            diff.add(abs(s[i] - t[i]))
        }
        var l = 0
        var r = 0
        var sum = 0
        var res = 0
        while(r < s.length) {
            sum += diff[r]
            while(sum > maxCost) {
                sum -= diff[l]
                ++l
            }
            res = maxOf(res, r - l + 1)
            ++r
        }
        return res
    }

    fun equalSubstring2(s: String, t: String, maxCost: Int): Int {
        assert(s.length == t.length)
        val diff = mutableListOf<Int>()
        for (i in s.indices) {
            diff.add((s[i] - t[i]).absoluteValue)
        }
        var l = 0
        var r = 0
        var sum = diff[0]
        var res = 0
        while(true) {
            if (sum <= maxCost) {
                res = maxOf(res, r - l + 1)
                ++r
                if (r == s.length) break
                sum += diff[r]
            } else {
                sum -= diff[l]
                ++l
            }
        }
        return res
    }
}

fun main() {
    val s = Solution1208()
    println(s.equalSubstring("abcd", "bcdf", 3) == 3)
    println(s.equalSubstring("abcd", "cdef", 3) == 1)
    println(s.equalSubstring("abcd", "acde", 0) == 1)
}
/*
执行用时：208 ms, 在所有 Kotlin 提交中击败了100.00%的用户
内存消耗：35.3 MB, 在所有 Kotlin 提交中击败了50.00%的用户
 */