package com.example.calendarmodule.repository

import com.example.calendarmodule.model.Weekdays
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CalendarRepositorey @Inject constructor(): IWeekday {

    private val _calendarResponse = MutableStateFlow(mutableListOf<Weekdays>())

    val weekDays = mutableListOf<Weekdays>()
    val days = listOf<String>(
        "M",
        "T",
        "W",
        "Th",
        "F",
        "Sa",
        "Su",
    )

    override fun showDate(): String {
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        // Format the date as a string
        val dateFormat = SimpleDateFormat("EEE,d MMM", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    override fun updateCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time

        // Format the time as a string
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return timeFormat.format(currentTime)
    }

    override fun getWeekDays(): MutableStateFlow<MutableList<Weekdays>> {
        val currentDayIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-2
        for (i in days.indices){
            val weekday = days[i]
            val isPastDay = i < currentDayIndex
            val isFutureDay = i > currentDayIndex
            val isCurrencyDay = i == currentDayIndex
            weekDays.add(Weekdays(weekday,isCurrencyDay,isPastDay,isFutureDay))
            _calendarResponse.value = weekDays
        }
        return _calendarResponse
    }

}