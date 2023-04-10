class Solution0017 {
    private val lettersMap = arrayOf(
        " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        val ret = mutableListOf<String>()
        if (digits.isEmpty()) {
            return ret
        }
        findCombination(digits, 0, mutableListOf(), ret)
        return ret
    }

    private fun findCombination(digits: String, index: Int, path: MutableList<Char>, ret: MutableList<String>) {
        if (index == digits.length) {
            ret.add(path.joinToString(""))
            return
        }
        for (c in lettersMap[digits[index] - '0']) {
            path.add(c)
            findCombination(digits, index + 1, path, ret)
            path.removeAt(path.lastIndex)
        }
    }
}

fun main() {
    val s = Solution0017()
    val ret = s.letterCombinations("23")
    println(ret)
}