package com.ifs21033.whatsapp

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21033.whatsapp.databinding.FragmentCallBinding

class CallFragment : Fragment() {
    private lateinit var binding: FragmentCallBinding
    private val dataCalls = ArrayList<Call>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val message = it.getString(EXTRA_MESSAGE)
        }

        binding.rvFruits.setHasFixedSize(false)
        dataCalls.addAll(getListCall())
        showRecyclerList()
    }

    private fun getListCall(): ArrayList<Call> {
        // Definisikan sumber data Call sesuai kebutuhan Anda
        // Misalnya, dapatkan data dari database atau resource array
        val dataName = resources.getStringArray(R.array.Call_name)
        val dataIcon = resources.obtainTypedArray(R.array.Call_icon)
        val dataDesc = resources.getStringArray(R.array.Call_desc)
        val listCall = ArrayList<Call>()
        for (i in dataName.indices) {
            val call = Call(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDesc[i]
            )
            listCall.add(call)
        }
        dataIcon.recycle()
        return listCall
    }

    private fun showRecyclerList() {
        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvFruits.layoutManager = layoutManager

        val listCallAdapter = ListCallAdapter(dataCalls)
        binding.rvFruits.adapter = listCallAdapter

        listCallAdapter.setOnItemClickCallback(object : ListCallAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Call) {
                showSelectedCall(data)
            }
        })
    }

    private fun showSelectedCall(call: Call) {
        // Implement your logic for handling selected call
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_QUERY = "extra_query"
    }
}
