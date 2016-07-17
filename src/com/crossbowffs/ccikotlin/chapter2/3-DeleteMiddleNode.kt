package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Deletes the given node from a linked list containing
 * it. The node must not be the last node in the list.
 */
fun deleteMiddleNode(node: LinkedNode<Int>) {
    // We delete the node by replacing its data with
    // the contents of the next node, then deleting
    // the next node from the list. This does not work
    // if the node is at the end of the list.
    node.data = node.next!!.data
    node.next = node.next!!.next
}

fun main(args: Array<String>) {
    linkedListOf(1, 2, 3)!!.apply {
        deleteMiddleNode(this)
        doAssert(linkedListEquals(2, 3))
    }
}
