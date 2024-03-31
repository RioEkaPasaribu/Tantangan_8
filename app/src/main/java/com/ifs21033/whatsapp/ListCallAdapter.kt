package com.ifs21033.whatsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21033.whatsapp.databinding.ItemRowChatBinding

class ListCallAdapter(private val listCall: ArrayList<Call>) :
    RecyclerView.Adapter<ListCallAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowChatBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val call = listCall[position]
        holder.binding.ivItemChat.setImageResource(call.icon)
        holder.binding.tvItemChat.text = call.name
        holder.binding.textView2.text = call.desc
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(call)
        }
    }

    override fun getItemCount(): Int = listCall.size

    inner class ListViewHolder(var binding: ItemRowChatBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Call)
    }
}
