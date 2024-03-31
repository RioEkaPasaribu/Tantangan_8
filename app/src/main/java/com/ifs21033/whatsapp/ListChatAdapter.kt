package com.ifs21033.whatsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21033.whatsapp.databinding.ItemRowChatBinding

class ListChatAdapter(private val listFamily: ArrayList<Chat>) :
    RecyclerView.Adapter<ListChatAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowChatBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val Family = listFamily[position]
        holder.binding.ivItemChat.setImageResource(Family.icon)
        holder.binding.tvItemChat.text = Family.name
        holder.binding.textView2.text = Family.desc
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listFamily[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listFamily.size
    class ListViewHolder(var binding: ItemRowChatBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Chat)
    }
}