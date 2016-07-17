package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Removes all duplicate values from the linked list.
 */
fun removeDups(head: LinkedNode<Int>?) {
    // Use a HashSet to keep track of which values
    // we've seen already.
    if (head == null) return
    val seen = HashSet<Int>()
    var prev: LinkedNode<Int> = head
    var curr = head
    while (curr != null) {
        if (!seen.add(curr.data)) {
            prev.next = curr.next
        } else {
            prev = curr
        }
        curr = curr.next
    }
}

/**
 * Removes all duplicate values from the linked list.
 * This function runs slower than removeDups() but
 * requires only constant space.
 */
fun removeDupsNoTemp(head: LinkedNode<Int>?) {
    // This version requires no additional space, but
    // has to iterate over the list multiple times.
    var curr = head
    while (curr != null) {
        var test = curr
        while (test != null) {
            if (test.next?.data == test.data) {
                test.next = test.next?.next
            } else {
                test = test.next
            }
        }
        curr = curr.next
    }
}

fun main(args: Array<String>) {
    arrayOf(::removeDups, ::removeDupsNoTemp).forEach {
        linkedListOf<Int>().apply {
            it(this)
            doAssert(linkedListEquals())
        }
        linkedListOf(1, 2, 3, 4, 5).apply {
            it(this)
            doAssert(linkedListEquals(1, 2, 3, 4, 5))
        }
        linkedListOf(1, 2, 2, 3, 4).apply {
            it(this)
            doAssert(linkedListEquals(1, 2, 3, 4))
        }
        linkedListOf(1, 1, 1, 1, 1).apply {
            it(this)
            doAssert(linkedListEquals(1))
        }
        linkedListOf(1, 1, 2, 2, 2).apply {
            it(this)
            doAssert(linkedListEquals(1, 2))
        }
    }
}
