package com.example.calendarmodule.repository

import com.example.calendarmodule.model.Weekdays
import kotlinx.coroutines.flow.MutableStateFlow

interface IWeekday : ICalendar{
    fun getWeekDays() : MutableStateFlow<MutableList<Weekdays>>
}