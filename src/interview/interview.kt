package interview

// 小米面试题
// 从字符串str中删除所有子字符串p, 不能使用String的contains/replace方法
// case 1: str="aa816816aa", p="816", 返回"aaaa"
// case 2: str="816816", p="816", 返回""
// case 3: str="881681616", p="816", 返回""
class Solution_XiaoMi {
    private fun List<Char>.endsWith(p: String): Boolean {
        if (this.size < p.length) return false
        var i = this.size - 1
        var j = p.length - 1
        while (j >= 0) {
            if (this[i] != p[j]) return false
            --i
            --j
        }
        return true
    }

    private fun MutableList<Char>.removeTailingChars(n: Int) {
        var i = 0
        while (i++ < n && isNotEmpty()) {
            removeLast()
        }
    }

    fun remove(str: String, p: String): String {
        val sLen = str.length
        val pLen = p.length
        if (pLen > sLen) {
            return str
        }
        val chars = mutableListOf<Char>()
        for (i in 0..<sLen) {
            chars.add(str[i])
            if (chars.endsWith(p)) {
                chars.removeTailingChars(pLen)
            }
        }
        val sb = StringBuilder()
        for (c in chars) {
            sb.append(c)
        }
        return sb.toString()
    }
}