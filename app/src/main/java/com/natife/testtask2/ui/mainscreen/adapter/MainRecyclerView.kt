package com.natife.testtask2.ui.mainscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natife.testtask2.databinding.ListItemBinding
import com.natife.testtask2.data.entities.Human

class MainRecyclerView (
    private val listener : OnItemClickListener
        ): RecyclerView.Adapter<MainRecyclerView.MainViewHolder>() {
    private var listHuman: List<Human> = listOf()

    class MainViewHolder(
        private val listener : OnItemClickListener,
        private val bindingItem: ListItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(item: Human) {
            with(bindingItem){
                Glide.with(this.root.context)
                    .load(item.picture.medium)
                    .into(bindingItem.itemImageView)
                itemNameText.text = item.name.first
                itemAgeText.text = item.dob.age.toString()
                root.setOnClickListener {
                    listener.onItemClicked(item.login.uuid?:"")
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
        return MainViewHolder(listener,binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listHuman[position])
    }

    override fun getItemCount(): Int = listHuman.size

    fun updateListRecycler(list: List<Human>) {
        listHuman = list
        notifyDataSetChanged()
    }


    interface OnItemClickListener{
        fun onItemClicked(id : String)
    }

}
