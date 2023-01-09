package com.example.exchangerate.presentation.screens.start

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.exchangerate.R

import com.example.exchangerate.databinding.FragmentStartBinding
import com.example.exchangerate.presentation.ExchangeRateApp
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.updateBut.setOnClickListener {
            viewModel.getCurrentRate()
            binding.updateBut.visibility = View.INVISIBLE
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
        viewModel.exchangeRateDataRub.observe(viewLifecycleOwner) {
            binding.tvRubUsd.text = it
        }
        viewModel.exchangeRateDataEur.observe(viewLifecycleOwner) {
            binding.tvRubEur.text = it
        }
        viewModel.errorCondition.observe(viewLifecycleOwner) {
            showErrorCondition()
        }
    }

    private fun showErrorCondition() {
        Toast.makeText(context, R.string.exception, Toast.LENGTH_LONG).show()
        binding.updateBut.visibility = View.VISIBLE
    }


}