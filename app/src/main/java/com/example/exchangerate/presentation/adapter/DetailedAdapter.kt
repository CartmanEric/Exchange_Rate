package com.example.exchangerate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.exchangerate.databinding.TimetableBinding
import com.example.exchangerate.domain.model.Rates

class DetailedAdapter : ListAdapter<Rates, DetailedViewHolder>(DetailedDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedViewHolder {
        return DetailedViewHolder(
            TimetableBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailedViewHolder, position: Int) {
        val currentItem = getItem(position)
        with(holder.itemBinding) {
            tvDataRv.text = currentItem.data
            tvEurRv.text = currentItem.EUR
            tvUsdRv.text = currentItem.RUB
        }
    }
}