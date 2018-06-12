package ru.kizup.musicplayer.utils

import android.graphics.Color

/**
 * Created by: kizup on 10.06.18.
 * Skype: kizupx
 */

fun getBlackWhiteColor(color: Int): Int {
    val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
    return if (darkness >= 0.5) {
        Color.WHITE
    } else
        Color.BLACK
}

fun getStatusBarColor(primaryColor: Int): Int {
    val arrayOfFloat = FloatArray(3)
    Color.colorToHSV(primaryColor, arrayOfFloat)
    arrayOfFloat[2] *= 0.9f
    return Color.HSVToColor(arrayOfFloat)
}
