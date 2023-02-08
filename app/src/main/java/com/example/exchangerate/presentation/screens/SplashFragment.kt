package com.example.exchangerate.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.exchangerate.R
import com.example.exchangerate.presentation.ExchangeRateApp
import com.example.exchangerate.presentation.MainViewModel
import com.example.exchangerate.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as ExchangeRateApp).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkCoroutineCondition()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun checkCoroutineCondition() {
        val initMainCoroutine = lifecycleScope.launch {
            viewModel.getCurrentRate()
        }
        lifecycleScope.launch {
            initMainCoroutine.join()
            openStartFragment()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }

    private fun openStartFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frag_lay_out, StartFragment.newInstance())
            .commit()
    }

}