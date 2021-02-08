// https://leetcode-cn.com/problems/longest-turbulent-subarray/
// 978. Longest Turbulent Subarray
/*
Given an integer array arr, return the length of a maximum size turbulent subarray of arr.

A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.
 

Example 1:
Input: arr = [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]

Example 2:
Input: arr = [4,8,12,16]
Output: 2

Example 3:
Input: arr = [100]
Output: 1
 

Constraints:
1 <= arr.length <= 4 * 10^4
0 <= arr[i] <= 10^9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0978_v2 {
    fun maxTurbulenceSize(arr: IntArray): Int {
        if (arr.size < 2) return arr.size
        var maxSize = 1
        var l = 0
        var r = 0
        while (r + 1 < arr.size) {
            if (r == 0) {
                if (arr[r + 1] == arr[r]) {
                    ++r
                    l = r
                } else {
                    ++r
                }
            } else {
                if ((arr[r + 1] < arr[r] && arr[r] > arr[r - 1]) || (arr[r + 1] > arr[r] && arr[r] < arr[r - 1])) {
                    ++r
                } else if (arr[r + 1] == arr[r]) {
                    ++r
                    l = r
                } else {
                    l = r
                    ++r
                }
            }
            maxSize = maxOf(maxSize, r - l + 1)
        }
        return maxSize
    }
}

fun main() {
    val s = Solution0978_v2()
    println(s.maxTurbulenceSize(intArrayOf(9, 4, 2, 10, 7, 8, 8, 1, 9)))
    println(s.maxTurbulenceSize(intArrayOf(4, 8, 12, 16)))
    println(s.maxTurbulenceSize(intArrayOf(1)))
    println(s.maxTurbulenceSize(intArrayOf()))
    println(s.maxTurbulenceSize(intArrayOf(9, 9)))
}
/*
执行用时：380 ms, 在所有 Kotlin 提交中击败了83.33%的用户
内存消耗：45.2 MB, 在所有 Kotlin 提交中击败了66.67%的用户
 */