// https://leetcode.com/problems/implement-magic-dictionary/
// 676. Implement Magic Dictionary
/*
Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False

Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
class MagicDictionary() {

    /** Initialize your data structure here. */
    private class Node(var isWord: Boolean = false) {
        var next = mutableMapOf<Char, Node>()
    }
    private val root = Node()

    /** Build a dictionary through a list of words */
    fun buildDict(dict: Array<String>) {
        for (word in dict) {
            var cur = root
            for (c in word) {
                if (cur.next[c] == null) {
                    cur.next[c] = Node()
                }
                cur = cur.next[c]!!
            }
            cur.isWord = true
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    fun search(word: String): Boolean {
        var cur = root
        word.forEachIndexed { index, c ->
            for ((k, node) in cur.next) {
                if (k == c) {
                    cur = cur.next[c]!!
                } else if(contains(node, word, index + 1)) {
                    return true
                }
            }
        }
        return false
    }

    private fun contains(node: Node, word: String, index: Int): Boolean {
        var cur = node
        for (i in index until word.length) {
            val c = word[i]
            if (cur.next[c] == null) {
                return false
            }
            cur = cur.next[c]!!
        }
        return cur.isWord
    }

}

fun main() {
    val md = MagicDictionary()
    md.buildDict(arrayOf("hello", "leetcode", "hallo"))
    println(md.search("hello"))
    println(md.search("hhllo"))
    println(md.search("hell"))
    println(md.search("leetcoded"))
}
/*
Runtime: 124 ms, faster than 50.00% of Kotlin online submissions for Implement Magic Dictionary.
Memory Usage: 39.9 MB, less than 100.00% of Kotlin online submissions for Implement Magic Dictionary.
 */