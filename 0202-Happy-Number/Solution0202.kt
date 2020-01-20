import org.omg.CORBA.NVList

// https://leetcode.com/problems/happy-number/
// 202. Happy Number
/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:
Input: 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
 */
class Solution0202 {
    fun isHappy(n: Int): Boolean {
        val record = HashSet<Int>()
        record.add(n)
        var num = n
        var sum = 0
        while(true) {
            while(num != 0) {
                val r = num % 10
                sum += r * r
                num /= 10
            }
            if (sum == 1) {
                return true
            }
            if (record.contains(sum)) {
                return false
            }
            record.add(sum)
            num = sum
            sum = 0
        }
    }
}

fun main() {
    val s = Solution0202()
    println(s.isHappy(2))
}
/*
Runtime: 100 ms, faster than 100.00% of Kotlin online submissions for Happy Number.
Memory Usage: 36.8 MB, less than 100.00% of Kotlin online submissions for Happy Number.
 */