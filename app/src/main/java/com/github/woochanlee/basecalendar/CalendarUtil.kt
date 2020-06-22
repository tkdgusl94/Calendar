package com.github.woochanlee.basecalendar

import java.util.*

class CalendarUtil {
    companion object {
        const val START_YEAR = 1970

        val cacheData = mutableMapOf<Int, Pair<Int, Int>>()

        val currentDate: Calendar = Calendar.getInstance().apply { time = Date(System.currentTimeMillis()) }

        init {
            initCache()
        }

        private fun initCache() {
            var position = 0
            val currentYear = currentDate.get(Calendar.YEAR)

            for (i in START_YEAR..currentYear + 20) {
                for (j in 1..12) cacheData[position++] = i to j
            }
        }

        fun getCurrentDatePosition(): Int {
            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)

            return (year - START_YEAR) * 12 + month
        }
    }
}