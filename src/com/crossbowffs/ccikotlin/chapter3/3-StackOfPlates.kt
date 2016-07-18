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
    private var currIndex = 0

    fun push(value: T) {
        if (stacks[currIndex].size == sizeLimit) {
            stacks.add(ArrayDeque())
            currIndex++
        }
        stacks[currIndex].push(value)
    }

    fun peek(): T? {
        return peekAt(currIndex)
    }

    fun peekAt(index: Int): T? {
        if (index > currIndex) return null
        return stacks[index].peek()
    }

    fun pop(): T? {
        return popAt(currIndex)
    }

    fun popAt(index: Int): T? {
        if (index > currIndex) return null
        if (stacks[index].isEmpty()) return null
        val top = stacks[index].pop()
        while (currIndex != 0 && stacks[currIndex].isEmpty()) {
            stacks.removeAt(currIndex--)
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
}
