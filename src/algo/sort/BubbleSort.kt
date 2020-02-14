package algo.sort

import java.util.*

fun bubbleSort(arr: IntArray) {
    var n = arr.size
    while(n > 0) {
        for (i in 0 until n - 1) {
            if (arr[i] > arr[i + 1]) {
                arr[i] = arr[i + 1].also { arr[i + 1] = arr[i] }
            }
        }
        --n
    }
}

fun main() {
    val arr = intArrayOf(5, 4, 3, 2, 1)
    bubbleSort(arr)
    println(Arrays.toString(arr))
}