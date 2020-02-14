package algo.sort

import java.util.*

fun quickSort(arr: IntArray) {
    __quickSort(arr, 0, arr.size - 1)
}

fun quickSort2Ways(arr: IntArray) {
    __quickSort2Ways(arr, 0, arr.size - 1)
}

fun quickSort3Ways(arr: IntArray) {
    __quickSort3Ways(arr, 0, arr.size - 1)
}

private fun __quickSort(arr: IntArray, l: Int, r: Int) {
    if (l >= r) {
        return
    }
    val p = __partition(arr, l, r)
    __quickSort(arr, l, p - 1)
    __quickSort(arr, p + 1, r)
}

private fun __partition(arr: IntArray, l: Int, r: Int): Int {
    val rand = Random().nextInt(r - l + 1) + l
    arr[l] = arr[rand].also { arr[rand] = arr[l] }
    var p = l // arr[l+1...p] < arr[l], arr[p+1...i) >= arr[l]
    var i = l + 1
    while(i <= r) {
        if (arr[i] < arr[l]) {
            arr[i] = arr[p + 1].also { arr[p + 1] = arr[i] }
            ++p
            ++i
        } else {
            ++i
        }
    }
    arr[l] = arr[p].also { arr[p] = arr[l] }
    return p
}

private fun __quickSort2Ways(arr: IntArray, l: Int, r: Int) {
    if (l >= r) {
        return
    }
    val p = __partition2(arr, l, r)
    __quickSort2Ways(arr, l, p - 1)
    __quickSort2Ways(arr, p + 1, r)
}

private fun __partition2(arr: IntArray, l: Int, r: Int): Int {
    val rand = Random().nextInt(r - l + 1) + l
    arr[l] = arr[rand].also { arr[rand] = arr[l] }
    var i = l + 1 // arr[l+1...i) <= arr[l]
    var j = r
    while(true) {
        while(i <= r && arr[i] < arr[l]) ++i
        while(j >= l + 1 && arr[j] > arr[l]) --j
        if (i > j) break
        arr[i] = arr[j].also { arr[j] = arr[i] }
        ++i
        --j
    }
    arr[l] = arr[j].also { arr[j] = arr[l] }
    return j
}

private fun __quickSort3Ways(arr: IntArray, l: Int, r: Int) {
    if (l >= r) {
        return
    }
    val p = __partition3(arr, l, r)
    __quickSort3Ways(arr, l, p.first)
    __quickSort3Ways(arr, p.second, r)
}

private fun __partition3(arr: IntArray, l: Int, r: Int): Pair<Int, Int> {
    val rand = Random().nextInt(r - l + 1) + l
    arr[l] = arr[rand].also { arr[rand] = arr[l] }
    var lt = l // arr[l+1...lt] < v
    var gt = r + 1 // arr[gt...r] > v
    var i = l + 1
    while(i < gt) {
        if (arr[i] < arr[l]) {
            arr[i] = arr[lt + 1].also { arr[lt + 1] = arr[i] }
            ++i
            ++lt
        } else if (arr[i] > arr[l]) {
            arr[i] = arr[gt - 1].also { arr[gt - 1] = arr[i] }
            --gt
        } else {
            ++i
        }
    }
    arr[l] = arr[lt].also { arr[lt] = arr[l] }
    --lt
    return lt to gt
}

fun main() {
    val arr = intArrayOf(5, 4, 3, 2, 1)
    quickSort3Ways(arr)
    println(Arrays.toString(arr))
}