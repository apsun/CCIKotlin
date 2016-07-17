package com.crossbowffs.ccikotlin.utils

import java.util.*

fun ByteArray.arrayEquals(other: ByteArray) = Arrays.equals(this, other)
fun BooleanArray.arrayEquals(other: BooleanArray) = Arrays.equals(this, other)
fun CharArray.arrayEquals(other: CharArray) = Arrays.equals(this, other)
fun DoubleArray.arrayEquals(other: DoubleArray) = Arrays.equals(this, other)
fun FloatArray.arrayEquals(other: FloatArray) = Arrays.equals(this, other)
fun IntArray.arrayEquals(other: IntArray) = Arrays.equals(this, other)
fun LongArray.arrayEquals(other: LongArray) = Arrays.equals(this, other)
fun ShortArray.arrayEquals(other: ShortArray) = Arrays.equals(this, other)
fun Array<*>.arrayEquals(other: Array<*>) = Arrays.deepEquals(this, other)

fun ByteArray.arrayToString() = Arrays.toString(this)!!
fun BooleanArray.arrayToString() = Arrays.toString(this)!!
fun CharArray.arrayToString() = Arrays.toString(this)!!
fun DoubleArray.arrayToString() = Arrays.toString(this)!!
fun FloatArray.arrayToString() = Arrays.toString(this)!!
fun IntArray.arrayToString() = Arrays.toString(this)!!
fun LongArray.arrayToString() = Arrays.toString(this)!!
fun ShortArray.arrayToString() = Arrays.toString(this)!!
fun Array<*>.arrayToString() = Arrays.toString(this)!!
