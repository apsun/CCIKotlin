package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Checks whether the input strings are rotations of
 * each other - that is, it is possible to form a string
 * by moving some of the characters from the head to the
 * tail of the other string.
 */
fun stringRotation(s1: String, s2: String): Boolean {
    // Let X+Y be the first string, and Y+X be the second string.
    // When we concatenate the first string to itself, we get
    // X+Y+X+Y. The second string must therefore be a substring
    // of this string.
    return s1.length == s2.length && (s1 + s1).contains(s2)
}

fun main(args: Array<String>) {
    doAssert(stringRotation("", ""))
    doAssert(stringRotation("banana", "nanaba"))
    doAssert(stringRotation("nyan", "nyan"))
    doAssert(!stringRotation("bob", "car"))
    doAssert(!stringRotation("bob", "bobbob"))
    doAssert(!stringRotation("bobbob", "bob"))
}
