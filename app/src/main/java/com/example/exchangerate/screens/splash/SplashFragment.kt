package com.example.exchangerate.screens.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchangerate.R
import com.example.exchangerate.screens.start.StartFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            parentFragmentManager.beginTransaction()
                .replace(R.id.frag_lay_out, StartFragment.newInstance())
                .commit()
        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
            }

}