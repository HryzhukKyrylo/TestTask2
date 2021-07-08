package com.natife.testtask2.ui.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.natife.testtask2.R
import com.natife.testtask2.databinding.FragmentMainSreenBinding
import com.natife.testtask2.ui.mainscreen.adapter.MainRecyclerView
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import com.natife.testtask2.utils.Const
import com.natife.testtask2.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private var binding: FragmentMainSreenBinding? = null
    private val mainViewModel by viewModel<MainViewModel>()
    private var adapter: MainRecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainSreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.loadUsers()
    }

    private fun initViewModel() {
        mainViewModel.responseUsers.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        resource.data.let { list -> list?.let { user -> adapter?.submitList(user) } }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Status.LOADING -> {
                        Toast.makeText(requireContext(), Const.LOAD, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        adapter = MainRecyclerView(::onItemClicked) { mainViewModel.loadUsers() }
        binding?.mainRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.mainRecyclerView?.adapter = adapter
    }

    fun onItemClicked(uuid: String) {
        val bundle = bundleOf(Const.ARG_USER to uuid)
        findNavController().navigate(R.id.navigateToPreviewScreen, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        adapter = null
    }
}
