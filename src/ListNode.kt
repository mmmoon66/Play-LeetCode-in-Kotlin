import java.lang.StringBuilder

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    private constructor() : this(-1)

    constructor(nums: IntArray) : this() {
        if (nums.isNotEmpty()) {
            this.`val` = nums[0]
            var cur = this
            for (i in 1 until nums.size) {
                cur.next = ListNode(nums[i])
                cur = cur.next!!
            }
        }
    }

    override fun toString(): String {
        val res = StringBuilder()
        var cur: ListNode? = this
        while(cur != null) {
            res.append(cur.`val`).append("->")
            cur = cur.next
        }
        res.append("null")
        return res.toString()
    }
}