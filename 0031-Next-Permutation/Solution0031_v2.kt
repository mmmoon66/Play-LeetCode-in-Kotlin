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
class Solution0031_v2 {
    fun nextPermutation(nums: IntArray) {
        var i = nums.lastIndex - 1
        while(i >= 0 && nums[i] >= nums[i + 1]) --i
        if (i >= 0) {
            var j = nums.lastIndex
            while(j >= i + 1 && nums[j] <= nums[i]) --j
            nums[i] = nums[j].also { nums[j] = nums[i] }
        }
        reverse(nums, i + 1, nums.lastIndex)
    }

    private fun reverse(arr: IntArray, left: Int, right: Int) {
        assert(left >= 0 && right < arr.size)
        var i = left
        var j = right
        while(i < j) {
            arr[i] = arr[j].also { arr[j] = arr[i] }
            ++i
            --j
        }
    }

    fun nextPermutation2(nums: IntArray) {
        // 从后向前找到第一个顺序对(i, i+1),满足nums[i] < nums[i+1]
        var i = nums.lastIndex - 1
        while(i >= 0 && nums[i] >= nums[i + 1]) --i
        if (i < 0) {
            // 没有找到顺序对 => nums是降序数组
            var j = 0
            var k = nums.lastIndex
            while(j < k) {
                nums[j] = nums[k].also { nums[k] = nums[j] }
                ++j
                --k
            }
        } else {
            // 找到顺序对
            // 在区间 nums[i+1,lastIndex] 内从后向前查找第一个大于nums[i]的元素nums[j]
            var j = nums.lastIndex
            while(j >= i + 1 && nums[j] <= nums[i]) --j
            // 交换nums[i]和nums[j]
            nums[i] = nums[j].also { nums[j] = nums[i] }
            // 交换之后，可以证明 nums[i+1,lastIndex]是降序的，将此区间的元素反转一下就能得到最终结果
            var m = i + 1
            var n = nums.lastIndex
            while(m < n) {
                nums[m] = nums[n].also { nums[n] = nums[m] }
                ++m
                --n
            }
        }
    }
}

fun main() {
    val s = Solution0031_v2()
    val nums = intArrayOf(1, 2)
    s.nextPermutation(nums)
    println("size:${nums.size}, contents:${nums.joinToString()}")
}
/*
执行用时：260 ms, 在所有 Kotlin 提交中击败了15.62%的用户
内存消耗：35.6 MB, 在所有 Kotlin 提交中击败了30.21%的用户
 */