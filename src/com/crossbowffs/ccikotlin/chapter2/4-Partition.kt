package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Partitions a linked list such that all elements
 * smaller than the pivot come before all elements
 * greater than or equal to the pivot. Returns the
 * new head of the list.
 */
fun partition(head: LinkedNode<Int>?, pivot: Int): LinkedNode<Int>? {
    // Simply iterate through the list and append the
    // node to either the "left" or "right" lists as
    // necessary. At the end, we join the left and right
    // lists together (making sure to reset the tail
    // pointer).
    var leftStart: LinkedNode<Int>? = null
    var leftEnd: LinkedNode<Int>? = null
    var rightStart: LinkedNode<Int>? = null
    var rightEnd: LinkedNode<Int>? = null
    var curr = head
    while (curr != null) {
        val next = curr.next
        if (curr.data < pivot) {
            if (leftEnd == null) {
                leftStart = curr
                leftEnd = curr
            } else {
                leftEnd.next = curr
                leftEnd = curr
            }
        } else {
            if (rightEnd == null) {
                rightStart = curr
                rightEnd = curr
            } else {
                rightEnd.next = curr
                rightEnd = curr
            }
        }
        curr = next
    }
    rightEnd?.next = null
    if (leftEnd == null) {
        return rightStart
    } else {
        leftEnd.next = rightStart
        return leftStart
    }
}

fun main(args: Array<String>) {
    partition(linkedListOf<Int>(), 0).apply {
        doAssert(linkedListEquals())
    }
    partition(linkedListOf(5, 4, 3, 2, 1), 3).apply {
        doAssert(linkedListEquals(2, 1, 5, 4, 3))
    }
    partition(linkedListOf(3, 3, 3), 3).apply {
        doAssert(linkedListEquals(3, 3, 3))
    }
    partition(linkedListOf(1, 2, 3, 4, 5), 0).apply {
        doAssert(linkedListEquals(1, 2, 3, 4, 5))
    }
    partition(linkedListOf(1, 2, 3, 4, 5), 6).apply {
        doAssert(linkedListEquals(1, 2, 3, 4, 5))
    }
    partition(linkedListOf(1), 1).apply {
        doAssert(linkedListEquals(1))
    }
}
