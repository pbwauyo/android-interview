package com.peter.androidinterview.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.peter.androidinterview.databinding.FragmentCommentsListBinding
import com.peter.androidinterview.presentation.adapters.CommentsPagingAdapter
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentsListFragment : Fragment() {
    private val args: CommentsListFragmentArgs by navArgs()
    private var binding: FragmentCommentsListBinding? = null
    val viewModel: AppViewModel by activityViewModels()
    private lateinit var adapter: CommentsPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentsListBinding.inflate(inflater, container, false)
        adapter = CommentsPagingAdapter(binding!!.progressBar)
        binding?.commentsRecyclerView?.adapter = adapter

        viewModel.commentsLiveData.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        /**
         * update the postId in the [viewModel] to trigger fetching of comments
         */
        viewModel.updatePostId(args.postId)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = CommentsListFragment()
    }
}