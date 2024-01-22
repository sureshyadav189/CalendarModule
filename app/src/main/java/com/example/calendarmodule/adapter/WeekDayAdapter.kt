package com.example.calendarmodule.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarmodule.R
import com.example.calendarmodule.model.Weekdays


class WeekDayAdapter(private val weekDays: List<Weekdays>) :
    RecyclerView.Adapter<WeekDayAdapter.WeekDayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_day,parent,false)
        return WeekDayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weekDays.size
    }

    override fun onBindViewHolder(holder: WeekDayViewHolder, position: Int) {
        val weekDays = weekDays[position]
        holder.bind(weekDays)
    }

    inner class WeekDayViewHolder(item : View) :RecyclerView.ViewHolder(item){
        private val tvdayName = item.findViewById<TextView>(R.id.weekDayTextView)
        private val imgDot = item.findViewById<AppCompatImageView>(R.id.bulletImageView)
        var backgroundGradient = tvdayName.background as GradientDrawable
        fun bind(weekDays: Weekdays) {
            tvdayName.text = weekDays.day
            when{
                weekDays.isInPastDay-> {
                    backgroundGradient.setColor(ContextCompat.getColor(itemView.context,
                        R.color.pastBCColor
                    ))
                    tvdayName.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    imgDot.visibility = View.GONE
                }
                weekDays.isInFutureDay->{
                    backgroundGradient.setColor(ContextCompat.getColor(itemView.context,
                        R.color.futureBCColor
                    ))
                    tvdayName.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                    imgDot.visibility = View.GONE
                }
                weekDays.isCurrentDay->{
                    backgroundGradient.setColor(ContextCompat.getColor(itemView.context,
                        R.color.currentColor
                    ))
                    tvdayName.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                    imgDot.visibility = View.VISIBLE
                }
            }

        }

    }
}