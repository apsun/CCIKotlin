package com.crossbowffs.ccikotlin.chapter3

import java.util.*

/**
 * A stack with the ability to peek at the minimum
 * element in the stack in constant-time.
 */
class StackWithMin<T: Comparable<T>>() {
    // We use a secondary stack to hold the minimum
    // values. When an element with the smallest value
    // seen so far is pushed onto the stack, it is also
    // pushed onto the "min stack". When that value is
    // popped from the stack, it is also popped from
    // the min stack. The top of the min stack holds
    // the current minimum value in the stack.
    val all = ArrayDeque<T>()
    val mins = ArrayDeque<T>()

    fun push(value: T) {
        all.push(value)
        if (mins.isEmpty() || value <= mins.peek()) {
            mins.push(value)
        }
    }

    fun min(): T? {
        return mins.peek()
    }

    fun peek(): T? {
        return all.peek()
    }

    fun pop(): T? {
        if (all.isEmpty()) return null
        val top = all.pop()
        if (top == mins.peek()) {
            mins.pop()
        }
        return top
    }
}

fun main(args: Array<String>) {
    StackWithMin<Int>().apply {
        assert(peek() == null)
        assert(min() == null)
        assert(pop() == null)
    }
    StackWithMin<Int>().apply {
        push(1)
        push(2)
        push(1)
        push(0)
        push(3)
        assert(min() == 0)
        assert(pop() == 3)
        assert(pop() == 0)
        assert(min() == 1)
        assert(pop() == 1)
        assert(min() == 1)
        assert(peek() == 2)
    }
}
