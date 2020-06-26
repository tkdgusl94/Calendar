package com.github.woochanlee.basecalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_calendar.view.*

/**
 * 생성자로 받는거 Param으로 바꾸기
 */
class CalendarFragment(private val year: Int, private val month: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        initView(view)

        return view
    }

    private fun initView(view: View) {
        view.rv_schedule.layoutManager = GridLayoutManager(requireContext(), BaseCalendar.DAYS_OF_WEEK)
        view.rv_schedule.adapter = RecyclerViewAdapter(year, month)
    }
}