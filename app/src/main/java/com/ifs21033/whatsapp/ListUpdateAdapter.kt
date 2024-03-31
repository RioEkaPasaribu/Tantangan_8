package com.ifs21033.whatsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21033.whatsapp.databinding.ItemRowUpdateBinding

class ListUpdateAdapter(private val listUpdate: List<Update>) : RecyclerView.Adapter<ListUpdateAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUpdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUpdate[position])
    }

    override fun getItemCount(): Int = listUpdate.size

    inner class ListViewHolder(private val binding: ItemRowUpdateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(update: Update) {
            binding.ivItemChat.setImageResource(update.icon)
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(update)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Update)
    }
}
