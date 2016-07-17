package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Compresses the input string such that duplicate consecutive
 * characters are replaced by that character followed by
 * the number of occurrences. If the "compressed" string
 * is not shorter than the input string, the input string
 * is returned. This function assumes the string contains
 * ASCII characters (all characters have ordinal value
 * 0 ~ 127).
 */
fun stringCompression(str: String): String {
    val sb = StringBuilder()
    var index = 0
    while (index < str.length) {
        var repeat = 1
        val char = str[index++]
        while (index < str.length && char == str[index]) {
            ++repeat
            ++index
        }
        sb.append(char)
        sb.append(repeat)
    }
    if (sb.length >= str.length) {
        return str
    } else {
        return sb.toString()
    }
}

fun main(args: Array<String>) {
    doAssert(stringCompression("") == "")
    doAssert(stringCompression("a") == "a")
    doAssert(stringCompression("aa") == "aa")
    doAssert(stringCompression("aaa") == "a3")
    doAssert(stringCompression("bobAAAAAAAA") == "b1o1b1A8")
}
