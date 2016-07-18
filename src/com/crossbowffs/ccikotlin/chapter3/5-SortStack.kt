package com.crossbowffs.ccikotlin.chapter3

import com.crossbowffs.ccikotlin.utils.arrayEquals
import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Sorts the input stack using a single temporary stack.
 * The smallest element will be on the top of the stack,
 * the largest element will be on the bottom.
 */
fun sortStack(stack: ArrayDeque<Int>) {
    // To sort the elements in the stack, we construct a
    // temporary stack to transfer elements to in reverse
    // sorted order, then "flip" the temporary stack by popping
    // all elements from it back into the original stack.
    // To maintain (reverse) sorted order in the temporary
    // stack, we first pop the element from the original
    // stack, then transfer elements from the temporary
    // stack back into the original stack until we find
    // the correct position to insert the element into.
    // These elements will then be re-transfered into the
    // temporary stack in subsequent iterations of the loop.
    val temp = ArrayDeque<Int>()
    while (stack.isNotEmpty()) {
        val top = stack.pop()
        while (temp.isNotEmpty() && top < temp.peek()) {
            stack.push(temp.pop())
        }
        temp.push(top)
    }
    while (temp.isNotEmpty()) {
        stack.push(temp.pop())
    }
}

fun main(args: Array<String>) {
    ArrayDeque<Int>().apply {
        sortStack(this)
        doAssert(isEmpty())
    }
    ArrayDeque<Int>().apply {
        push(1)
        push(5)
        push(10)
        push(2)
        push(10)
        push(0)
        sortStack(this)
        doAssert(toIntArray().arrayEquals(intArrayOf(0, 1, 2, 5, 10, 10)))
    }
    ArrayDeque<Int>().apply {
        push(1)
        push(1)
        push(1)
        sortStack(this)
        doAssert(toIntArray().arrayEquals(intArrayOf(1, 1, 1)))
    }
}
