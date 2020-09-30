package lcof

import ListNode
import java.util.*

// 剑指 Offer 06. 从尾到头打印链表
/*
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：
输入：head = [1,3,2]
输出：[2,3,1]

限制：
0 <= 链表长度 <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution006_v3 {
    fun reversePrint(head: ListNode?): IntArray {
        var newHead: ListNode? = null
        var h = head
        var size = 0
        while(h != null) {
            val node = h
            h = h.next
            node.next = null

            node.next = newHead
            newHead = node
            ++size
        }
        val res = IntArray(size)
        var index = 0
        while(newHead != null) {
            res[index++] = newHead.`val`
            newHead = newHead.next
        }
        return res
    }
}

fun main() {
    val head = ListNode(intArrayOf(1,3,2))
    val s = Solution006_v3()
    println(s.reversePrint(head).contentToString())
}