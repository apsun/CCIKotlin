package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Checks if the input string has all unique characters.
 * This function assumes the string contains only ASCII
 * characters (all characters have ordinal value 0 ~ 127).
 */
fun isUnique(str: String): Boolean {
    // Iterate over each character, using a bit vector
    // to track if we've seen a character before. This is
    // more space efficient than an array of 128 booleans.
    val flags = BitSet(128)
    for (char in str) {
        val ord = char.toInt()
        if (flags[ord]) {
            return false
        } else {
            flags[ord] = true
        }
    }
    return true
}

fun main(args: Array<String>) {
    doAssert(isUnique(""))
    doAssert(isUnique("Bob"))
    doAssert(isUnique("nya"))
    doAssert(!isUnique("nyaa"))
}
