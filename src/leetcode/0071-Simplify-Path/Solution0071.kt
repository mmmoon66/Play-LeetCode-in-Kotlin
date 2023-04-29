// https://leetcode-cn.com/problems/simplify-path/
// 71. Simplify Path
/*
Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file
system, convert it to the simplified canonical path.

In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory
up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any
other format of periods such as '...' are treated as file/directory names.

The canonical path should have the following format:

The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.
 

Example 1:
Input: path = "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
Input: path = "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
Input: path = "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Example 4:
Input: path = "/a/./b/../../c/"
Output: "/c"
 

Constraints:
1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/simplify-path
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0071 {
    fun simplifyPath(path: String): String {
        val res = mutableListOf<String>()
        path.split("/").forEach {
            if (it == "..") {
                repeat(2) {
                    if (res.isNotEmpty()) res.removeAt(res.lastIndex)
                }
            } else if (it != "." && it.isNotEmpty()) {
                res.add("/")
                res.add(it)
            }
        }
        if (res.isEmpty()) res.add("/")
        return res.joinToString("")
    }
}

fun main() {
    val s = Solution0071()
    println(s.simplifyPath("/home/"))
    println(s.simplifyPath("/../"))
    println(s.simplifyPath("/home//foo/"))
    println(s.simplifyPath("/a/./b/../../c/"))
}
/*
执行用时：240 ms, 在所有 Kotlin 提交中击败了58.62%的用户
内存消耗：35.9 MB, 在所有 Kotlin 提交中击败了17.24%的用户
 */