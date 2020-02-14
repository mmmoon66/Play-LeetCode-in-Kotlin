package algo.sort

import java.util.*

fun mergeSort(arr: IntArray) {
    val n = arr.size
    if (n <= 1) {
        return
    }
    val aux = IntArray(n)
    __mergeSort(arr, aux, 0, n - 1)
}

fun mergeSortBU(arr: IntArray) {
    val n = arr.size
    if (n <= 1) {
        return
    }
    val aux = IntArray(n)
    var sz = 1
    while(sz <= n) {
        var i = 0
        while(i + sz < n) {
            // 对 arr[i...i+sz-1], arr[i+sz...i+2*sz-1] 两部分进行归并
            if (arr[i + sz - 1] > arr[i + sz]) {
                __merge(arr, aux, i, i + sz - 1, minOf(i + 2 * sz - 1, n - 1))
            }
            i += 2 * sz
        }
        sz *= 2
    }
}

private fun __mergeSort(arr: IntArray, aux: IntArray, l: Int, r: Int) {
    if (l >= r) {
        return
    }
    val mid = l + (r - l) / 2
    __mergeSort(arr, aux, l, mid)
    __mergeSort(arr, aux, mid + 1, r)
    if (arr[mid] > arr[mid + 1]) {
        __merge(arr, aux, l, mid, r)
    }
}

private fun __merge(arr: IntArray, aux: IntArray, l: Int, mid: Int, r: Int) {
    for (i in l..r) {
        aux[i] = arr[i]
    }
    var i = l
    var j = mid + 1
    var k = l
    while(k <= r) {
        if (i > mid) {
            arr[k++] = aux[j++]
        } else if (j > r) {
            arr[k++] = aux[i++]
        } else if (aux[i] < aux[j]) {
            arr[k++] = aux[i++]
        } else {
            arr[k++] = aux[j++]
        }
    }
}

fun main() {
    val arr = intArrayOf(5, 4, 3, 2, 1, 6, 10, 9, 8, 7, 11, 12)
    mergeSortBU(arr)
    println(Arrays.toString(arr))
}

