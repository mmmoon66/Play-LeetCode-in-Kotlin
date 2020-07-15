import java.util.*

// https://leetcode.com/problems/next-permutation/
// 31. Next Permutation
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */
class Solution0031 {

    private var found = false
    private var nextPermutation = intArrayOf()
    private var nums: IntArray = intArrayOf()

    fun nextPermutation(nums: IntArray): Unit {
        this.found = false
        this.nextPermutation = intArrayOf()
        this.nums = nums
        val used = BooleanArray(nums.size)
        val sortedNums = nums.sortedArray()
        generatePermutation(sortedNums, mutableListOf(), used)
        if (nextPermutation.isEmpty()) {
            nextPermutation = sortedNums
        }
        for (i in nums.indices) {
            nums[i] = nextPermutation[i]
        }
//        println(Arrays.toString(nums))
    }

    private fun generatePermutation(
            nums: IntArray,
            path: MutableList<Int>,
            used: BooleanArray) {
        if (path.size == nums.size) {
            if (found) {
                nextPermutation = path.toIntArray()
                return
            }
            if (path.toIntArray().contentEquals(this.nums)) {
                found = true
            }
            return
        }
        for (i in nums.indices) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1] && used[i - 1].not()) {
                continue
            }
            if (used[i].not()) {
                path.add(nums[i])
                used[i] = true
                generatePermutation(nums, path, used)
                if (found && nextPermutation.isNotEmpty()) return
                path.removeAt(path.size - 1)
                used[i] = false
            }
        }
    }
}

fun main() {
    val s = Solution0031()
    s.nextPermutation(intArrayOf(1, 2, 3))
    s.nextPermutation(intArrayOf(3, 2, 1))
    s.nextPermutation(intArrayOf(1, 1, 5))
//    s.nextPermutation(intArrayOf(6, 7, 5, 3, 5, 6, 2, 9, 1, 2, 7, 0, 9))
}
/*
Time Limit Exceeded
 */