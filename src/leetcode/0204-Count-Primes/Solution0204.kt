// https://leetcode.com/problems/count-primes/
// 204. Count Primes
/*
Count the number of prime numbers less than a non-negative number, n.

Example:
Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
class Solution0204 {
    fun countPrimes(n: Int): Int {
        val isPrime = BooleanArray(n) { true }
        var count = 0
        for (i in 2 until isPrime.size) {
            if (isPrime[i]) {
                ++count
                for (j in 2 * i until isPrime.size step i) {
                    isPrime[j] = false
                }
            }
        }
        return count
    }
}

fun main() {
    val s = Solution0204()
    println(s.countPrimes(10))
}
/*
Runtime: 152 ms, faster than 93.18% of Kotlin online submissions for Count Primes.
Memory Usage: 44.3 MB, less than 100.00% of Kotlin online submissions for Count Primes.
 */