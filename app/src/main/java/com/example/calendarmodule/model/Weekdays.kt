package com.example.calendarmodule.model

data class Weekdays(
    val day:String,
    val isCurrentDay:Boolean = false,
    val isInPastDay:Boolean = false,
    val isInFutureDay:Boolean = false,
    val isShowingDot:Boolean = false
)
