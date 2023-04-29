// https://leetcode.com/problems/find-and-replace-pattern/
// 890. Find and Replace Pattern
class Solution0890 {
    private fun match(word: String, pattern: String): Boolean {
        if (word.length != pattern.length) return false
        val w2p = HashMap<Char, Char>()
        val p2w = HashMap<Char, Char>()
        for (i in 0 until word.length) {
            val w = word[i]
            val p = pattern[i]
            if (w2p[w] == null && p2w[p] == null) {
                w2p[w] = p
                p2w[p] = w
            } else if (w2p[w] != p || p2w[p] != w) {
                return false
            }
        }
        return true
    }

    fun findAndReplacePattern(words: Array<String>, pattern: String): List<String> {
        return words.filter { match(it, pattern) }
    }
}
/*
Runtime: 124 ms, faster than 100.00% of Kotlin online submissions for Find and Replace Pattern.
Memory Usage: 38.8 MB, less than 100.00% of Kotlin online submissions for Find and Replace Pattern.
 */