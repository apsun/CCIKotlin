package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Checks if the two input strings are permutations of each
 * other. This function assumes the string contains ASCII
 * characters (all characters have ordinal value 0 ~ 127).
 */
fun checkPermutation(a: String, b: String): Boolean {
    // We use a dictionary to count the number of occurrences
    // of each character in the first string, then compare
    // this to the number of occurrences in the second string.
    if (a.length != b.length) return false
    val counts = HashMap<Char, Int>()
    for (char in a) {
        counts[char] = counts.getOrDefault(char, 0) + 1
    }
    for (char in b) {
        val count = counts.getOrDefault(char, 0)
        if (count == 0) return false
        counts[char] = count - 1
    }
    return counts.values.all { it == 0 }
}

fun main(args: Array<String>) {
    doAssert(checkPermutation("", ""))
    doAssert(checkPermutation("asdf", "sfda"))
    doAssert(checkPermutation("111", "111"))
    doAssert(!checkPermutation("111", "1111"))
    doAssert(!checkPermutation("", "111"))
    doAssert(!checkPermutation("111", ""))
}
