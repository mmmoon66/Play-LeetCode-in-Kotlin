import java.util.*

// https://leetcode.com/problems/min-stack/
// 155. Min Stack
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */
class MinStack() {

    /** initialize your data structure here. */
    private val stack = Stack<Pair<Int, Int>>()


    fun push(x: Int) {
        if (stack.isEmpty()) {
            stack.push(Pair(x, x))
        } else {
            val top = stack.peek()
            stack.push(Pair(x, minOf(x, top.second)))
        }
    }

    fun pop() {
        if (stack.isNotEmpty()) {
            stack.pop()
        }
    }

    fun top(): Int {
        return stack.peek().first
    }

    fun getMin(): Int {
        return stack.peek().second
    }
}
/*
Runtime: 204 ms, faster than 92.65% of Kotlin online submissions for Min Stack.
Memory Usage: 52.4 MB, less than 100.00% of Kotlin online submissions for Min Stack.
 */