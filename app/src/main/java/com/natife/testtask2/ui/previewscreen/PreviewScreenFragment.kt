package com.natife.testtask2.ui.previewscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.databinding.FragmentPreviewScreenBinding
import com.natife.testtask2.ui.previewscreen.viewmodel.PreviewViewModel
import com.natife.testtask2.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewScreenFragment : Fragment() {
    private var binding: FragmentPreviewScreenBinding? = null
    private val viewmodel: PreviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreviewScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUser()
        initObserv()
    }

    private fun fetchUser() {
        val arg = arguments?.getString(Const.ARG_USER)
        if (!arg.isNullOrEmpty()) {
            viewmodel.fetchUser(arg)
        } else {
            findNavController().popBackStack()
        }
    }

    private fun initObserv() {
        viewmodel.user.observe(viewLifecycleOwner) {
            initView(it)
        }
    }

    private fun initView(user: User) {
        with(binding) {
            this?.let {
                Glide.with(requireContext())
                    .load(user.picture)
                    .into(userImageView)
                userImageView.contentDescription = user.title
                firstNameText.text = user.firstName
                lastNameText.text = user.lastName
                ageText.text = user.age.toString()
                phoneText.text = user.phone
                emailText.text = user.email
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}