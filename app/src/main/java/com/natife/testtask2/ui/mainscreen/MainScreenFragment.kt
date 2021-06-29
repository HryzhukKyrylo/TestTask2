package com.natife.testtask2.ui.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.natife.testtask2.R
import com.natife.testtask2.databinding.FragmentMainSreenBinding
import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.ui.mainscreen.adapter.MainRecyclerView
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment(), MainRecyclerView.OnItemClickListener {

    private lateinit var binding: FragmentMainSreenBinding

    private val viewModel: MainViewModel by viewModels()
    private val adapter: MainRecyclerView by lazy { MainRecyclerView(this) }

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
        initViewMovel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
    }

    private fun initViewMovel() {
        viewModel.users.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty())
                updateListRecycler(it.results)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateListRecycler(list: List<Human>) {
        adapter.updateListRecycler(list)
    }

    private fun initAdapter() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.adapter = adapter

    }

    override fun onItemClicked(id: String) {
        findNavController().navigate(R.id.navigateToPreviewScreen)
    }
}
