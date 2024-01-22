package com.example.calendarmodule.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarmodule.R
import com.example.calendarmodule.adapter.WeekDayAdapter
import com.example.calendarmodule.databinding.ActivityCalendarBinding
import com.example.calendarmodule.viewmodel.CalendarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random


@AndroidEntryPoint
class CalendarActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalendarBinding
    private var updateTimeJob: Job? = null
    private val viewModel: CalendarViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        bindObserver()


    }

    private fun init(){
        binding.topBox.tvDate.text = viewModel.showDate()

        binding.bottomBox.btnEnter.setOnClickListener {
            openPage()
        }

        updateTimeJob = CoroutineScope(Dispatchers.Main).launch {
            while (true){
                binding.topBox.tvTime.text = viewModel.updateCurrentTime()
                delay(1000)
            }
        }

        updateTimeJob = CoroutineScope(Dispatchers.Main).launch {
            while (true){
                randomColor()
                delay(1000*60*60)
            }
        }


    }

    private fun bindObserver(){
        lifecycleScope.launch {
            viewModel.calendarList.collect{
                binding.bottomBox.rvWeekdays.apply {
                    layoutManager = LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL,false)
                    adapter = WeekDayAdapter(it)
                }
            }
        }
    }

    private fun openPage(){
        startActivity(Intent(this,EndActivity::class.java))
    }

    private fun randomColor(){
        val rand = Random()
        val r: Int = rand.nextInt(255)
        val g: Int = rand.nextInt(255)
        val b: Int = rand.nextInt(255)
        val randomColor = Color.rgb(r, g, b)
        val color: IntArray? = null
        val col = intArrayOf(Color.rgb(r, g, b))
        var backgroundGradient = binding.consMain.background as GradientDrawable
        backgroundGradient.setColor(Color.rgb(r,g,b))
    }
}