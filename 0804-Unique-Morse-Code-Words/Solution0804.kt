// https://leetcode.com/problems/unique-morse-code-words/
// 804. Unique Morse Code Words
/*
International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.

For convenience, the full table for the 26 letters of the English alphabet is given below:

[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example, "cba" can be written as "-.-..--...", (which is the concatenation "-.-." + "-..." + ".-"). We'll call such a concatenation, the transformation of a word.

Return the number of different transformations among all words we have.

Example:
Input: words = ["gin", "zen", "gig", "msg"]
Output: 2
Explanation:
The transformation of each word is:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."
There are 2 different transformations, "--...-." and "--...--.".

Note:
The length of words will be at most 100.
Each words[i] will have length in range [1, 12].
words[i] will only consist of lowercase letters.
 */
class Solution0804 {
    private val morseCodes = arrayOf(
        ".-",
        "-...",
        "-.-.",
        "-..",
        ".",
        "..-.",
        "--.",
        "....",
        "..",
        ".---",
        "-.-",
        ".-..",
        "--",
        "-.",
        "---",
        ".--.",
        "--.-",
        ".-.",
        "...",
        "-",
        "..-",
        "...-",
        ".--",
        "-..-",
        "-.--",
        "--.."
    )

    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val set = HashSet<String>()
        words.forEach { word ->
            val sb = StringBuffer()
            word.forEach { c ->
                sb.append(morseCodes[c - 'a'])
            }
            set.add(sb.toString())
        }
        return set.size
    }
}

fun main() {
    val s = Solution0804()
    println(s.uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg")))
}
/*
Runtime: 96 ms, faster than 100.00% of Kotlin online submissions for Unique Morse Code Words.
Memory Usage: 37.6 MB, less than 100.00% of Kotlin online submissions for Unique Morse Code Words.
 */