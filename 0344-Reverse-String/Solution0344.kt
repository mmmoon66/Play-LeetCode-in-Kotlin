import java.util.*

// https://leetcode.com/problems/reverse-string/
// 344. Reverse String
/*
Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

Example 1:
Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

Example 2:
Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 */
class Solution0344 {
    fun reverseString(s: CharArray): Unit {
        val n = s.size
        var i = 0;
        var j = n - 1
        while(i < j) {
            s[i] = s[j].also { s[j] = s[i] }
            ++i
            --j
        }
    }
}

fun main() {
    val s = Solution0344()
    val arr = charArrayOf('h', 'e', 'l', 'l', 'o')
    s.reverseString(arr)
    println(Arrays.toString(arr))
}
/*
Runtime: 208 ms, faster than 100.00% of Kotlin online submissions for Reverse String.
Memory Usage: 48.7 MB, less than 25.00% of Kotlin online submissions for Reverse String.
 */