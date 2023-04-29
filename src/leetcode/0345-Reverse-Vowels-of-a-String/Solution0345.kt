// https://leetcode.com/problems/reverse-vowels-of-a-string/
// 345. Reverse Vowels of a String
/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Input: "hello"
Output: "holle"

Example 2:
Input: "leetcode"
Output: "leotcede"

Note:
The vowels does not include the letter "y".
 */
class Solution0345 {
    private fun isVowel(c: Char): Boolean {
        return c == 'A' || c == 'a'
                || c == 'E' || c == 'e'
                || c == 'I' || c == 'i'
                || c == 'O' || c == 'o'
                || c == 'U' || c == 'u'
    }

    fun reverseVowels(s: String): String {
        val n = s.length
        var i = 0
        var j = n - 1
        val arr = s.toCharArray()
        while(true) {
            while(i < j && !isVowel(arr[i])) ++i
            while(j > i && !isVowel(arr[j])) --j
            if (i >= j) break
            arr[i] = arr[j].also { arr[j] = arr[i] }
            ++i
            --j
        }
        return String(arr)
    }
}

fun main() {
    val s = Solution0345()
    println(s.reverseVowels("hello") == "holle")
    println(s.reverseVowels("leetcode") == "leotcede")
}
/*
Runtime: 168 ms, faster than 100.00% of Kotlin online submissions for Reverse Vowels of a String.
Memory Usage: 41.3 MB, less than 100.00% of Kotlin online submissions for Reverse Vowels of a String.
 */