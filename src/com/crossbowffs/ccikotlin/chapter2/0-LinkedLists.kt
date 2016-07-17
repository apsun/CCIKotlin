package com.crossbowffs.ccikotlin.chapter2

data class LinkedNode<T>(var data: T) {
    var next: LinkedNode<T>? = null
}

fun <T> getNthNode(head: LinkedNode<T>?, n: Int): LinkedNode<T>? {
    var curr = head
    for (i in 1..n) {
        if (curr == null) return null
        curr = curr.next
    }
    return curr
}

fun <T> getLength(head: LinkedNode<T>?): Int {
    var n = 0
    var curr = head
    while (curr != null) {
        n++
        curr = curr.next
    }
    return n
}

fun <T> linkedListOf(vararg elems: T): LinkedNode<T>? {
    if (elems.size == 0) return null
    val head = LinkedNode(elems[0])
    var tail = head
    for (i in 1..elems.lastIndex) {
        val node = LinkedNode(elems[i])
        tail.next = node
        tail = node
    }
    return head
}

fun <T> LinkedNode<T>?.linkedListEquals(vararg elems: T): Boolean {
    var curr = this
    for (i in 0..elems.lastIndex) {
        if (curr == null) {
            return false
        }
        if (curr.data != elems[i]) {
            return false
        }
        curr = curr.next
    }
    return curr == null
}
