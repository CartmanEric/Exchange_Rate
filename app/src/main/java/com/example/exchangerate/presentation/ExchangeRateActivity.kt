package com.example.exchangerate.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exchangerate.R
import com.example.exchangerate.databinding.ActivityMainBinding
import com.example.exchangerate.presentation.screens.SplashFragment

class ExchangeRateActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.frag_lay_out, SplashFragment.newInstance()
            ).commit()
        }
    }
}
