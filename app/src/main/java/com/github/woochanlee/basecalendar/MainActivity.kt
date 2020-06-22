package com.github.woochanlee.basecalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.github.woochanlee.basecalendar.CalendarUtil.Companion.cacheData
import com.github.woochanlee.basecalendar.CalendarUtil.Companion.getCurrentDatePosition
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
    }

    private fun initViewPager() {
        val position = getCurrentDatePosition()

        vp_calendar.adapter = SlideCalendar(supportFragmentManager)
        vp_calendar.currentItem = position

        setDate()

        vp_calendar.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) { setDate() }
        })
    }

    private fun setDate() {
        val date = cacheData[vp_calendar.currentItem]

        if (date != null) {
            tv_current_month.text = String.format("%d %d", date.first, date.second)
        } else {
            println("error")
        }
    }
}
