package com.peter.androidinterview.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.peter.androidinterview.databinding.FragmentAddPostBinding
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment : DialogFragment() {

    private var binding: FragmentAddPostBinding? = null
    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddPostBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addPostBtn?.setOnClickListener {
            val title = binding!!.title.text.toString()
            val body = binding!!.body.text.toString()

            appViewModel.postNewPost(title, body)
            dismiss()
        }

        binding?.cancel?.setOnClickListener {
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}