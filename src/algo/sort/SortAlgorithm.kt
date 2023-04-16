package algo.sort

import java.util.*
import kotlin.system.measureTimeMillis

class SelectionSort {
    fun sort(arr: IntArray) {
        val n = arr.size
        for (i in 0 until n) {
            var minIndex = i
            for (j in i + 1 until n) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }
            if (minIndex != i) {
                arr[i] = arr[minIndex].also { arr[minIndex] = arr[i] }
            }
        }
    }
}

class BubbleSort {
    fun sort(arr: IntArray) {
        var n = arr.size
        while (n > 0) {
            var i = 0
            while (i + 1 < n) {
                if (arr[i] > arr[i + 1]) {
                    arr[i] = arr[i + 1].also { arr[i + 1] = arr[i] }
                }
                ++i
            }
            --n
        }
    }
}

class InsertionSort {
    fun sort(arr: IntArray) {
        val n = arr.size
        for (i in 0 until n) {
            val e = arr[i]
            var j = i
            while (j - 1 >= 0 && arr[j - 1] > e) {
                arr[j] = arr[j - 1]
                --j
            }
            arr[j] = e
        }
    }
}

class ShellSort {
    fun sort(arr: IntArray) {
        val n = arr.size
        var h = 1
        while (h * 3 <= n) h *= 3
        while (h > 0) {
            for (i in 0 until n) {
                val e = arr[i]
                var j = i
                while (j - h >= 0 && arr[j - h] > e) {
                    arr[j] = arr[j - h]
                    j -= h
                }
                arr[j] = e
            }
            h /= 3
        }
    }
}

class QuickSort {
    fun sort(arr: IntArray) {
        _sort(arr, 0, arr.size - 1)
    }

    private fun _sort(arr: IntArray, l: Int, r: Int) {
        if (l >= r) return
        val (lt, gt) = _partition(arr, l, r)
        _sort(arr, l, lt)
        _sort(arr, gt, r)
    }

    private fun _partition(arr: IntArray, l: Int, r: Int): Pair<Int, Int> {
        val rand = Random().nextInt(r - l + 1) + l
        val e = arr[rand]
        var lt = l - 1//arr[l..lt]<e
        var gt = r + 1//arr[gt..r]>e
        var i = l//arr[lt+1..i)==e
        while (i < gt) {
            if (arr[i] < e) {
                arr[lt + 1] = arr[i].also { arr[i] = arr[lt + 1] }
                ++lt
                ++i
            } else if (arr[i] > e) {
                arr[i] = arr[gt - 1].also { arr[gt - 1] = arr[i] }
                --gt
            } else {//arr[i]==e
                ++i
            }
        }
        return lt to gt
    }
}

class MergeSort {
    fun sort(arr: IntArray) {
        val aux = IntArray(arr.size)
        _sort(arr, 0, arr.size - 1, aux)
    }

    private fun _sort(arr: IntArray, l: Int, r: Int, aux: IntArray) {
        if (l >= r) return
        val mid = l + (r - l) / 2
        _sort(arr, l, mid, aux)
        _sort(arr, mid + 1, r, aux)
        if (arr[mid] > arr[mid + 1]) {
            _merge(arr, l, mid, r, aux)
        }
    }

    private fun _merge(arr: IntArray, l: Int, mid: Int, r: Int, aux: IntArray) {
        for (i in l..r) aux[i] = arr[i]
        var i = l
        var j = mid + 1
        var k = l
        while (k <= r) {
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
}

class MergeSortBU {
    fun sort(arr: IntArray) {
        val n = arr.size
        val aux = IntArray(n)
        var size = 1
        while (size < n) {
            var i = 0
            // 对arr[i,i+size-1]和arr[i+size,i+2*size-1]两部分进行归并
            while (i < n) {
                if (i + size >= n) break//右子区间不存在
                val mid = i + size - 1
                if (arr[mid] > arr[mid + 1]) {
                    val l = i
                    val r = minOf(n - 1, i + 2 * size - 1)
                    merge(arr, l, mid, r, aux)
                }
                // 每次对2*size个元素进行归并
                i += 2 * size
            }
            size *= 2
        }
    }

    // 对arr[l..mid]和arr[mid+1..r]进行归并
    private fun merge(arr: IntArray, l: Int, mid: Int, r: Int, aux: IntArray) {
        for (i in l..r) aux[i] = arr[i]
        var i = l
        var j = mid + 1
        for (k in l..r) {
            if (i > mid) {
                arr[k] = aux[j++]
            } else if (j > r) {
                arr[k] = aux[i++]
            } else if (aux[i] < aux[j]) {
                arr[k] = aux[i++]
            } else {
                arr[k] = aux[j++]
            }
        }
    }
}

class HeapSort {
    fun sort(arr: IntArray) {
        val n = arr.size
        // heapify: 从最后一个非叶子节点开始执行shiftDown
        // 最后一个节点的父节点就是最后一个非叶子节点
        // 最后一个节点的索引为n-1,则最后一个非叶子节点的索引为((n-1)-1)/2=n/2-1
        for (i in n / 2 - 1 downTo 0) {
            _shiftDown(arr, i, n - 1)
        }
        for (i in n - 1 downTo 0) {
            // 将最大元素arr[0]换到i位置
            arr[0] = arr[i].also { arr[i] = arr[0] }
            // 将arr[0..i)范围内元素进行shiftDown
            _shiftDown(arr, 0, i - 1)
        }
    }

    // 在arr[k..r]范围内执行shiftDown操作
    private fun _shiftDown(arr: IntArray, k: Int, r: Int) {
        var i = k
        while (2 * i + 1 <= r) {//存在左孩子
            var j = 2 * i + 1
            // 存在右孩子并且右孩子大于左孩子
            if (j + 1 <= r && arr[j + 1] > arr[j]) ++j
            if (arr[j] <= arr[i]) break
            arr[i] = arr[j].also { arr[j] = arr[i] }
            i = j
        }
    }
}

fun main() {
    val n = 100000
    val arr = generateRandomArray(n, 0, n)

//    val sortClass = SelectionSort()
//    val sortClass = BubbleSort()
//    val sortClass = InsertionSort()
//    val sortClass = ShellSort()
//    val sortClass = QuickSort()
//    val sortClass = MergeSort()
    val sortClass = MergeSortBU()
//    val sortClass = HeapSort()
    val costTime = measureTimeMillis {
        sortClass.sort(arr)
    }
    println("isSorted:${isSorted(arr)}, costTime:$costTime ms")
}