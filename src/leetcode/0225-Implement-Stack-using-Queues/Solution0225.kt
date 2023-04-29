import java.lang.IllegalArgumentException
import java.util.*

// https://leetcode-cn.com/problems/implement-stack-using-queues/
// 225. Implement Stack using Queues
/*
Implement a last in first out (LIFO) stack using only two queues. The implemented stack should support all the functions
of a normal queue (push, top, pop, and empty).

Implement the MyStack class:
void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.

Notes:
You must use only standard operations of a queue, which means only push to back, peek/pop from front, size, and is empty
operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque
(double-ended queue), as long as you use only a queue's standard operations.
 

Example 1:
Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
 

Constraints:
1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.

Follow-up: Can you implement the stack such that each operation is amortized O(1) time complexity? In other words,
performing n operations will take overall O(n) time even if one of those operations may take longer. You can use more
than two queues.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-stack-using-queues
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class MyStack() {

    /** Initialize your data structure here. */
    private val p: LinkedList<Int> = LinkedList()
    private val q: LinkedList<Int> = LinkedList()

    /** Push element x onto stack. */
    fun push(x: Int) {
        p.offer(x)
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        if (empty()) throw IllegalArgumentException("stack is empty")
        if (p.isEmpty()) {
            while(q.isNotEmpty()) {
                val front = q.poll()
                if (q.isEmpty()) {
                    return front
                } else {
                    p.offer(front)
                }
            }
        } else {
            while(p.isNotEmpty()) {
                val front = p.poll()
                if (p.isEmpty()) {
                    return front
                } else {
                    q.offer(front)
                }
            }
        }
        throw IllegalArgumentException("can not reach this line")
    }

    /** Get the top element. */
    fun top(): Int {
        if (empty()) throw IllegalArgumentException("stack is empty")
        if (p.isEmpty()) {
            while(q.isNotEmpty()) {
                val front = q.poll()
                p.offer(front)
                if (q.isEmpty()) return front
            }
        } else {
            while(p.isNotEmpty()) {
                val front = p.poll()
                q.offer(front)
                if (p.isEmpty()) return front
            }
        }
        throw IllegalArgumentException("can not reach this line")
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return p.isEmpty() && q.isEmpty()
    }

}

fun main() {
    val stack = MyStack()
    stack.push(1)
    stack.push(2)
    println(stack.top())
    println(stack.pop())
    println(stack.empty())
}
/*
执行用时：180 ms, 在所有 Kotlin 提交中击败了79.17%的用户
内存消耗：34.8 MB, 在所有 Kotlin 提交中击败了8.33%的用户
 */