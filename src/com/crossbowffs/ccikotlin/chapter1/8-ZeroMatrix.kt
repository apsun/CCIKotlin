package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.arrayEquals
import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

/**
 * Zeroes all rows and columns in the input matrix that have
 * zero as an element.
 */
fun zeroMatrix(matrix: Array<IntArray>) {
    // We have to determine which rows and columns
    // to zero before actually performing the action,
    // or else our changes will cascade to other rows
    // and columns.
    val height = matrix.size
    if (height == 0) return
    val width = matrix[0].size
    if (width == 0) return
    val zeroRowFlags = BitSet(height)
    val zeroColFlags = BitSet(width)
    for (i in 0..height - 1) {
        for (j in 0..width - 1) {
            if (matrix[i][j] == 0) {
                zeroRowFlags[i] = true
                zeroColFlags[j] = true
            }
        }
    }
    for (i in 0..height - 1) {
        for (j in 0..width - 1) {
            if (zeroRowFlags[i] || zeroColFlags[j]) {
                matrix[i][j] = 0
            }
        }
    }
}

fun main(args: Array<String>) {
    emptyArray<IntArray>().apply {
        zeroMatrix(this)
        doAssert(arrayEquals(emptyArray<IntArray>()))
    }
    arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 0, 8),
        intArrayOf(9, 10, 11, 12)
    ).apply {
        zeroMatrix(this)
        doAssert(arrayEquals(arrayOf(
            intArrayOf(1, 2, 0, 4),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(9, 10, 0, 12)
        )))
    }
}
