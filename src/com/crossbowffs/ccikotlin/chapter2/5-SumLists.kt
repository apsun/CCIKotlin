package com.crossbowffs.ccikotlin.chapter2

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Calculates the sum of a linked list of decimal digits.
 * The least significant digit comes first, i.e. 1->2->3
 * is equal to 321. The result is returned in a new linked
 * list with the same format. If the result equals zero,
 * null is returned.
 */
fun sumListsReverse(a: LinkedNode<Int>?, b: LinkedNode<Int>?): LinkedNode<Int>? {
    // This version uses a recursive full-adder algorithm to
    // add each digit together. We can begin "merging" the nodes
    // immediately since the least significant digit is at the
    // beginning. This version is not limited by the size of the
    // integer, since the digits are added together directly.
    fun helper(a: LinkedNode<Int>?, b: LinkedNode<Int>?, carry: Int): LinkedNode<Int>? {
        if (a == null && b == null && carry == 0) return null
        var sum = carry
        if (a != null) sum += a.data
        if (b != null) sum += b.data
        val next = helper(a?.next, b?.next, sum / 10)
        if (next == null && sum == 0) {
            return null
        }
        val node = LinkedNode(sum % 10)
        node.next = next
        return node
    }
    return helper(a, b, 0)
}

/**
 * Calculates the sum of a linked list of decimal digits.
 * The most significant digit comes first, i.e. 1->2->3
 * is equal to 123. The result is returned in a new linked
 * list with the same format. If the result equals zero,
 * null is returned.
 */
fun sumListsForward(a: LinkedNode<Int>?, b: LinkedNode<Int>?): LinkedNode<Int>? {
    // This version converts each list to an integer, adds the
    // results, then converts the sum into a new list. This is
    // necessary since we don't know the length of each list
    // in advance, so we cannot know whether the digits of the
    // two lists are aligned or not without first iterating the
    // entire list. This version is limited by the size of
    // an integer (so arbitrary-precision addition is not possible).
    fun listToInt(node: LinkedNode<Int>?): Pair<Int, Int> {
        if (node == null) return 0 to 1
        val (digit, base) = listToInt(node.next)
        return (node.data * base + digit) to (base * 10)
    }
    var sum = listToInt(a).first + listToInt(b).first
    var prev: LinkedNode<Int>? = null
    while (sum != 0) {
        val curr = LinkedNode(sum % 10)
        sum /= 10
        curr.next = prev
        prev = curr
    }
    return prev
}

fun main(args: Array<String>) {
    sumListsReverse(linkedListOf(), linkedListOf()).apply {
        doAssert(linkedListEquals())
    }
    sumListsReverse(linkedListOf(), linkedListOf(0)).apply {
        doAssert(linkedListEquals())
    }
    sumListsReverse(linkedListOf(1, 2, 3), linkedListOf(9, 9, 9)).apply {
        doAssert(linkedListEquals(0, 2, 3, 1))
    }
    sumListsReverse(linkedListOf(1, 2), linkedListOf(9, 9, 0, 0)).apply {
        doAssert(linkedListEquals(0, 2, 1))
    }

    sumListsForward(linkedListOf(), linkedListOf()).apply {
        doAssert(linkedListEquals())
    }
    sumListsForward(linkedListOf(), linkedListOf(0)).apply {
        doAssert(linkedListEquals())
    }
    sumListsForward(linkedListOf(1, 2, 3), linkedListOf(9, 9, 9)).apply {
        doAssert(linkedListEquals(1, 1, 2, 2))
    }
    sumListsForward(linkedListOf(0, 0, 0, 1, 2), linkedListOf(9, 9, 0, 0)).apply {
        doAssert(linkedListEquals(9, 9, 1, 2))
    }
}
