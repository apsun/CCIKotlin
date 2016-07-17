package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Converts all spaces in the input string to "%20".
 * The input string must have enough free space at the end
 * to hold the extra characters. This function assumes the
 * string contains ASCII characters (all characters have
 * ordinal value 0 ~ 127).
 */
fun urlify(str: String, trueLength: Int): String {
    // Starting from the end of the string, shift characters
    // to the right while replacing spaces with %20. This way
    // we only perform at most one shift per character. We then
    // take the substring starting from the index of the shifted
    // first character from the original string (this only occurs
    // if the input string had excess capacity).
    if (trueLength == 0) return ""
    var endIndex = str.length
    val chars = str.toCharArray()
    for (i in trueLength - 1 downTo 0) {
        if (chars[i] == ' ') {
            chars[--endIndex] = '0'
            chars[--endIndex] = '2'
            chars[--endIndex] = '%'
        } else {
            chars[--endIndex] = chars[i]
        }
    }
    return String(chars, endIndex, str.length - endIndex)
}

fun main(args: Array<String>) {
    doAssert(urlify("", 0) == "")
    doAssert(urlify("Test", 0) == "")
    doAssert(urlify("A", 1) == "A")
    doAssert(urlify("   ", 1) == "%20")
    doAssert(urlify("This is a URL      ", 13) == "This%20is%20a%20URL")
    doAssert(urlify("This is a URL           ", 13) == "This%20is%20a%20URL")
}
