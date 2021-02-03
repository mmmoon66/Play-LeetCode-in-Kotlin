import java.lang.StringBuilder

// https://leetcode-cn.com/problems/reverse-bits/
// 190. Reverse Bits
/*
Reverse bits of a given 32 bits unsigned integer.

Note:
Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be
given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation
is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the
input represents the signed integer -3 and the output represents the signed integer -1073741825.

Follow up:
If this function is called many times, how would you optimize it?
 
Example 1:
Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return
964176192 which its binary representation is 00111001011110000010100101000000.

Example 2:
Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so
return 3221225471 which its binary representation is 10111111111111111111111111111111.
 
Constraints:
The input must be a binary string of length 32

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-bits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution0190 {
    fun reverseBits(n: Int): Int {
        val sb = StringBuilder()
        var num = n
        while (num != 0 || sb.length < 32) {
            sb.append(num % 2)
            num /= 2
        }
        val s = sb.toString()
        var res = 0
        for (i in s.indices) {
            res = res * 2 + (s[i] - '0')
        }
        return res
    }
}

fun main() {
    val s = Solution0190()
    val res = s.reverseBits(0b00000010100101000001111010011100)
    println(res == 964176192)
    println(res.toString(2))
}