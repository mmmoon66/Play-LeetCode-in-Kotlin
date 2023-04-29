
// 1002. Find Common Characters
/*
Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings
within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you
need to include that character three times in the final answer.

You may return the answer in any order.

Example 1:
Input: ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:
Input: ["cool","lock","cook"]
Output: ["c","o"]

Note:
1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-common-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution1002_v3 {
    fun commonChars(words: Array<String>): List<String> {
        val freqs = Array<MutableMap<Char, Int>>(words.size) { mutableMapOf() }
        words.forEachIndexed { index, word ->
            word.forEachIndexed { _, c ->
                freqs[index][c] = freqs[index].getOrDefault(c, 0) + 1
            }
        }
        var freq = freqs[0]
        freqs.forEachIndexed { index, f ->
            if (index > 0) {
                freq = intersect(freq, f)
            }
        }
        val res = mutableListOf<String>()
        freq.forEach { c, count ->
            repeat(count) {
                res.add(c.toString())
            }
        }
        return res
    }

    private fun intersect(a: MutableMap<Char, Int>, b: MutableMap<Char, Int>): MutableMap<Char, Int> {
        val res = mutableMapOf<Char, Int>()
        a.forEach { c, count ->
            b[c]?.let {
                res[c] = minOf(it, count)
            }
        }
        return res
    }
}

fun main() {
    val s = Solution1002_v3()
    println(s.commonChars(arrayOf("bella", "label", "roller")))
    println(s.commonChars(arrayOf("cool", "lock", "cook")))
}
/*
执行用时：260 ms, 在所有 Kotlin 提交中击败了 50.00% 的用户
内存消耗：36.7 MB, 在所有 Kotlin 提交中击败了 50.00% 的用户
 */