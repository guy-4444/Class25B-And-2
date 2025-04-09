package com.guyi.class25b_and_2.utils

object TimeFormatter {
    fun formatTime(currentTimeDelta: Long): String {
        var seconds = (currentTimeDelta / 1000).toInt()
        var minutes = seconds / 60
        seconds %= 60
        var hours = minutes / 60
        minutes %= 60
        hours %= 24
        return buildString {
            append(String.format(locale = null, format = "%02d", hours))
            append(":")
            append(String.format(locale = null, format = "%02d", minutes))
            append(":")
            append(String.format(locale = null, format = "%02d", seconds))
        }
    }
}