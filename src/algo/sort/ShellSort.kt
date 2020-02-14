package algo.sort

fun shellSort(arr: IntArray) {
    val n = arr.size
    var h = 1
    while(h < n / 3) {
        h = 3 * h + 1
    }
    while(h >= 1) {
        for (i in 0 until n) {
            var j = i
            val e = arr[i]
            while(j - h >= 0 && arr[j - h] > e) {
                arr[j] = arr[j - h]
                j -= h
            }
            arr[j] = e
        }
        h /= 3
    }
}