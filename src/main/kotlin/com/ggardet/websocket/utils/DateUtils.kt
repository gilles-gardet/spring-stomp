package com.ggardet.websocket.utils

import java.time.LocalDateTime

object DateUtils {
    @JvmStatic
    fun getTime(): String {
        val time = LocalDateTime.now()
        val hours = convertToTwoDigits(time.hour)
        val minutes = convertToTwoDigits(time.minute)
        val seconds = convertToTwoDigits(time.second)
        return "$hours:$minutes:$seconds"
    }

    private fun convertToTwoDigits(number: Int): String =
        if (numberOfDigits(number) == 1) "0$number" else number.toString()

    private fun numberOfDigits(number: Int): Int =
        when (number) {
            in -9..9 -> 1
            else -> 1 + numberOfDigits(number / 10)
        }
}
