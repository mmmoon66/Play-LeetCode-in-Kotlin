import java.util.*

// https://leetcode.com/problems/split-linked-list-in-parts/
// 725. Split Linked List in Parts
/*
Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.

Return a List of ListNode's representing the linked list parts that are formed.

Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]

Example 1:
Input:
root = [1, 2, 3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The input and each element of the output are ListNodes, not arrays.
For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but it's string representation as a ListNode is [].

Example 2:
Input:
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.

Note:
The length of root will be in the range [0, 1000].
Each value of a node in the input will be an integer in the range [0, 999].
k will be an integer in the range [1, 50].
 */
class Solution0725 {
    fun splitListToParts(root: ListNode?, k: Int): Array<ListNode?> {
        var totalCount = 0
        var cur = root
        while(cur != null) {
            ++totalCount
            cur = cur.next
        }
        val res = Array<ListNode?>(k) { null }
        if (totalCount == 0) {
            return res
        }
        val eachCount = totalCount / k
        val remainder = totalCount % k
        cur = root
        for (i in 0 until k) {
            var count = eachCount
            if (i < remainder) {
                ++count
            }
            val dummyHead = ListNode(0)
            var prev = dummyHead
            for (j in 0 until count) {
                val node = cur
                cur = cur!!.next
                node!!.next = null

                prev.next = node
                prev = prev.next!!
            }
            res[i] = dummyHead.next
        }
        return res
    }
}

fun main() {
    val s = Solution0725()
    println(Arrays.toString(s.splitListToParts(ListNode(intArrayOf(1, 2, 3)), 5)))
    println(Arrays.toString(s.splitListToParts(ListNode(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), 3)))
}
/*
Runtime: 108 ms, faster than 100.00% of Kotlin online submissions for Split Linked List in Parts.
Memory Usage: 39.1 MB, less than 100.00% of Kotlin online submissions for Split Linked List in Parts.
 */