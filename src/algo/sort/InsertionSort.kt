package algo.sort

fun insertionSort(arr: IntArray): Unit {
    for (i in 0 until arr.size) {
        var j = i
        val e = arr[i]
        while(j - 1 >= 0 && arr[j - 1] > e) {
            arr[j] = arr[j - 1]
            --j
        }
        arr[j] = e
    }
}
