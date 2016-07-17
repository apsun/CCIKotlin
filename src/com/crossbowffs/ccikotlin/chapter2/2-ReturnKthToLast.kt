package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Returns the k-th to last node in the linked list.
 * k = 0 returns the last node, k = 1 the node before
 * the last, etc. If there are not enough elements in
 * the list, null is returned.
 */
fun returnKthToLast(head: LinkedNode<Int>?, k: Int): LinkedNode<Int>? {
    // We first advance the back node k nodes, then
    // advance both front and back in parallel. Once the
    // back node becomes null, the front node is k nodes
    // away from the end.
    var front = head
    var back = head
    for (i in 0..k) {
        if (back == null) return null
        back = back.next
    }
    while (back != null) {
        back = back.next
        front = front!!.next
    }
    return front
}

fun main(args: Array<String>) {
    returnKthToLast(linkedListOf<Int>(), 1).apply {
        doAssert(this == null)
    }
    returnKthToLast(linkedListOf(1, 2, 3), 5).apply {
        doAssert(this == null)
    }
    returnKthToLast(linkedListOf(1, 2, 3, 4, 5), 0).apply {
        doAssert(this!!.data == 5)
    }
    returnKthToLast(linkedListOf(1, 2, 3, 4, 5), 1).apply {
        doAssert(this!!.data == 4)
    }
    returnKthToLast(linkedListOf(1, 2, 3, 4, 5), 4).apply {
        doAssert(this!!.data == 1)
    }
}
