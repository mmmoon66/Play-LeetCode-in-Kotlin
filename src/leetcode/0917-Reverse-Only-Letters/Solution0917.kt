// https://leetcode.com/problems/reverse-only-letters/
// 917. Reverse Only Letters
/*
Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

Example 1:
Input: "ab-cd"
Output: "dc-ba"

Example 2:
Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:
Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"

Note:
S.length <= 100
33 <= S[i].ASCIIcode <= 122
S doesn't contain \ or "
 */
class Solution0917 {
    fun reverseOnlyLetters(S: String): String {
        val n = S.length
        var i = 0
        var j = n - 1
        val arr = S.toCharArray()
        while(true) {
            while(i < n && !arr[i].isLetter()) ++i
            while(j >= 0 && !arr[j].isLetter()) --j
            if (i >= j) break
            arr[i] = arr[j].also { arr[j] = arr[i] }
            ++i
            --j
        }
        return String(arr)
    }
}
/*
Runtime: 128 ms, faster than 88.24% of Kotlin online submissions for Reverse Only Letters.
Memory Usage: 36.5 MB, less than 100.00% of Kotlin online submissions for Reverse Only Letters.
 */