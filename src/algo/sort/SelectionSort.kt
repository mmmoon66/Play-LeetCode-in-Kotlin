package algo.sort

fun selectionSort(arr: IntArray): Unit {
    val n = arr.size
    for (i in 0 until n) {
        var minIndex = i
        for (j in i + 1 until n) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j
            }
        }
        if (i != minIndex) {
            arr[i] = arr[minIndex].also { arr[minIndex] = arr[i] }
        }
    }
}
