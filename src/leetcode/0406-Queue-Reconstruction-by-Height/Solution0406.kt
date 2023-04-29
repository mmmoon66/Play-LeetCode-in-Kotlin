import java.util.*

// https://leetcode.com/problems/queue-reconstruction-by-height/
// 406. Queue Reconstruction by Height
/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
class Solution0406 {
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        people.sortWith(Comparator { a, b ->
            if (a[0] == b[0]) {
                a[1] - b[1]
            } else {
                b[0] - a[0]
            }
        })
        val res = mutableListOf<IntArray>()
        for (p in people) {
            res.add(p[1], p)
        }
        return res.toTypedArray()
    }
}

fun main() {
    val s = Solution0406()
    val res = s.reconstructQueue(
        arrayOf(
            intArrayOf(7, 0),
            intArrayOf(4, 4),
            intArrayOf(7, 1),
            intArrayOf(5, 0),
            intArrayOf(6, 1),
            intArrayOf(5, 2)
        )
    )
    res.forEach { println(Arrays.toString(it)) }
}
/*
Runtime: 556 ms, faster than 20.00% of Kotlin online submissions for Queue Reconstruction by Height.
Memory Usage: 65.4 MB, less than 100.00% of Kotlin online submissions for Queue Reconstruction by Height.
 */