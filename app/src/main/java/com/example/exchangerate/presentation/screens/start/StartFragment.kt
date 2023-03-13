package com.example.exchangerate.presentation.screens.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exchangerate.databinding.FragmentStartBinding
import com.example.exchangerate.presentation.ExchangeRateApp
import com.example.exchangerate.presentation.screens.splash.SplashViewModel
import com.example.exchangerate.presentation.ViewModelFactory
import javax.inject.Inject


class StartFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as ExchangeRateApp).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[StartViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        initViewModel()
        return binding.root

    }


    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        viewModel.getList.observe(viewLifecycleOwner) {
            with(binding) {
                tvRubUsd.text = it.last().RUB
                tvRubEur.text = it.last().EUR
                tvData.text = it.last().data
                tvRubToEur.text = it.size.toString()
            }
        }
    }
}