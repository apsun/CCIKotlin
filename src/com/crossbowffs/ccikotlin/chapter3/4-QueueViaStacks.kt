package com.crossbowffs.ccikotlin.chapter3

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Represents a queue implemented using two stacks.
 */
class QueueViaStacks<T>() {
    // Incoming elements are pushed onto the "main" stack,
    // and outgoing elements are popped from the "off" stack.
    // When peeking or dequeueing, we transfer all elements
    // from the main to the off stack if the off stack is empty.
    // This way, the order is reversed such that the first
    // element added is at the top of the "off" stack, the second
    // element added is below that, etc.
    private val main = ArrayDeque<T>()
    private val off = ArrayDeque<T>()

    fun enqueue(value: T) {
        main.push(value)
    }

    private fun transferIfNecessary() {
        if (off.isEmpty()) {
            while (main.isNotEmpty()) {
                off.push(main.pop())
            }
        }
    }

    fun peek(): T? {
        transferIfNecessary()
        return off.peek()
    }

    fun dequeue(): T? {
        transferIfNecessary()
        return if (off.isNotEmpty()) off.pop() else null
    }
}

fun main(args: Array<String>) {
    QueueViaStacks<Int>().apply {
        assert(peek() == null)
        assert(dequeue() == null)
    }
    QueueViaStacks<Int>().apply {
        enqueue(10)
        enqueue(5)
        doAssert(peek() == 10)
        doAssert(dequeue() == 10)
        enqueue(7)
        doAssert(dequeue() == 5)
        doAssert(dequeue() == 7)
        enqueue(1)
        doAssert(dequeue() == 1)
        doAssert(peek() == null)
        doAssert(dequeue() == null)
    }
}
