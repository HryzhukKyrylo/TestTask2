package com.natife.testtask2.ui.mainscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.natife.testtask2.BaseApplication
import com.natife.testtask2.R
import com.natife.testtask2.databinding.FragmentMainSreenBinding
import com.natife.testtask2.ui.mainscreen.adapter.MainRecyclerView
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import com.natife.testtask2.utils.Const
import com.natife.testtask2.utils.Resource
import javax.inject.Inject


class MainScreenFragment : Fragment() {

    private var binding: FragmentMainSreenBinding? = null
    lateinit var viewModel: MainViewModel
    private var adapter: MainRecyclerView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        (activity?.applicationContext as BaseApplication).getAppComponent().inject(this)
    }

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
        viewModel.loadUsers()
    }

    private fun initViewModel() {
        viewModel.responseUsers.observe(viewLifecycleOwner) {
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
        adapter = MainRecyclerView(::onItemClicked) { viewModel.loadUsers() }
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
