package algo.sort

import java.util.*

fun generateRandomArray(n: Int, rangeL: Int, rangeR: Int): IntArray {
    val res = IntArray(n)
    val random = Random()
    for (i in 0 until n) {
        res[i] = rangeL + random.nextInt(rangeR - rangeL + 1)
    }
    return res
}

fun generateNearlyOrderedArray(n: Int, swapTimes: Int): IntArray {
    val res = IntArray(n) { it }
    val random = Random()
    for (i in 0 until swapTimes) {
        val x = random.nextInt(n)
        val y = random.nextInt(n)
        res[x] = res[y].also { res[y] = res[x] }
    }
    return res
}

fun copyIntArray(arr: IntArray): IntArray = IntArray(arr.size) {arr[it]}

fun isSorted(arr: IntArray): Boolean {
    val n = arr.size
    for (i in 1 until n) {
        if (arr[i] < arr[i - 1]) {
            return false
        }
    }
    return true
}

fun testSort(arr: IntArray, sortName: String, sort: (IntArray) -> Unit): Unit {
    val startTime = System.currentTimeMillis()
    sort(arr)
    val endTime = System.currentTimeMillis()
    assert(isSorted(arr))
    println("$sortName : ${endTime - startTime} ms")
}