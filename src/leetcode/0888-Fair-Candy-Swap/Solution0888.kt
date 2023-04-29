// https://leetcode-cn.com/problems/fair-candy-swap/
// 888. Fair Candy Swap
/*
Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.
 

Example 1:
Input: A = [1,1], B = [2,2]
Output: [1,2]

Example 2:
Input: A = [1,2], B = [2,3]
Output: [1,2]

Example 3:
Input: A = [2], B = [1,3]
Output: [2,3]

Example 4:
Input: A = [1,2,5], B = [2,4]
Output: [5,4]

Note:
1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
It is guaranteed that Alice and Bob have different total amounts of candy.
It is guaranteed there exists an answer.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fair-candy-swap
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0888 {
    fun fairCandySwap(A: IntArray, B: IntArray): IntArray {
        val aSum = A.sum()
        val bSum = B.sum()
        val diff = aSum - bSum
        val bSet = B.toSet()
        for (a in A) {
            if (a - diff / 2 in bSet) {
                return intArrayOf(a, a - diff / 2)
            }
        }
        return intArrayOf()
    }
}

fun main() {
    val s = Solution0888()
    println(s.fairCandySwap(intArrayOf(1, 1), intArrayOf(2, 2)).joinToString())
    println(s.fairCandySwap(intArrayOf(1, 2), intArrayOf(2, 3)).joinToString())
}
/*
用时：372 ms, 在所有 Kotlin 提交中击败了100.00%的用户
内存消耗：40.9 MB, 在所有 Kotlin 提交中击败了16.67%的用户
 */