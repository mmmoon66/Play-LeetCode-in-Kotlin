// https://leetcode.com/problems/sort-characters-by-frequency/
// 451. Sort Characters By Frequency
/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input:
"tree"
Output:
"eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input:
"cccaaa"
Output:
"cccaaa"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input:
"Aabb"
Output:
"bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */
class Solution0451 {
    fun frequencySort(s: String): String {
        val freq = mutableMapOf<Char, Int>()
        for (c in s) {
            freq[c] = freq.getOrDefault(c, 0) + 1
        }
        val bucket = Array<MutableList<Char>?>(s.length + 1) { null }
        for ((c, count) in freq) {
            if (bucket[count] == null) {
                bucket[count] = mutableListOf()
            }
            bucket[count]?.add(c)
        }
        val sb = StringBuilder()
        for (i in bucket.size - 1 downTo 0) {
            if (bucket[i] != null) {
                for (c in bucket[i]!!) {
                    for (j in 0 until i) {
                        sb.append(c)
                    }
                }
            }
        }
        return sb.toString()
    }
}

fun main() {
    val s = Solution0451()
    println(s.frequencySort("Tree"))
}
/*
Runtime: 212 ms, faster than 71.43% of Kotlin online submissions for Sort Characters By Frequency.
Memory Usage: 44.7 MB, less than 100.00% of Kotlin online submissions for Sort Characters By Frequency.
 */