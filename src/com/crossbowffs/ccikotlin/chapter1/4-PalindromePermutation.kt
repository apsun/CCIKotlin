package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Checks whether the input string can be rearranged to form
 * a palindrome. This function assumes the string contains ASCII
 * characters (all characters have ordinal value 0 ~ 127).
 */
fun palindromePermutation(str: String): Boolean {
    // We need to check if the string has at most one "odd"
    // character, which will form the middle of the palindrome
    // string. This is done by toggling the corresponding flag
    // in the bit vector -- if the bit is set, there is an
    // odd number of that character in the string.
    val flags = BitSet(128)
    for (char in str) {
        val ord = char.toInt()
        flags.flip(ord)
    }
    return flags.cardinality() <= 1
}

fun main(args: Array<String>) {
    doAssert(palindromePermutation(""))
    doAssert(palindromePermutation("dog dog"))
    doAssert(palindromePermutation("aaaa"))
    doAssert(palindromePermutation("aabaa"))
    doAssert(!palindromePermutation("ab"))
    doAssert(!palindromePermutation("ab"))
    doAssert(!palindromePermutation("random words"))
}
