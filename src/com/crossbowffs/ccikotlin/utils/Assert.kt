package com.crossbowffs.ccikotlin.utils

fun doAssert(cond: Boolean) {
    if (!cond) {
        throw AssertionError("Assertion failed")
    }
}
