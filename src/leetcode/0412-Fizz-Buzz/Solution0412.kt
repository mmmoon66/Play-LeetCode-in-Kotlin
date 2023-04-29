// https://leetcode.com/problems/fizz-buzz/
// 412. Fizz Buzz
/*
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:
n = 15,
Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */
class Solution0412 {
    fun fizzBuzz(n: Int): List<String> {
        val res = arrayListOf<String>()
        for (i in 1..n) {
            if (i % 15 == 0) {
                res.add("FizzBuzz")
            } else if (i % 5 == 0) {
                res.add("Buzz")
            } else if (i % 3 == 0) {
                res.add("Fizz")
            } else {
                res.add(i.toString())
            }
        }
        return res
    }
}
/*
Runtime: 152 ms, faster than 100.00% of Kotlin online submissions for Fizz Buzz.
Memory Usage: 43.2 MB, less than 100.00% of Kotlin online submissions for Fizz Buzz.
 */