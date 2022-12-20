package com.example.exchangerate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exchangerate.databinding.ActivityMainBinding
import com.example.exchangerate.screens.splash.SplashFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.frag_lay_out, SplashFragment.newInstance()).commit()
        }
    }
}
