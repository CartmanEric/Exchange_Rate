package com.example.exchangerate.presentation.screens.splash

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
import com.example.exchangerate.databinding.FragmentSplashBinding
import com.example.exchangerate.domain.model.ConditionError
import com.example.exchangerate.domain.model.ConditionSuccess
import com.example.exchangerate.presentation.ExchangeRateApp
import com.example.exchangerate.presentation.ViewModelFactory
import com.example.exchangerate.presentation.screens.start.StartFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as ExchangeRateApp).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]
    }

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        checkCoroutineCondition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.retryBt.setOnClickListener {
            retryGetInfo()
        }
        binding.openOfBt.setOnClickListener {
            openStartFragment()
        }
    }

    private fun checkCoroutineCondition() {
        val initMainCoroutine = lifecycleScope.launch {
            viewModel.getAndSetData()
        }
        lifecycleScope.launch {
            initMainCoroutine.join()
            viewModel.exchangeRater.observe(viewLifecycleOwner) {
                when (it) {
                    is ConditionSuccess -> openStartFragment()
                    is ConditionError -> showErrorCondition()
                }
            }
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

    private fun showErrorConditionToast() {
        Toast.makeText(context, R.string.exception, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorCondition() {
        showErrorConditionToast()
        binding.progressBar.visibility = View.INVISIBLE
        binding.retryBt.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            if (viewModel.checkIsListEmpty()) {
                binding.openOfBt.visibility = View.VISIBLE
            }
        }
    }

    private fun retryGetInfo() {
        binding.progressBar.visibility = View.VISIBLE
        checkCoroutineCondition()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}