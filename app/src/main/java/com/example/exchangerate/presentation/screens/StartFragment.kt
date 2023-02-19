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
import com.example.exchangerate.databinding.FragmentStartBinding
import com.example.exchangerate.domain.model.CheckCondition
import com.example.exchangerate.domain.model.Rates
import com.example.exchangerate.presentation.ExchangeRateApp
import com.example.exchangerate.presentation.MainViewModel
import com.example.exchangerate.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class StartFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as ExchangeRateApp).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.updateBut.setOnClickListener {
            retryToGetData()
        }
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
        viewModel.exchangeRater.observe(viewLifecycleOwner) {
            when (it) {
                is Rates -> {
                    binding.updateBut.visibility = View.INVISIBLE
                    binding.tvRubUsd.text = it.RUB
                    binding.tvRubEur.text = it.EUR
                }

                is CheckCondition -> showErrorCondition()
            }
        }
    }


    private fun retryToGetData() {
        lifecycleScope.launch {
            viewModel.getCurrentRate()
        }
    }

    private fun showErrorCondition() {
        Toast.makeText(context, R.string.exception, Toast.LENGTH_LONG).show()
    }

}