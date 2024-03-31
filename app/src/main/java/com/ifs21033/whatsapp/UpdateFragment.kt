package com.ifs21033.whatsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21033.whatsapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val dataUpdates = ArrayList<Update>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val message = it.getString(EXTRA_MESSAGE)
        }

        binding.rvFruits.setHasFixedSize(true)
        dataUpdates.addAll(getListUpdate())
        showRecyclerList()
    }

    private fun getListUpdate(): ArrayList<Update> {
        val dataIcon = resources.obtainTypedArray(R.array.Chat_icon)
        val listUpdate = ArrayList<Update>()
        for (i in 0 until dataIcon.length()) {
            val update = Update(
                dataIcon.getResourceId(i, -1)
            )
            listUpdate.add(update)
        }
        dataIcon.recycle()
        return listUpdate
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFruits.layoutManager = layoutManager

        val listUpdateAdapter = ListUpdateAdapter(dataUpdates)
        binding.rvFruits.adapter = listUpdateAdapter

        listUpdateAdapter.setOnItemClickCallback(object : ListUpdateAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Update) {
                showSelectedUpdate(data)
            }
        })
    }

    private fun showSelectedUpdate(update: Update) {
        // Implement your logic for handling selected update
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_QUERY = "extra_query"
    }
}
