import java.lang.StringBuilder

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
class Solution1002_v2 {
    fun commonChars(words: Array<String>): List<String> {
        var res = words[0]
        words.forEachIndexed { index, word ->
            if (index > 0) {
                res = intersect(res, word)
            }
        }
        return res.toCharArray().map { it.toString() }
    }

    // 将字符串排序后，使用双指针技术找到公共字符
    private fun intersect(a: String, b: String): String {
        val sortedArr1 = a.toCharArray().sortedArray()
        val sortedArr2 = b.toCharArray().sortedArray()
        val res = StringBuilder()
        var index1 = 0
        var index2 = 0
        while(index1 < sortedArr1.size && index2 < sortedArr2.size) {
            val c1 = sortedArr1[index1]
            val c2 = sortedArr2[index2]
            when {
                c1 == c2 -> {
                    res.append(sortedArr1[index1])
                    ++index1
                    ++index2
                }
                c1 < c2 -> {
                    ++index1
                }
                else -> {
                    ++index2
                }
            }
        }
        return res.toString()
    }
}

fun main() {
    val s = Solution1002_v2()
    println(s.commonChars(arrayOf("bella", "label", "roller")))
    println(s.commonChars(arrayOf("cool", "lock", "cook")))
}
/*
执行用时：260 ms, 在所有 Kotlin 提交中击败了 50.00% 的用户
内存消耗：36.7 MB, 在所有 Kotlin 提交中击败了 50.00% 的用户
 */