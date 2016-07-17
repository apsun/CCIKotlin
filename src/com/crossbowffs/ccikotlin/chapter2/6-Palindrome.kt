package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Checks whether the given linked list is a palindrome --
 * that is, the first node is equal to the last node, the
 * second node is equal to the node before the last node, etc.
 */
fun palindrome(head: LinkedNode<Int>?): Boolean {
    // We iterate the list until we reach the middle node,
    // pushing each element as we go onto a stack. After
    // reaching the middle node, we keep iterating while
    // comparing the current element with the top of the stack.
    // If all elements pair up, the list forms a palindrome.
    // We need to use a "front" and "back" pointer to determine
    // the middle node -- once the back pointer is null, the
    // front pointer is at the middle.
    val stack = ArrayDeque<Int>()
    var front = head
    var back = head
    while (back != null && back.next != null) {
        stack.push(front!!.data)
        front = front.next
        back = back.next!!.next
    }
    if (back != null) {
        front = front!!.next
    }
    while (front != null) {
        if (stack.pop() != front.data) {
            return false
        }
        front = front.next
    }
    return true
}

fun main(args: Array<String>) {
    doAssert(palindrome(linkedListOf()))
    doAssert(palindrome(linkedListOf(1)))
    doAssert(palindrome(linkedListOf(1, 1)))
    doAssert(palindrome(linkedListOf(1, 2, 2, 1)))
    doAssert(palindrome(linkedListOf(1, 2, 3, 2, 1)))
    doAssert(!palindrome(linkedListOf(1, 2)))
    doAssert(!palindrome(linkedListOf(1, 2, 3, 1)))
}
