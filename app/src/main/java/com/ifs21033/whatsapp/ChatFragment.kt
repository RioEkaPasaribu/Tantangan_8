package com.ifs21033.whatsapp

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21033.whatsapp.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private val dataFruits = ArrayList<Chat>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val message = it.getString(EXTRA_MESSAGE)
        }

        binding.rvFruits.setHasFixedSize(false)
        dataFruits.addAll(getListChat())
        showRecyclerList()
    }

    private fun getListChat(): ArrayList<Chat> {
        val dataName = resources.getStringArray(R.array.Chat_name)
        val dataIcon = resources.obtainTypedArray(R.array.Chat_icon)
        val dataDesc = resources.getStringArray(R.array.Chat_desc)
        val listChat = ArrayList<Chat>()
        for (i in dataName.indices) {
            val chat = Chat(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDesc[i]
            )
            listChat.add(chat)
        }
        dataIcon.recycle()
        return listChat
    }

    private fun showRecyclerList() {
        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvFruits.layoutManager = layoutManager

        val listFamilyAdapter = ListChatAdapter(dataFruits)
        binding.rvFruits.adapter = listFamilyAdapter

        listFamilyAdapter.setOnItemClickCallback(object : ListChatAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Chat) {
                showSelectedChat(data)
            }
        })
    }

    private fun showSelectedChat(chat: Chat) {
        // Implement your logic for handling selected chat
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_QUERY = "extra_query"
    }
}
