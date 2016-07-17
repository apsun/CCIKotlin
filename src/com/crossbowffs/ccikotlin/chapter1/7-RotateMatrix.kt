package com.crossbowffs.ccikotlin.chapter1

import com.crossbowffs.ccikotlin.utils.arrayEquals
import com.crossbowffs.ccikotlin.utils.doAssert

/**
 * Rotates the input square matrix by 90 degrees clockwise,
 * such that the element at [0][0] is moved to [0][N-1],
 * the element at [0][1] is moved to [1][N-1], and so forth.
 * This function modifies the input matrix in-place.
 */
fun rotateMatrix(matrix: Array<IntArray>) {
    // Rotation can be performed by transposing
    // the matrix, then reversing each column.
    val dimMinus1 = matrix.lastIndex
    for (i in 0..dimMinus1) {
        for (j in i..dimMinus1) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }
    for (i in 0..dimMinus1) {
        for (j in 0..dimMinus1 / 2) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[i][dimMinus1 - j]
            matrix[i][dimMinus1 - j] = temp
        }
    }
}

fun main(args: Array<String>) {
    emptyArray<IntArray>().apply {
        rotateMatrix(this)
        doAssert(arrayEquals(emptyArray<IntArray>()))
    }
    arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    ).apply {
        rotateMatrix(this)
        doAssert(arrayEquals(arrayOf(
            intArrayOf(7, 4, 1),
            intArrayOf(8, 5, 2),
            intArrayOf(9, 6, 3)
        )))
    }
    arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12),
        intArrayOf(13, 14, 15, 16)
    ).apply {
        rotateMatrix(this)
        doAssert(arrayEquals(arrayOf(
            intArrayOf(13, 9, 5, 1),
            intArrayOf(14, 10, 6, 2),
            intArrayOf(15, 11, 7, 3),
            intArrayOf(16, 12, 8, 4)
        )))
    }
}
