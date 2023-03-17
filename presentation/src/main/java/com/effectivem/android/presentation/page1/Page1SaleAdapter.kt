package com.effectivem.android.presentation.page1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.effectivem.android.domain.entities.SaleItem
import com.effectivem.android.presentation.databinding.Page1ItemSaleBinding

class SaleItemHolder(
    private val binding: Page1ItemSaleBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(saleItem: SaleItem) {
        binding.itemView.load(saleItem.url) {
        }

        val discount = "${saleItem.discount} % off"
        binding.discount.text = discount

        binding.category.text = saleItem.category

        binding.name.text = saleItem.name

        val price = "$ ${saleItem.price}"
        binding.price.text = price
    }
}

class SaleAdapter(
    private val saleItems: List<SaleItem>
) : RecyclerView.Adapter<SaleItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Page1ItemSaleBinding.inflate(inflater, parent, false)
        return SaleItemHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleItemHolder, position: Int) {
        val item = saleItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = saleItems.size
}