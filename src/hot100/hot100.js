// region 哈希
// 1. 两数之和
{
    /**
     * @param {number[]} nums
     * @param {number} target
     * @return {number[]}
     */
    var twoSum = function (nums, target) {
        const map = {}
        for (let i = 0; i < nums.length; ++i) {
            const num = nums[i]
            const other = target - num
            if (other in map) {
                return [map[other], i]
            }
            map[num] = i
        }
        return []
    };
}

// 49. 字母异位词分组
{
    /**
     * @param {string[]} strs
     * @return {string[][]}
     */
    var groupAnagrams = function (strs) {
        const map = {}
        for (const str of strs) {
            const sorted = str.split('').sort().join('')
            if (!(sorted in map)) {
                map[sorted] = []
            }
            map[sorted].push(str)
        }
        return Object.values(map)
    };
}

// 128. 最长连续序列
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var longestConsecutive = function (nums) {
        const set = {}
        for (const num of nums) {
            set[num] = true
        }
        let res = 0
        for (const num of nums) {
            if (!((num - 1) in set)) {
                let curNum = num
                let curLen = 0
                while (curNum in set) {
                    ++curLen
                    ++curNum
                }
                res = Math.max(res, curLen)
            }
        }
        return res
    };
}
// endregion

// region 双指针
// 283. 移动零
{
    /**
     * @param {number[]} nums
     * @return {void} Do not return anything, modify nums in-place instead.
     */
    var moveZeroes = function (nums) {
        let i = 0
        for (const num of nums) {
            if (num !== 0) {
                nums[i++] = num
            }
        }
        for (; i < nums.length; ++i) {
            nums[i] = 0
        }
    };
}

// 11. 盛最多水的容器
{
    /**
     * @param {number[]} height
     * @return {number}
     */
    var maxArea = function (height) {
        const n = height.length
        let i = 0
        let j = n - 1
        let res = 0
        while (i < j) {
            const area = (j - i) * Math.min(height[i], height[j])
            res = Math.max(res, area)
            if (height[i] < height[j]) {
                ++i
            } else {
                --j
            }
        }
        return res
    };
}

// 15. 三数之和
{
    /**
     * @param {number[]} nums
     * @return {number[][]}
     */
    var threeSum = function (nums) {
        function nextIndex(nums, i) {
            let j = i + 1
            while (j < nums.length) {
                if (nums[j] !== nums[i]) {
                    return j
                }
                ++j
            }
            return nums.length
        }

        function prevIndex(nums, i) {
            let j = i - 1
            while (j >= 0) {
                if (nums[j] !== nums[i]) {
                    return j
                }
                --j
            }
            return -1
        }

        nums.sort((a, b) => a - b)
        const n = nums.length
        const res = []
        for (let i = 0; i < n;) {
            let j = i + 1
            let k = n - 1
            while (j < k) {
                const sum = nums[i] + nums[j] + nums[k]
                if (sum === 0) {
                    res.push([nums[i], nums[j], nums[k]])
                    j = nextIndex(nums, j)
                    k = prevIndex(nums, k)
                } else if (sum < 0) {
                    j = nextIndex(nums, j)
                } else {
                    k = prevIndex(nums, k)
                }
            }
            i = nextIndex(nums, i)
        }
        return res
    };
}

// 42. 接雨水
{
    /**
     * @param {number[]} height
     * @return {number}
     */
    var trap = function (height) {
        const n = height.length
        // leftMax[i]表示height[0..i]范围内最大值
        const leftMax = []
        leftMax[0] = height[0]
        for (let i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i])
        }
        // rightMax[i]表示height[i..end]范围内最大值
        const rightMax = []
        rightMax[n - 1] = height[n - 1]
        for (let i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i])
        }
        let res = 0
        for (let i = 1; i < n - 1; ++i) {
            res += Math.max(0, Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i])
        }
        return res
    };
}
// endregion

// region 滑动窗口
// 3. 无重复字符的最长子串
{
    /**
     * @param {string} s
     * @return {number}
     */
    var lengthOfLongestSubstring = function (s) {
        const map = {}
        const n = s.length
        let i = 0
        let j = 0
        let res = 0
        while (j < n) {
            const c = s[j]
            map[c] = (map[c] ?? 0) + 1
            while (map[c] > 1) {
                map[s[i]] = map[s[i]] - 1
                ++i
            }
            res = Math.max(res, j - i + 1)
            ++j
        }
        return res
    };
}

// 438. 找到字符串中所有字母异位词
{
    /**
     * @param {string} s
     * @param {string} p
     * @return {number[]}
     */
    var findAnagrams = function (s, p) {
        function isAnagram(a, b) {
            for (let i = 0; i < a.length; ++i) {
                if (a[i] !== b[i]) return false
            }
            return true
        }

        const m = s.length
        const n = p.length
        const res = []
        if (m < n) {
            return res
        }
        const a = new Array(26).fill(0)
        const b = new Array(26).fill(0)
        for (let i = 0; i < n; ++i) {
            ++a[s[i].charCodeAt(0) - 'a'.charCodeAt(0)]
            ++b[p[i].charCodeAt(0) - 'a'.charCodeAt(0)]
        }
        for (let i = 0; i + n - 1 < m; ++i) {
            if (isAnagram(a, b)) {
                res.push(i)
            }
            if (i + n < m) {
                ++a[s[i + n].charCodeAt(0) - 'a'.charCodeAt(0)]
                --a[s[i].charCodeAt(0) - 'a'.charCodeAt(0)]
            }
        }
        return res
    };
}
// endregion

// region 子串
// 560. 和为 K 的子数组
{
    /**
     * @param {number[]} nums
     * @param {number} k
     * @return {number}
     */
    var subarraySum = function (nums, k) {
        const map = {}
        map[0] = 1
        let sum = 0
        let res = 0
        for (const num of nums) {
            sum += num
            // sum - prevSum = k
            const prevSum = sum - k
            res += map[prevSum] || 0
            map[sum] = (map[sum] || 0) + 1
        }
        return res
    };
}

// 239. 滑动窗口最大值
{
    class MyPriorityQueue {
        /**
         * @param comparator (number, number) => number
         */
        constructor(comparator) {
            this.comparator = comparator
            this.data = []
        }

        /**
         *
         * @param e number
         */
        offer(e) {
            // this.data.splice(0, 0, e)
            this.data.unshift(e)
            this.__shiftDown(0)
        }

        /**
         * @return number
         */
        poll() {
            const result = this.data[0]
            this.data[0] = this.data[this.data.length - 1]
            this.data.pop()
            this.__shiftDown(0)
            return result
        }

        /**
         * @return number
         */
        peek() {
            return this.data[0]
        }

        /**
         * @param i number
         * @private
         */
        __shiftDown(i) {
            while (2 * i + 1 < this.data.length) {
                let j = 2 * i + 1
                if (j + 1 < this.data.length && this.comparator(this.data[j + 1], this.data[j]) < 0) {
                    ++j
                }
                if (this.comparator(this.data[i], this.data[j]) <= 0) {
                    break
                }
                const temp = this.data[i]
                this.data[i] = this.data[j]
                this.data[j] = temp
                i = j
            }
        }

        /**
         * @return number
         */
        size() {
            return this.data.length
        }

        /**
         * @return boolean
         */
        isEmpty() {
            return !this.data.length
        }
    }

    /**
     * @param {number[]} nums
     * @param {number} k
     * @return {number[]}
     */
    var maxSlidingWindow = function (nums, k) {
        const res = []
        const n = nums.length
        const queue = new MyPriorityQueue((a, b) => {
            if (nums[a] === nums[b]) {
                return a - b
            } else {
                return nums[b] - nums[a]
            }
        })
        for (let i = 0; i < k; ++i) {
            queue.offer(i)
        }
        let j = 0
        res[j++] = nums[queue.peek()]
        for (let i = k; i < n; ++i) {
            queue.offer(i)
            while (!queue.isEmpty() && queue.peek() <= i - k) {
                queue.poll()
            }
            res[j++] = nums[queue.peek()]
        }
        return res
    };
}

// 76. 最小覆盖子串
{
    /**
     * @param {number[]} a
     * @param {number[]} b
     * @return {boolean}
     */
    function isCover(a, b) {
        for (let i = 0; i < a.length; ++i) {
            if (a[i] < b[i]) return false
        }
        return true
    }

    /**
     * @param {string} s
     * @param {string} t
     * @return {string}
     */
    var minWindow = function (s, t) {
        const a = new Array(128).fill(0)
        const b = new Array(128).fill(0)
        for (const c of t) {
            ++b[c.charCodeAt(0)]
        }
        let start = 0
        let end = 0
        let minLen = s.length + 1
        let res = ""
        while (end < s.length) {
            ++a[s[end].charCodeAt(0)]
            while (isCover(a, b)) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1
                    res = s.substring(start, end + 1)
                }
                --a[s[start++].charCodeAt(0)]
            }
            ++end
        }
        return res
    };
}
// endregion

// region 普通数组
// 53. 最大子数组和
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var maxSubArray = function (nums) {
        const n = nums.length
        // dp[i]表示以nums[i]结尾的子数组的最大和
        const dp = []
        dp[0] = nums[0]
        let res = dp[0]
        for (let i = 1; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i])
            res = Math.max(res, dp[i])
        }
        return res
    };
}

// 56. 合并区间
{
    /**
     * @param {number[][]} intervals
     * @return {number[][]}
     */
    var merge = function (intervals) {
        intervals.sort((a, b) => a[0] - b[0])
        const res = []
        for (const interval of intervals) {
            if (res.length && res[res.length - 1][1] >= interval[0]) {
                res[res.length - 1][1] = Math.max(res[res.length - 1][1], interval[1])
            } else {
                res.push(interval)
            }
        }
        return res
    };
}

// 189. 轮转数组
{
    /**
     * @param {number[]} nums
     * @param {number} start
     * @param {number} end
     * @return {void}
     */
    function reverse(nums, start, end) {
        while (start < end) {
            const temp = nums[start]
            nums[start] = nums[end]
            nums[end] = temp
            ++start
            --end
        }
    }

    /**
     * @param {number[]} nums
     * @param {number} k
     * @return {void} Do not return anything, modify nums in-place instead.
     */
    var rotate = function (nums, k) {
        const n = nums.length
        k = k % n
        if (k === 0) return

        reverse(nums, 0, n - 1)
        reverse(nums, 0, k - 1)
        reverse(nums, k, n - 1)
    };
}

// 238. 除自身以外数组的乘积
{
    /**
     * @param {number[]} nums
     * @return {number[]}
     */
    var productExceptSelf = function (nums) {
        const n = nums.length
        // leftProduct[i]表示nums[0..i]范围内元素的乘积
        const leftProduct = new Array(n)
        // rightProduct[i]表示nums[i..end]范围内元素的乘积
        const rightProduct = new Array(n)
        leftProduct[0] = nums[0]
        for (let i = 1; i < n; ++i) {
            leftProduct[i] = leftProduct[i - 1] * nums[i]
        }
        rightProduct[n - 1] = nums[n - 1]
        for (let i = n - 2; i >= 0; --i) {
            rightProduct[i] = rightProduct[i + 1] * nums[i]
        }
        const res = []
        for (let i = 0; i < n; ++i) {
            let product = 0
            if (i === 0) {
                product = rightProduct[1]
            } else if (i === n - 1) {
                product = leftProduct[n - 2]
            } else {
                product = leftProduct[i - 1] * rightProduct[i + 1]
            }
            res.push(product)
        }
        return res
    };
}
// endregion

// region 矩阵
// 54. 螺旋矩阵
{
    /**
     * @param {number[][]} matrix
     * @return {number[]}
     */
    var spiralOrder = function (matrix) {
        const visited = 1024
        const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        const m = matrix.length
        const n = matrix[0].length
        const total = m * n
        const res = []
        res.push(matrix[0][0])
        matrix[0][0] = visited
        let i = 0
        let j = 0
        let k = 0
        while (res.length < total) {
            const d = directions[k]
            const newI = i + d[0]
            const newJ = j + d[1]
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && matrix[newI][newJ] !== visited) {
                i = newI
                j = newJ
                res.push(matrix[i][j])
                matrix[i][j] = visited
            } else {
                k = (k + 1) % 4
            }
        }
        return res
    };
}
// endregion

// region 链表
function ListNode(val) {
    this.val = val;
    this.next = null;
}

// 160. 相交链表
{
    /**
     * @param {ListNode} headA
     * @param {ListNode} headB
     * @return {ListNode}
     */
    var getIntersectionNode = function (headA, headB) {
        let a = headA
        let b = headB
        while (a !== b) {
            a = a === null ? headB : a.next
            b = b === null ? headA : b.next
        }
        return a
    };
}

// 206. 反转链表
{
    /**
     * @param {ListNode} head
     * @return {ListNode}
     */
    var reverseList = function (head) {
        if (!head) {
            return null
        }
        const dummyHead = new ListNode(-1)
        while (head) {
            const node = head
            head = head.next
            node.next = dummyHead.next
            dummyHead.next = node
        }
        return dummyHead.next
    };
}

// 234. 回文链表
{
    /**
     * @param {ListNode} head
     * @return {boolean}
     */
    var isPalindrome = function (head) {
        const values = []
        let cur = head
        while (cur) {
            values.push(cur.val)
            cur = cur.next
        }
        let i = 0
        let j = values.length - 1
        while (i < j) {
            if (values[i] !== values[j]) {
                return false
            }
            ++i
            --j
        }
        return true
    };
}

// 141. 环形链表
{
    /**
     * @param {ListNode} head
     * @return {boolean}
     */
    var hasCycle = function (head) {
        let fast = head
        let slow = head
        while (!!fast) {
            fast = fast?.next?.next
            slow = slow?.next
            if (!!fast && fast === slow) {
                return true
            }
        }
        return false
    };
}

// 25. K个一组翻转链表
{
    /**
     * Definition for singly-linked list.
     * function ListNode(val, next) {
     *     this.val = (val===undefined ? 0 : val)
     *     this.next = (next===undefined ? null : next)
     * }
     */
    /**
     * @param {ListNode} head
     * @param {number} k
     * @return {ListNode}
     */
    var reverseKGroup = function (head, k) {
        if (!head || k === 1) {
            return head
        }
        let cur = head
        let count = 1
        while (cur) {
            cur = cur.next
            if (!!cur && ++count === k) {
                break
            }
        }
        if (count < k) {
            return head
        }
        let rightHead = cur.next
        cur.next = null
        rightHead = reverseKGroup(rightHead, k)

        cur = head
        const dummyHead = new ListNode()
        while (cur) {
            const node = cur
            cur = cur.next
            node.next = dummyHead.next
            dummyHead.next = node
        }
        head.next = rightHead
        return dummyHead.next
    };
}

// 21. 合并两个有序链表
{
    /**
     * @param {ListNode} list1
     * @param {ListNode} list2
     * @return {ListNode}
     */
    var mergeTwoLists = function (list1, list2) {
        const dummyHead = new ListNode()
        let i = list1
        let j = list2
        let cur = dummyHead
        while (i && j) {
            if (i.val < j.val) {
                const node = i
                i = i.next
                node.next = null
                cur.next = node
                cur = cur.next
            } else {
                const node = j
                j = j.next
                node.next = null
                cur.next = node
                cur = cur.next
            }
        }
        if (i) {
            cur.next = i
        }
        if (j) {
            cur.next = j
        }
        return dummyHead.next
    };
}

// 2. 两数相加
{
    /**
     * @param {ListNode} l1
     * @param {ListNode} l2
     * @return {ListNode}
     */
    var addTwoNumbers = function (l1, l2) {
        const dummyHead = new ListNode()
        let carry = 0
        let cur = dummyHead
        while (l1 || l2 || carry) {
            let sum = 0
            if (l1) {
                sum += l1.val
                l1 = l1.next
            }
            if (l2) {
                sum += l2.val
                l2 = l2.next
            }
            sum += carry

            carry = Math.trunc(sum / 10)
            cur.next = new ListNode(sum % 10)
            cur = cur.next
        }
        return dummyHead.next
    };
}
// endregion

// region 二叉树
function TreeNode(val, left, right) {
    this.val = val === undefined ? 0 : val
    this.left = left === undefined ? null : left
    this.right = right === undefined ? null : right
}

// 94. 二叉树的中序遍历
{
    /**
     * @param {TreeNode} root
     * @return {number[]}
     */
    var inorderTraversal = function (root) {
        const res = []
        inorder(root, res)
        return res
    };

    /**
     * @param {TreeNode | null} root
     * @param {number[]} res
     */
    function inorder(root, res) {
        if (!root) return
        inorder(root.left, res)
        res.push(root.val)
        inorder(root.right, res)
    }
}

// 104. 二叉树的最大深度
{
    /**
     * @param {TreeNode} root
     * @return {number}
     */
    var maxDepth = function (root) {
        if (!root) return 0
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    };
}

// 102. 二叉树的层序遍历
{
    /**
     * @param {TreeNode} root
     * @return {number[][]}
     */
    var levelOrder = function (root) {
        const res = []
        if (!root) return res
        const queue = [root]
        while (queue.length) {
            const len = queue.length
            const level = []
            for (let i = 0; i < len; ++i) {
                const node = queue.shift()
                level.push(node.val)
                if (node.left) {
                    queue.push(node.left)
                }
                if (node.right) {
                    queue.push(node.right)
                }
            }
            res.push(level)
        }
        return res
    };
}
// endregion

// region 图论
// 994. 腐烂的橘子
{
    /**
     * @param {number[][]} grid
     * @return {number}
     */
    var orangesRotting = function (grid) {
        const queue = []
        const m = grid.length
        const n = grid[0].length
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                if (grid[i][j] === 2) {
                    queue.push({ i, j, depth: 0 })
                }
            }
        }
        const directions = [[-1, 0],//up
            [0, 1],//right
            [1, 0],//down
            [0, -1],//left
        ]
        let maxDepth = 0
        while (queue.length) {
            const { i, j, depth } = queue.splice(0, 1)[0]
            maxDepth = Math.max(maxDepth, depth)
            for (const d of directions) {
                const newI = i + d[0]
                const newJ = j + d[1]
                const newDepth = depth + 1
                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && grid[newI][newJ] === 1) {
                    queue.push({ i: newI, j: newJ, depth: newDepth })
                    grid[newI][newJ] = 2
                }
            }
        }
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                if (grid[i][j] === 1) {
                    return -1
                }
            }
        }
        return maxDepth
    };
}

// 207. 课程表
{
    /**
     * @param {number} numCourses
     * @param {number[][]} prerequisites
     * @return {boolean}
     */
    var canFinish = function (numCourses, prerequisites) {
        const inDegree = new Array(numCourses).fill(0)
        const edges = new Array(numCourses).fill(null).map(() => [])
        for (const [course, pre] of prerequisites) {
            ++inDegree[course]
            edges[pre].push(course)
        }
        const queue = []
        for (let i = 0; i < numCourses; ++i) {
            if (inDegree[i] === 0) {
                queue.push(i)
            }
        }
        let count = 0
        while (queue.length) {
            const course = queue.shift()
            ++count
            for (const next of edges[course]) {
                if (--inDegree[next] === 0) {
                    queue.push(next)
                }
            }
        }
        return count === numCourses
    };
}

// 208. 实现字典树
{
    var Trie = function () {
        this.root = { isWord: false, children: new Map() }
    };

    /**
     * @param {string} word
     * @return {void}
     */
    Trie.prototype.insert = function (word) {
        let cur = this.root
        for (const c of word) {
            if (!cur.children.has(c)) {
                cur.children.set(c, { isWord: false, children: new Map() })
            }
            cur = cur.children.get(c)
        }
        cur.isWord = true
    };

    /**
     * @param {string} word
     * @return {boolean}
     */
    Trie.prototype.search = function (word) {
        let cur = this.root
        for (const c of word) {
            if (!cur.children.has(c)) {
                return false
            }
            cur = cur.children.get(c)
        }
        return !!cur.isWord
    };

    /**
     * @param {string} prefix
     * @return {boolean}
     */
    Trie.prototype.startsWith = function (prefix) {
        let cur = this.root
        for (const c of prefix) {
            if (!cur.children.has(c)) {
                return false
            }
            cur = cur.children.get(c)
        }
        return !!cur
    };

    /**
     * Your Trie object will be instantiated and called as such:
     * var obj = new Trie()
     * obj.insert(word)
     * var param_2 = obj.search(word)
     * var param_3 = obj.startsWith(prefix)
     */
}
// endregion

// region 回溯
// endregion

// region 二分查找
// 35. 搜索插入位置
{
    /**
     * @param {number[]} nums
     * @param {number} target
     * @return {number}
     */
    var searchInsert = function (nums, target) {
        let l = 0
        let r = nums.length - 1
        while (l <= r) {
            const mid = l + Math.trunc((r - l) / 2)
            if (target === nums[mid]) {
                return mid
            } else if (target < nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return l
    };
}
// 74. 搜索二维矩阵
{
    /**
     * @param {number[][]} matrix
     * @param {number} target
     * @return {boolean}
     */
    var searchMatrix = function (matrix, target) {
        const m = matrix.length
        const n = matrix[0].length
        let i = 0
        let j = n - 1
        while (i < m && j >= 0) {
            if (target === matrix[i][j]) {
                return true
            } else if (target < matrix[i][j]) {
                --j
            } else {
                ++i
            }
        }
        return false
    };
}
//34. 在排序数组中查找元素的第一个和最后一个位置
{
    /**
     * @param {number[]} nums
     * @param {number} target
     * @return {number[]}
     */
    var searchRange = function (nums, target) {
        return [searchLower(nums, target), searchUpper(nums, target)]
    };

    /**
     * @param {number[]} nums
     * @param {number} target
     * @return {number}
     */
    function searchUpper(nums, target) {
        let l = 0
        let r = nums.length - 1
        while (l <= r) {
            const mid = l + Math.trunc((r - l) / 2)
            if (target === nums[mid]) {
                if (mid === r || nums[mid + 1] > nums[mid]) {
                    return mid
                } else {
                    l = mid + 1
                }
            } else if (target < nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return -1
    }

    /**
     * @param {number[]} nums
     * @param {number} target
     * @return {number}
     */
    function searchLower(nums, target) {
        let l = 0
        let r = nums.length - 1
        while (l <= r) {
            const mid = l + Math.trunc((r - l) / 2)
            if (target === nums[mid]) {
                if (mid === l || nums[mid - 1] < nums[mid]) {
                    return mid
                } else {
                    r = mid - 1
                }
            } else if (target < nums[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return -1
    }
}
// 33. 搜索旋转排序数组
{
    /**
     * @param {number[]} nums
     * @param {number} target
     * @return {number}
     */
    var search = function (nums, target) {
        let l = 0
        let r = nums.length - 1
        while (l <= r) {
            const mid = l + Math.trunc((r - l) / 2)
            if (target === nums[mid]) {
                return mid
            }
            if (nums[l] <= nums[mid]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1
                } else {
                    l = mid + 1
                }
            } else {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
        }
        return -1
    };
}
// 153. 寻找旋转排序数组中的最小值
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var findMin = function (nums) {
        let l = 0
        let r = nums.length - 1
        while (l <= r) {
            if (l === r) {
                return nums[l]
            }
            if (l + 1 === r) {
                return Math.min(nums[l], nums[r])
            }
            if (nums[l] < nums[r]) {
                return nums[l]
            }
            const mid = l + Math.trunc((r - l) / 2)
            if (nums[l] < nums[mid]) {
                l = mid
            } else {
                r = mid
            }
        }
        return -1
    };
}
// endregion

// region 栈
// 20. 有效的括号
{
    /**
     * @param {string} s
     * @return {boolean}
     */
    var isValid = function (s) {
        if (s.length % 2 === 1) return false
        const stack = []
        for (const c of s) {
            if (c === '(' || c === '[' || c === '{') {
                stack.push(c)
            } else {
                if (stack.length === 0) return false
                const top = stack.pop()
                if (top === '(' && c !== ')') return false
                if (top === '[' && c !== ']') return false
                if (top === '{' && c !== '}') return false
            }
        }
        return stack.length === 0
    };
}

// 155. 最小栈
{

    var MinStack = function () {
        this.data = []
    };

    /**
     * @param {number} val
     * @return {void}
     */
    MinStack.prototype.push = function (val) {
        if (this.data.length === 0) {
            this.data.push({ val, min: val })
        } else {
            const min = Math.min(val, this.data[this.data.length - 1].min)
            this.data.push({ val, min: Math.min(val, min) })
        }
    };

    /**
     * @return {void}
     */
    MinStack.prototype.pop = function () {
        if (this.data.length) {
            this.data.pop()
        }
    };

    /**
     * @return {number}
     */
    MinStack.prototype.top = function () {
        return this.data[this.data.length - 1].val
    };

    /**
     * @return {number}
     */
    MinStack.prototype.getMin = function () {
        return this.data[this.data.length - 1].min
    };
}

// 394. 字符串解码
{
    /**
     * @param {string} s
     * @return {string}
     */
    var decodeString = function (s) {
        const stack = []
        const count = []
        const len = s.length
        let i = 0
        while (i < len) {
            const c = s[i]
            if (isDigit(c)) {
                let curCount = 0
                while (i < len && isDigit(s[i])) {
                    curCount = 10 * curCount + Number(s[i])
                    ++i
                }
                count.push(curCount)
            } else if (isLetter(c)) {
                stack.push(c)
                ++i
            } else if (c === '[') {
                stack.push(c)
                ++i
            } else {
                // c===']'
                let curStr = ""
                while (stack[stack.length - 1] !== '[') {
                    curStr = stack.pop() + curStr
                }
                // pop [
                stack.pop()
                const curCount = count.pop()
                curStr = curStr.repeat(curCount)
                stack.push(curStr)
                ++i
            }
        }
        return stack.join('')
    };

    function isDigit(c) {
        return c.charCodeAt(0) >= '0'.charCodeAt(0) && c.charCodeAt(0) <= '9'.charCodeAt(0)
    }

    function isLetter(c) {
        return c.charCodeAt(0) >= 'a'.charCodeAt(0) && c.charCodeAt(0) <= 'z'.charCodeAt(0)
    }
}

// 739. 每日温度
{
    /**
     * @param {number[]} temperatures
     * @return {number[]}
     */
    var dailyTemperatures = function (temperatures) {
        const n = temperatures.length
        const stack = []
        const res = new Array(n).fill(0)
        for (let i = 0; i < n; ++i) {
            const t = temperatures[i]
            while (stack.length && t > temperatures[stack[stack.length - 1]]) {
                const prevIndex = stack.pop()
                res[prevIndex] = i - prevIndex
            }
            stack.push(i)
        }
        return res
    };
}
// endregion

// region 堆
// 215. 数组中的第K个最大元素
{
    /**
     * @param {number[]} nums
     * @param {number} k
     * @return {number}
     */
    var findKthLargest = function (nums, k) {
        const n = nums.length
        const index = n - k
        let l = 0
        let r = n - 1
        while (l <= r) {
            const { lt, gt } = partition(nums, l, r)
            if (index <= lt) {
                r = lt
            } else if (index >= gt) {
                l = gt
            } else {
                return nums[index]
            }
        }
        return -1
    };

    /**
     *
     * @param {number[]} nums
     * @param {number} l
     * @param {number} r
     * @return {{ lt: number, gt: number }}
     */
    function partition(nums, l, r) {
        const randomIndex = l + Math.floor(Math.random() * (r - l))
        const e = nums[randomIndex]
        let lt = l - 1// nums[l..lt] < e
        let gt = r + 1// nums[gt..r] > e
        let i = l// nums[l..i) === e
        while (i < gt) {
            if (nums[i] < e) {
                ++lt
                swap(nums, lt, i)
                ++i
            } else if (nums[i] > e) {
                --gt
                swap(nums, i, gt)
            } else {
                ++i
            }
        }
        return { lt, gt }
    }

    function swap(nums, i, j) {
        const temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}

// 347. 前k个高频元素
{
    /**
     * @param {number[]} nums
     * @param {number} k
     * @return {number[]}
     */
    var topKFrequent = function (nums, k) {
        const map = new Map()
        for (const num of nums) {
            map.set(num, (map.get(num) || 0) + 1)
        }
        const keys = Array.from(map.keys())
        const index = keys.length - k
        let l = 0
        let r = keys.length - 1
        while (l <= r) {
            const { lt, gt } = partition(keys, l, r, map)
            if (index <= lt) {
                r = lt
            } else if (index >= gt) {
                l = gt
            } else {
                return keys.slice(index)
            }
        }
        return []
    };

    /**
     * @param {number[]} nums
     * @param {number} l
     * @param {number} r
     * @param {Map<number, number>} map
     * @return {{lt:number, gt: number}}
     */
    function partition(nums, l, r, map) {
        const randomIndex = l + Math.floor(Math.random() * (r - l))
        const e = nums[randomIndex]
        let lt = l - 1
        let gt = r + 1
        let i = l
        while (i < gt) {
            if (map.get(nums[i]) < map.get(e)) {
                ++lt
                swap(nums, lt, i)
                ++i
            } else if (map.get(nums[i]) > map.get(e)) {
                --gt
                swap(nums, i, gt)
            } else {
                ++i
            }
        }
        return { lt, gt }
    }

    function swap(nums, i, j) {
        const temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}
// endregion

// region 贪心算法
// 121. 买卖股票的最佳时机
{
    /**
     * @param {number[]} prices
     * @return {number}
     */
    var maxProfit = function (prices) {
        let curMin = prices[0]
        let res = 0
        for (let i = 1; i < prices.length; ++i) {
            res = Math.max(res, prices[i] - curMin)
            curMin = Math.min(curMin, prices[i])
        }
        return res
    };
}

// 55. 跳跃游戏
{
    /**
     * @param {number[]} nums
     * @return {boolean}
     */
    var canJumpV1 = function (nums) {
        const n = nums.length
        const dp = new Array(n).fill(false)
        dp[0] = true
        for (let i = 0; i < n; ++i) {
            if (dp[i]) {
                let j = 1;
                while (j <= nums[i] && i + j < n) {
                    dp[i + j] = true
                    ++j
                }
            }
        }
        return dp[n - 1]
    };
}
{
    /**
     * @param {number[]} nums
     * @return {boolean}
     */
    var canJump = function (nums) {
        const n = nums.length
        let maxPos = 0
        let i = 0
        while (i <= maxPos) {
            maxPos = Math.max(maxPos, i + nums[i])
            if (maxPos >= n - 1) {
                return true
            }
            ++i
        }
        return false
    };
}

// 45. 跳跃游戏II
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var jump = function (nums) {
        const n = nums.length
        const dp = new Array(n).fill(n + 1)
        dp[0] = 0
        for (let i = 0; i < n; ++i) {
            let j = 1;
            while (j <= nums[i] && i + j < n) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1)
                ++j
            }
        }
        return dp[n - 1]
    };
}

// 763. 划分字母区间
{
    /**
     * @param {string} s
     * @return {number[]}
     */
    var partitionLabels = function (s) {
        const lastIndex = new Array(26)
        for (let i = 0; i < s.length; ++i) {
            lastIndex[s[i].charCodeAt(0) - 'a'.charCodeAt(0)] = i
        }
        const n = s.length
        let start = 0
        let end = 0
        let maxIndex = 0
        const ans = []
        while (end < n) {
            maxIndex = Math.max(maxIndex, lastIndex[s[end].charCodeAt(0) - 'a'.charCodeAt(0)])
            if (end === maxIndex) {
                ans.push(end - start + 1)
                start = end + 1
                end = start
            } else {
                ++end
            }
        }
        return ans
    };
}
// endregion

// region 动态规划
// 70. 爬楼梯
{
    /**
     * @param {number} n
     * @return {number}
     */
    var climbStairs = function (n) {
        const dp = []
        dp[0] = dp[1] = 1
        for (let i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    };
}

// 198. 打家劫舍
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var rob = function (nums) {
        const n = nums.length
        if (n === 0) return 0
        if (n === 1) return nums[0]
        const dp = []
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])
        for (let i = 2; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp[n - 1]
    };
}

// 279. 完全平方数
{
    /**
     * @param {number} n
     * @return {number}
     */
    var numSquares = function (n) {
        const dp = new Array(n + 1).fill(n)
        dp[0] = 0
        dp[1] = 1
        for (let i = 2; i <= n; ++i) {
            for (let j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j])
            }
        }
        return dp[n]
    };
}

// 322. 零钱兑换
{
    /**
     * @param {number[]} coins
     * @param {number} amount
     * @return {number}
     */
    var coinChange = function (coins, amount) {
        const dp = new Array(amount + 1).fill(amount + 1)
        dp[0] = 0
        for (let i = 1; i <= amount; ++i) {
            for (const c of coins) {
                if (i >= c) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - c])
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount]
    };
}

// 139. 单词拆分
{
    /**
     * @param {string} s
     * @param {string[]} wordDict
     * @return {boolean}
     */
    var wordBreak = function (s, wordDict) {
        const wordSet = new Set()
        for (const word of wordDict) {
            wordSet.add(word)
        }
        const n = s.length
        // dp[i]表示s[0..i)能否由字典中的单词组成
        const dp = new Array(n + 1).fill(false)
        dp[0] = true
        for (let j = 1; j <= n; ++j) {
            for (let i = 0; i < j; ++i) {
                if (dp[i] && wordSet.has(s.substring(i, j))) {
                    dp[j] = true
                }
            }
        }
        return dp[n]
    };
}

// 300. 最长递增子序列
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var lengthOfLIS = function (nums) {
        const n = nums.length
        // dp[i]表示以nums[i]结尾的递增子序列的最大长度
        const dp = new Array(n).fill(1)
        let res = 0
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i])
        }
        return res
    };
}

// 152. 乘积最大子数组
{
    /**
     * @param {number[]} nums
     * @return {number}
     */
    var maxProduct = function (nums) {
        const n = nums.length
        const maxProduct = new Array(n).fill(0)
        const minProduct = new Array(n).fill(0)
        maxProduct[0] = minProduct[0] = nums[0]
        let res = maxProduct[0]
        for (let i = 1; i < n; ++i) {
            const a = nums[i]
            const b = nums[i] * minProduct[i - 1]
            const c = nums[i] * maxProduct[i - 1]
            maxProduct[i] = Math.max(a, b, c)
            minProduct[i] = Math.min(a, b, c)
            res = Math.max(res, maxProduct[i])
        }
        return res
    };
}

// 416. 分割等和子集
{
    /**
     * @param {number[]} nums
     * @return {boolean}
     */
    var canPartition = function (nums) {
        let sum = 0
        for (const num of nums) {
            sum += num
        }
        if (sum % 2 === 1) return false
        const C = sum / 2
        const n = nums.length
        // dp[i][j]表示从nums[0..i]范围内取若干元素能否使得和等于j
        const dp = new Array(n).fill(null).map(() => new Array(C + 1).fill(false))
        for (let j = 0; j <= C; ++j) {
            dp[0][j] = nums[0] === j
        }
        for (let i = 1; i < n; ++i) {
            for (let j = 0; j <= C; ++j) {
                dp[i][j] = dp[i - 1][j] || (j >= nums[i] && dp[i - 1][j - nums[i]])
            }
        }
        return dp[n - 1][C]
    };
}

// 32. 最长有效括号
{
    /**
     * @param {string} s
     * @return {number}
     */
    function longestValidParentheses(s) {
        const n = s.length
        // dp[i]表示以s[i]结尾的合法子串的最大长度
        const dp = new Array(n).fill(0)
        let res = 0
        for (let i = 1; i < n; ++i) {
            if (s[i] === ')') {
                if (s[i - 1] === '(') {
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0)
                } else {
                    const j = i - dp[i - 1] - 1
                    if (j >= 0 && s[j] === '(') {
                        dp[i] = 2 + dp[i - 1] + (j - 1 >= 0 ? dp[j - 1] : 0)
                    }
                }
                res = Math.max(res, dp[i])
            }
        }
        return res
    }
}
// endregion

// region 多维动态规划
// 5. 最长回文子串
{
    /**
     * @param {string} s
     * @return {string}
     */
    var longestPalindromeV1 = function (s) {
        const n = s.length
        // dp[i][j]表示s[i..j]是否为回文串
        const dp = new Array(n).fill(null).map(() => new Array(n).fill(true))
        for (let i = n - 1; i >= 0; --i) {
            for (let j = i + 1; j < n; ++j) {
                dp[i][j] = s[i] === s[j] && dp[i + 1][j - 1]
            }
        }
        let maxLen = 0
        let res = ""
        for (let i = 0; i < n; ++i) {
            for (let j = i; j < n; ++j) {
                if (dp[i][j]) {
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1
                        res = s.substring(i, j + 1)
                    }
                }
            }
        }
        return res
    };
}
{
    /**
     * @param {string} s
     * @return {string}
     */
    var longestPalindrome = function (s) {
        const n = s.length
        let res = ""
        for (let i = 0; i < n; ++i) {
            const a = findPalindrome(s, i, i)
            const b = findPalindrome(s, i, i + 1)
            if (res.length < a.length) {
                res = a
            }
            if (res.length < b.length) {
                res = b
            }
        }
        return res
    };

    /**
     * @param {string} s
     * @param {number} l
     * @param {number} r
     * @return {string}
     */
    function findPalindrome(s, l, r) {
        if (s[l] !== s[r]) {
            return ""
        }
        while (l >= 0 && r < s.length && s[l] === s[r]) {
            --l
            ++r
        }
        return s.substring(l + 1, r)
    }
}
// endregion

// region 技巧
// endregion