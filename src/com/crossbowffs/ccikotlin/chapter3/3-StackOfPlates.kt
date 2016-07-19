package com.crossbowffs.ccikotlin.chapter3

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Represents multiple stacks, each with a particular
 * size limit. If a stack becomes full, a new internal
 * stack is created to hold the new elements.
 */
class MultiStack<T>(val sizeLimit: Int) {
    private val stacks = arrayListOf(ArrayDeque<T>())

    fun push(value: T) {
        if (stacks.last().size == sizeLimit) {
            stacks.add(ArrayDeque())
        }
        stacks.last().push(value)
    }

    fun peek(): T? {
        return peekAt(stacks.lastIndex)
    }

    fun peekAt(index: Int): T? {
        if (index >= stacks.size) return null
        return stacks[index].peek()
    }

    fun pop(): T? {
        return popAt(stacks.lastIndex)
    }

    fun popAt(index: Int): T? {
        if (index >= stacks.size) return null
        if (stacks[index].isEmpty()) return null
        val top = stacks[index].pop()
        while (stacks.size > 1 && stacks.last().isEmpty()) {
            stacks.removeAt(stacks.lastIndex)
        }
        return top
    }
}

fun main(args: Array<String>) {
    MultiStack<Int>(2).apply {
        assert(peek() == null)
        assert(pop() == null)
    }
    MultiStack<Int>(2).apply {
        push(1)
        push(2)
        push(3)
        push(4)
        push(5)
        doAssert(pop() == 5)
        doAssert(popAt(2) == null)
        doAssert(peekAt(1) == 4)
        doAssert(pop() == 4)
        doAssert(popAt(0) == 2)
        doAssert(pop() == 3)
        doAssert(peek() == 1)
        doAssert(pop() == 1)
        doAssert(peek() == null)
        doAssert(pop() == null)
        push(6)
        push(7)
        push(8)
        doAssert(popAt(1) == 8)
    }
    MultiStack<Int>(2).apply {
        push(1)
        push(2)
        push(3)
        doAssert(popAt(0) == 2)
        doAssert(popAt(0) == 1)
        doAssert(popAt(0) == null)
        doAssert(pop() == 3)
        doAssert(pop() == null)
        push(1)
        doAssert(popAt(1) == null)
        doAssert(pop() == 1)
    }
}
