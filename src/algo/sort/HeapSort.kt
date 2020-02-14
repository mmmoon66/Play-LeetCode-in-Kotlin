package algo.sort

import java.util.*

fun heapSort(arr: IntArray) {
    val n = arr.size
    if (n <= 1) {
        return
    }
    for (i in (n - 1 - 1) / 2 downTo 0) {
        shiftDown(arr, n, i)
    }
    for (i in n - 1 downTo 0) {
        arr[0] = arr[i].also { arr[i] = arr[0] }
        shiftDown(arr, i, 0)
    }
}

private fun shiftDown(arr: IntArray, n: Int, k: Int) {
    val e = arr[k]
    var i = k
    while(2 * i + 1 < n) {
        var j = 2 * i + 1
        if (j + 1 < n && arr[j + 1] > arr[j]) {
            ++j
        }
        if (e >= arr[j]) {
            break
        }
        arr[i] = arr[j]
        i = j
    }
    arr[i] = e
}

fun main() {
    val arr = intArrayOf(5, 4, 3, 2, 1)
    testSort(arr, "HeapSort", ::heapSort)
    println(Arrays.toString(arr))
}