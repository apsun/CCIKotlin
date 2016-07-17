package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Finds the intersection point of two linked lists.
 * Returns null if the two lists do not intersect.
 */
fun intersection(a: LinkedNode<Int>?, b: LinkedNode<Int>?): LinkedNode<Int>? {
    // To find the intersection point (if there is any),
    // we need to align the two lists such that both have
    // the same length. Then, we iterate both lists in
    // parallel until the lists merge, which is the
    // intersection point. If we reach the end of either
    // list, they do not intersect.
    val lenA = getLength(a)
    val lenB = getLength(b)
    var (shorter, longer) = if (lenA < lenB) {
        a to getNthNode(b, lenB - lenA)
    } else {
        b to getNthNode(a, lenA - lenB)
    }
    while (longer != null && longer != shorter) {
        longer = longer.next
        shorter = shorter!!.next
    }
    return longer
}

fun main(args: Array<String>) {
    linkedListOf<Int>().apply {
        val other = linkedListOf<Int>()
        doAssert(intersection(this, other) == null)
    }
    linkedListOf(1).apply {
        val other = linkedListOf(2)
        doAssert(intersection(this, other) == null)
    }
    linkedListOf(1, 2, 3, 4, 5).apply {
        val other = linkedListOf(7, 8, 9, 10)
        getNthNode(other, 3)!!.next = getNthNode(this, 3)
        doAssert(intersection(this, other)!!.data == 4)
    }
    linkedListOf(1, 2, 3).apply {
        doAssert(intersection(this, this) == this)
    }
}
