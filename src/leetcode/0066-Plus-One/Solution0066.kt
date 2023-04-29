import java.util.*

// https://leetcode.com/problems/plus-one/
// 66. Plus One
/*
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Example 2:
Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
 */
class Solution0066 {
    fun plusOne(digits: IntArray): IntArray {
        var carry = 1
        for (index in digits.size - 1 downTo 0) {
            val num = digits[index]
            digits[index] = (num + carry) % 10
            carry = (num + carry) / 10
        }
        if (carry == 1) {
            return intArrayOf(1, *digits)
        } else {
            return digits
        }
    }
}

fun main() {
    val s = Solution0066()
    println(Arrays.toString(s.plusOne(intArrayOf(1, 2, 3))))
    println(Arrays.toString(s.plusOne(intArrayOf(9, 9, 9))))
}
/*
Runtime: 104 ms, faster than 100.00% of Kotlin online submissions for Plus One.
Memory Usage: 36.8 MB, less than 100.00% of Kotlin online submissions for Plus One.
 */