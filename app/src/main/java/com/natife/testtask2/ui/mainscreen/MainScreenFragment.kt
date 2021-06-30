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
import com.natife.testtask2.ui.mainscreen.adapter.MainRecyclerView
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import com.natife.testtask2.utils.Status
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
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
    }

    private fun initViewModel() {
        viewModel.responseUsers.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data?.isNullOrEmpty() == true) {
                            adapter.updateListRecycler(it.data)
                        }
                    }
                    Status.ERROR ->
                        Toast.makeText(requireContext(), "WTF!!!!!", Toast.LENGTH_SHORT).show()

                    Status.LOADING ->
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initAdapter() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.adapter = adapter

    }

    override fun onItemClicked(id: String) {
        findNavController().navigate(R.id.navigateToPreviewScreen)
    }
}
