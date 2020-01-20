// https://leetcode.com/problems/palindrome-number/
// 9. Palindrome Number
/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true

Example 2:
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:
Coud you solve it without converting the integer to a string?
 */
class Solution0009 {
    fun isPalindrome(x: Int): Boolean {
        var reverse = 0L
        var num = x
        while(num > 0) {
            reverse = reverse * 10 + num % 10
            if (reverse > Int.MAX_VALUE) {
                return false
            }
            num /= 10
        }
        return x == reverse.toInt()
    }
}

fun main() {
    val s = Solution0009()
    println(s.isPalindrome(121))
    println(s.isPalindrome(12321))
    println(s.isPalindrome(123321))
    println(s.isPalindrome(10))
    println(s.isPalindrome(21120))
}
/*
Runtime: 164 ms, faster than 99.07% of Kotlin online submissions for Palindrome Number.
Memory Usage: 40.8 MB, less than 11.11% of Kotlin online submissions for Palindrome Number.
 */