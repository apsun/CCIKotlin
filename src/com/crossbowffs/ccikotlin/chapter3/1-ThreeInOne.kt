package com.crossbowffs.ccikotlin.chapter3

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Represents three integer stacks supporting push and
 * pop operations using a single array as the storage
 * buffer.
 */
class TripleStack() {
    // We use three segments of the array to hold the
    // stack values. Once a segment reaches capacity
    // (equal to total buffer size / 3), the array size
    // is doubled and the old values are copied to the
    // appropriate position in the new buffer. The size
    // of the buffer must be a multiple of 3.
    private var buffer = IntArray(12)
    private val sizes = IntArray(3)

    private fun getRawTopIndex(index: Int): Int {
        return index * buffer.size / 3 + sizes[index] - 1
    }

    fun push(index: Int, value: Int) {
        if (sizes[index] == buffer.size / 3) {
            val newBuffer = IntArray(buffer.size * 2)
            for (i in 0..2) {
                for (j in 0..sizes[i] - 1) {
                    val oldValue = buffer[i * buffer.size / 3 + j]
                    newBuffer[i * newBuffer.size / 3 + j] = oldValue
                }
            }
            buffer = newBuffer
        }
        sizes[index]++
        buffer[getRawTopIndex(index)] = value
    }

    fun peek(index: Int): Int? {
        if (sizes[index] == 0) return null
        return buffer[getRawTopIndex(index)]
    }

    fun pop(index: Int): Int? {
        if (sizes[index] == 0) return null
        val top = buffer[getRawTopIndex(index)]
        sizes[index]--
        return top
    }
}

fun main(args: Array<String>) {
    TripleStack().apply {
        assert(peek(0) == null)
        assert(pop(0) == null)
    }
    TripleStack().apply {
        push(0, 1)
        push(2, 2)
        push(1, 3)
        push(0, 4)
        push(1, 5)
        push(2, 6)
        push(0, 7)
        push(0, 8)
        push(0, 9)
        doAssert(peek(0) == 9)
        doAssert(peek(2) == 6)
        doAssert(peek(1) == 5)
        doAssert(pop(0) == 9)
        doAssert(pop(1) == 5)
        doAssert(pop(1) == 3)
        doAssert(pop(1) == null)
        push(1, 10)
        doAssert(pop(0) == 8)
        doAssert(pop(0) == 7)
        doAssert(pop(2) == 6)
        doAssert(pop(1) == 10)
    }
}
