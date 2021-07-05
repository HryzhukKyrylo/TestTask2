package com.natife.testtask2.ui.mainscreen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.natife.testtask2.data.entities.User

class ItemDiff : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
