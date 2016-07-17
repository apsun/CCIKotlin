package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Returns the starting point of a loop in the linked
 * list, if one exists, or null if there is no loop.
 */
fun loopDetection(head: LinkedNode<Int>?): LinkedNode<Int>? {
    // To determine if there is a loop, we use
    // a "fast" and a "slow" pointer to iterate the
    // list. If there is a loop, at some point the
    // fast and slow pointers will point to the
    // same node.
    var front = head
    var back = head
    while (back != null) {
        front = front!!.next
        back = back.next?.next
        if (front == back) {
            break
        }
    }
    if (back == null) return null
    back = head
    while (back != front) {
        front = front!!.next
        back = back!!.next
    }
    return back
}

fun main(args: Array<String>) {
    linkedListOf<Int>().apply {
        doAssert(loopDetection(this) == null)
    }
    linkedListOf(1, 2, 3, 4, 5).apply {
        doAssert(loopDetection(this) == null)
    }
    linkedListOf(1, 1).apply {
        doAssert(loopDetection(this) == null)
    }
    linkedListOf(1, 2, 3, 4).apply {
        getNthNode(this, 3)!!.next = this
        doAssert(loopDetection(this) != null)
    }
    linkedListOf(1, 2, 3, 4, 5).apply {
        getNthNode(this, 4)!!.next = this!!.next
        doAssert(loopDetection(this) == this.next)
    }
    linkedListOf(1, 2, 3, 4, 5).apply {
        getNthNode(this, 3)!!.next = this!!.next
        doAssert(loopDetection(this) == this.next)
    }
    linkedListOf(1).apply {
        this!!.next = this
        doAssert(loopDetection(this) == this)
    }
}
