package com.github.woochanlee.basecalendar

import java.util.*


/**
 * BaseCalendar
 */
class BaseCalendar {

    val calendar: Calendar = Calendar.getInstance()

    var prevMonthTailOffset = 0
    var nextMonthHeadOffset = 0
    var currentMonthMaxDate = 0

    var data = arrayListOf<Int>()

    init {
        calendar.time = Date()
    }

    fun changeTo(year: Int, month: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)

        calendar.set(Calendar.DATE, 1)

        makeMonthDate(0)
    }

    /**
     * make month date.
     */
    private fun makeMonthDate(day: Int) {
        data.clear()

        currentMonthMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        /* 토, 일, 월 중에 뭐부터 시작할건지 판단 */
        prevMonthTailOffset = when(day) {
            // 일요일
            0 -> calendar.get(Calendar.DAY_OF_WEEK) - 1
            // 월요일
            1 -> calendar.get(Calendar.DAY_OF_WEEK) - 2
            // 토요일
            2 -> calendar.get(Calendar.DAY_OF_WEEK)
            else -> 1
        }
        makePrevMonthTail(calendar.clone() as Calendar)
        makeCurrentMonth(calendar)

        nextMonthHeadOffset = LOW_OF_CALENDAR * DAYS_OF_WEEK - (prevMonthTailOffset + currentMonthMaxDate)
        makeNextMonthHead()
    }

    /**
     * Generate data for the last month displayed before the first day of the current calendar.
     */
    private fun makePrevMonthTail(calendar: Calendar) {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        val maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        var maxOffsetDate = maxDate - prevMonthTailOffset

        for (i in 1..prevMonthTailOffset) data.add(++maxOffsetDate)
    }

    /**
     * Generate data for the current calendar.
     */
    private fun makeCurrentMonth(calendar: Calendar) {
        for (i in 1..calendar.getActualMaximum(Calendar.DATE)) data.add(i)
    }

    /**
     * Generate data for the next month displayed before the last day of the current calendar.
     */
    private fun makeNextMonthHead() {
        var date = 1

        for (i in 1..nextMonthHeadOffset) data.add(date++)
    }

    companion object {
        const val DAYS_OF_WEEK = 7
        const val LOW_OF_CALENDAR = 6
    }
}