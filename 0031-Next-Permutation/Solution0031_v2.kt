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
class Solution0031_v2 {
    fun nextPermutation(nums: IntArray) {
        var i = nums.lastIndex - 1
        // 从后向前找到第一个升序对
        while (i >= 0 && nums[i] >= nums[i + 1]) --i
        if (i >= 0) {
            // 从后向前找到第一个比nums[i]大的元素
            var j = nums.lastIndex
            while (j >= i + 1 && nums[j] <= nums[i]) --j
            // 交换nums[i]和nums[j]
            nums[i] = nums[j].also { nums[j] = nums[i] }
        }
        // 可以证明，交换完nums[i]和nums[j]后，nums[i+1..lastIndex]是降序的，需要将这个区间的元素reverse成升序的
        reverse(nums, i + 1, nums.lastIndex)
    }

    private fun reverse(nums: IntArray, i: Int, j: Int) {
        assert(i >= 0 && j < nums.size)
        var p = i
        var q = j
        while (p < q) {
            nums[p] = nums[q].also { nums[q] = nums[p] }
            ++p
            --q
        }
    }
}

fun main() {
    val s = Solution0031_v2()
    val nums = intArrayOf(1, 3, 2)
    s.nextPermutation(nums)
    println(nums.joinToString())
}
/*
执行用时：260 ms, 在所有 Kotlin 提交中击败了15.62%的用户
内存消耗：35.6 MB, 在所有 Kotlin 提交中击败了30.21%的用户
 */