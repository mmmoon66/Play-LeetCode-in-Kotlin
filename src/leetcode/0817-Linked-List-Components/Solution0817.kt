// https://leetcode.com/problems/linked-list-components/
// 817. Linked List Components
/*
We are given head, the head node of a linked list containing unique integer values.

We are also given the list G, a subset of the values in the linked list.

Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

Example 1:
Input:
head: 0->1->2->3
G = [0, 1, 3]
Output: 2
Explanation:
0 and 1 are connected, so [0, 1] and [3] are the two connected components.

Example 2:
Input:
head: 0->1->2->3->4
G = [0, 3, 1, 4]
Output: 2
Explanation:
0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

Note:
If N is the length of the linked list given by head, 1 <= N <= 10000.
The value of each node in the linked list will be in the range [0, N - 1].
1 <= G.length <= 10000.
G is a subset of all values in the linked list.
 */
class Solution0817 {
    fun numComponents(head: ListNode?, G: IntArray): Int {
        var res = 0
        var flag = false
        val set = G.toSet()
        var cur = head
        while(cur != null) {
            if (cur.`val` in set) {
                if (!flag) {
                    ++res
                    flag = true
                }
            } else {
                flag = false
            }
            cur = cur.next
        }
        return res
    }
}
/*
Runtime: 224 ms, faster than 100.00% of Kotlin online submissions for Linked List Components.
Memory Usage: 49.2 MB, less than 100.00% of Kotlin online submissions for Linked List Components.
 */