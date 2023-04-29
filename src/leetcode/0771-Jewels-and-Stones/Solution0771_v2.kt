// https://leetcode.com/problems/jewels-and-stones/
/*
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3

Example 2:
Input: J = "z", S = "ZZ"
Output: 0

Note:
S and J will consist of letters and have length at most 50.
The characters in J are distinct.
 */

class Solution0771_v2 {
    fun numJewelsInStones(J: String, S: String): Int {
        val set = J.toSet()
        return S.filter { set.contains(it) }.length
    }
}

fun main() {
    val s = Solution0771_v2()
    println(s.numJewelsInStones("aA", "aAAbbbb"))
    println(s.numJewelsInStones("z", "ZZ"))
}
/*
Runtime: 128 ms, faster than 99.11% of Kotlin online submissions for Jewels and Stones.
Memory Usage: 39 MB, less than 20.00% of Kotlin online submissions for Jewels and Stones.
 */