package com.effectivem.android.presentation.page1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.effectivem.android.domain.entities.LatestItem
import com.effectivem.android.presentation.databinding.Page1ItemLatestBinding

class LatestItemHolder(
    private val binding: Page1ItemLatestBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(latestItem: LatestItem) {
        binding.itemView.load(latestItem.url) {
        }

        binding.category.text = latestItem.category

        binding.name.text = latestItem.name

        val price = "$ ${latestItem.price}"
        binding.price.text = price
    }
}

class LatestAdapter(
    private val latestItems: List<LatestItem>
) : RecyclerView.Adapter<LatestItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Page1ItemLatestBinding.inflate(inflater, parent, false)
        return LatestItemHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestItemHolder, position: Int) {
        val item = latestItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = latestItems.size
}

