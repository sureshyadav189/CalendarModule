package com.example.calendarmodule.viewmodel

import androidx.lifecycle.ViewModel
import com.example.calendarmodule.model.Weekdays
import com.example.calendarmodule.repository.CalendarRepositorey
import com.example.calendarmodule.repository.ICalendar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(private val calendarRepositorey: CalendarRepositorey):ViewModel(),
    ICalendar {
    private val _calendarResponse = MutableStateFlow(mutableListOf<Weekdays>())
    var calendarList:StateFlow<List<Weekdays>> = _calendarResponse

    init {
        getCalendarData()
    }

    fun getCalendarData(){
        calendarList = calendarRepositorey.getWeekDays()
    }

    override fun showDate(): String {
        return calendarRepositorey.showDate()
    }

    override fun updateCurrentTime(): String {
        return calendarRepositorey.updateCurrentTime()
    }
}