package com.natife.testtask2.ui.mainscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natife.testtask2.databinding.ListItemBinding
import com.natife.testtask2.model.Human

class MainRecyclerView : RecyclerView.Adapter<MainRecyclerView.MainViewHolder>() {
    private lateinit var listHuman: List<String>

    class MainViewHolder(private val bindingItem: ListItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(item: String) {
            bindingItem.itemNameText.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listHuman[position])
    }

    override fun getItemCount(): Int = listHuman.size

    fun updateListRecycler(list: List<String>) {
        listHuman = list
    }
}