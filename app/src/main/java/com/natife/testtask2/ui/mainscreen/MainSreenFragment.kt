package com.natife.testtask2.ui.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.natife.testtask2.R
import com.natife.testtask2.databinding.FragmentMainSreenBinding
import com.natife.testtask2.model.Human
import com.natife.testtask2.model.Name
import com.natife.testtask2.ui.mainscreen.adapter.MainRecyclerView

class MainSreenFragment : Fragment() {

    private lateinit var binding: FragmentMainSreenBinding
    private val adapter: MainRecyclerView by lazy { MainRecyclerView() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainSreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        updateListRecycler()
    }

    private fun updateListRecycler() {
        adapter.updateListRecycler(initListHuman())
    }

    private fun initListHuman() = listOf<String>("Bob","Dilan","Willam")


    private fun initAdapter() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.adapter = adapter

    }

}
