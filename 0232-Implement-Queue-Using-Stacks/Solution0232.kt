import java.util.*

// https://leetcode.com/problems/implement-queue-using-stacks/
/*
Implement the following operations of a queue using stacks.
push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Example:

MyQueue queue = new MyQueue();
queue.push(1);
queue.push(2);
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false

Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
class MyQueue() {

    /** Initialize your data structure here. */
    val input = Stack<Int>()
    var output = Stack<Int>()


    /** Push element x to the back of queue. */
    fun push(x: Int) {
        input.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        peek()
        return output.pop()
    }

    /** Get the front element. */
    fun peek(): Int {
        if (output.isEmpty()) {
            while(input.isNotEmpty()) {
                output.push(input.pop())
            }
        }
        return output.peek()
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return input.isEmpty() && output.isEmpty()
    }
}

fun main() {
    val queue = MyQueue()
    queue.push(1)
    queue.push(2)
    queue.push(3)
    println(queue.peek())
    queue.push(4)
    println(queue.pop())
}
/*
Runtime: 204 ms, faster than 6.90% of Kotlin online submissions for Implement Queue using Stacks.
Memory Usage: 35.3 MB, less than 7.14% of Kotlin online submissions for Implement Queue using Stacks.
 */