package com.github.woochanlee.basecalendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_schedule.*
import kotlinx.android.synthetic.main.item_schedule.view.*
import java.util.*

/**
 * RecyclerViewAdapter
 */
class RecyclerViewAdapter(year: Int, month: Int) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val baseCalendar = BaseCalendar()

    private val currentDate = Date(System.currentTimeMillis())

    init {
        baseCalendar.changeTo(year, month)
    }

    /**
     * ViewHolder
     */
    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /* 요일별로 날짜 색깔 설정 */
        when {
            position % BaseCalendar.DAYS_OF_WEEK == 0 -> holder.tv_date.setTextColor(Color.parseColor("#ff1200"))
            position % BaseCalendar.DAYS_OF_WEEK == 6 -> holder.tv_date.setTextColor(Color.parseColor("#2979FF"))
            else -> holder.tv_date.setTextColor(Color.parseColor("#676d6e"))
        }

        /* 이전 달 혹은 다음 달은 글씨 흐리게 설정 */
        if (position < baseCalendar.prevMonthTailOffset || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.tv_date.alpha = 0.3f
        } else {
            holder.tv_date.alpha = 1f
        }

        /* 현재 날짜 찍기 */
        if (isCurrentDate(baseCalendar.data[position])) holder.itemView.cl_background.visibility = View.VISIBLE

        /* TODO : 클릭 리스너 */
        holder.itemView.setOnClickListener {
            println("${baseCalendar.data[position]}")
        }

        holder.tv_date.text = baseCalendar.data[position].toString()
    }

    private fun isCurrentDate(day: Int): Boolean {
        val cal = Calendar.getInstance().apply { time = currentDate }

        return (baseCalendar.calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
                baseCalendar.calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
                day == cal.get(Calendar.DATE))
    }
}