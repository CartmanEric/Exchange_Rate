package com.example.exchangerate.presentation.screens.detailed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.exchangerate.R
import com.example.exchangerate.databinding.FragmentDetailedBinding
import com.example.exchangerate.presentation.ExchangeRateApp
import com.example.exchangerate.presentation.ViewModelFactory
import com.example.exchangerate.presentation.adapter.DetailedAdapter
import com.example.exchangerate.presentation.screens.start.StartViewModel
import javax.inject.Inject


class DetailedFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as ExchangeRateApp).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailedViewModel::class.java]
    }
    private lateinit var detailedAdapter: DetailedAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentDetailedBinding? = null
    private val binding: FragmentDetailedBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailedBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getList.observe(viewLifecycleOwner){
detailedAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailedFragment()
    }

    private fun initAdapter() {
        val manager = LinearLayoutManager(requireContext(),HORIZONTAL,false)
        with(binding.rv) {
            detailedAdapter = DetailedAdapter()
            layoutManager = manager
            adapter = detailedAdapter
        }
    }

    private fun initViewModel(){

    }

}