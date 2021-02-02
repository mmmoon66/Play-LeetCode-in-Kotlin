// https://leetcode-cn.com/problems/longest-repeating-character-replacement/
// 424. Longest Repeating Character Replacement
/*
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:
Input:
s = "ABAB", k = 2
Output:
4
Explanation:
Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input:
s = "AABABBA", k = 1
Output:
4
Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0424_v2 {
    fun characterReplacement(s: String, k: Int): Int {
        val len = s.length
        var maxCount = 0
        var left = 0
        var right = 0
        val freq = IntArray(26)
        while(right < len) {
            ++freq[s[right] - 'A']
            maxCount = maxOf(maxCount, freq[s[right] - 'A'])
            if (right - left + 1 - maxCount > + k) {
                --freq[s[left] - 'A']
                ++left
            }
            ++right
        }
        return right - left
    }
}

fun main() {
    val s = Solution0424_v2()
    println(s.characterReplacement("ABAB", 2))
    println(s.characterReplacement("AABABBA", 1))
    println(s.characterReplacement("ABAA", 0))
    println(s.characterReplacement("ABBB", 2))
}
/*
执行用时：2340 ms, 在所有 Kotlin 提交中击败了100.00%的用户
内存消耗：34.7 MB, 在所有 Kotlin 提交中击败了100.00%的用户
 */