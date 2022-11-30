package com.example.exchangerate.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.exchangerate.R

import com.example.exchangerate.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private val viewModel: StartViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        viewModel.getCurrentRate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.exchangeRateDataRub.observe(viewLifecycleOwner) {
            binding.tvRubUsd.text = it
        }
        viewModel.exchangeRateDataEur.observe(viewLifecycleOwner) {
            binding.tvRubEur.text = it
        }
        viewModel.errorCondition.observe(viewLifecycleOwner) {
            showErrorCondition()
        }
        binding.updateBut.setOnClickListener {
            viewModel.getCurrentRate()
            binding.updateBut.visibility = View.INVISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }

    private fun showErrorCondition() {
        Toast.makeText(context, R.string.exception, Toast.LENGTH_LONG).show()
        binding.updateBut.visibility = View.VISIBLE
    }

}