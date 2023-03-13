package com.example.exchangerate.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exchangerate.domain.model.Rates

class DetailedDiffCallback : DiffUtil.ItemCallback<Rates>() {
    override fun areItemsTheSame(oldItem: Rates, newItem: Rates): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Rates, newItem: Rates): Boolean {
        return oldItem == newItem
    }
}