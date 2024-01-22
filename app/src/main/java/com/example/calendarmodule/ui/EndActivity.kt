package com.example.calendarmodule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendarmodule.R
import com.example.calendarmodule.databinding.ActivityEndBinding

class EndActivity : AppCompatActivity() {
    lateinit var binding:ActivityEndBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myWebView.loadUrl("https://giphy.com/gifs/KARDEA-boy-url-kardea-ckBy1fzVTODEE6wSrN")
    }
}