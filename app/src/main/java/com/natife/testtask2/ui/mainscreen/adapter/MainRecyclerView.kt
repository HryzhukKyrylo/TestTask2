package com.natife.testtask2.ui.mainscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natife.testtask2.databinding.ListItemBinding
import com.natife.testtask2.data.entities.User

class MainRecyclerView(
    val itemClick: (String) -> Unit,
    val pagination: () -> Unit
) : ListAdapter<User, MainRecyclerView.MainViewHolder>(ItemDiff()) {

    inner class MainViewHolder(
        private val bindingItem: ListItemBinding
    ) :
        RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(item: User) {
            with(bindingItem) {
                Glide.with(this.root.context)
                    .load(item.picture)
                    .into(bindingItem.itemImageView)
                itemNameText.text = item.firstName
                itemAgeText.text = item.age.toString()

                root.setOnClickListener {
                    itemClick(item.uuid ?: "")
                }
            }
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
        holder.bind(getItem(position))
        if (position == itemCount - 2) {
            pagination()
        }
    }
}
