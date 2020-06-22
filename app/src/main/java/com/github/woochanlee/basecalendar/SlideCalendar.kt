package com.github.woochanlee.basecalendar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SlideCalendar(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        CalendarUtil.cacheData[position]?.let { date ->
            return CalendarFragment(date.first, date.second)
        }
        return CalendarFragment(2020, 6)
    }

    override fun getCount(): Int =  CalendarUtil.cacheData.size
}