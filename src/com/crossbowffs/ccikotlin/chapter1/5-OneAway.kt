package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Checks whether either input string can be changed into
 * the other string with a single edit, i.e. a one-character
 * replacement, insertion, or deletion. This function assumes
 * the string contains ASCII characters (all characters have
 * ordinal value 0 ~ 127).
 */
fun oneAway(a: String, b: String): Boolean {
    // If the strings differ by more than 1 character, they
    // cannot be formed with a single mutation. We look for
    // the first position at which the two strings differ;
    // there are two cases to consider:
    //
    // 1. If the two strings have the same length, this must
    //    be a replacement. Check that the rest of the strings
    //    are equal.
    //
    // 2. If the two strings have different lengths, this
    //    must be an insertion in the longer string (or a
    //    deletion in the shorter string, it doesn't matter).
    //    Check the rest of the strings, including the current
    //    character from the shorter string.
    //
    // If the end of the shorter string is reached, the longer
    // string has one extra character at the end and can be
    // formed from insertion.
    val (shorter, longer) = if (a.length < b.length) a to b else b to a
    if (longer.length - shorter.length > 1) return false
    val index = shorter.indices.firstOrNull { shorter[it] != longer[it] }
    if (index != null) {
        if (shorter.length == longer.length) {
            return shorter.substring(index + 1) == longer.substring(index + 1)
        } else {
            return shorter.substring(index) == longer.substring(index + 1)
        }
    }
    return true
}

fun main(args: Array<String>) {
    doAssert(oneAway("", ""))
    doAssert(oneAway("", "A"))
    doAssert(oneAway("test", "test"))
    doAssert(oneAway("blah", "bleh"))
    doAssert(oneAway("blah", "bla"))
    doAssert(oneAway("end", "ends"))
    doAssert(!oneAway("not even", "close"))
    doAssert(!oneAway("blah", "ble"))
}
