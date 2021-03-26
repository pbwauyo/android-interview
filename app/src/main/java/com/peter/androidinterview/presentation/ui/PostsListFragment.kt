package com.peter.androidinterview.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.peter.androidinterview.R
import com.peter.androidinterview.databinding.FragmentPostsListBinding
import com.peter.androidinterview.presentation.adapters.PostsPagingAdapter
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsListFragment : Fragment() {

    private var binding: FragmentPostsListBinding? = null
    val appViewModel: AppViewModel by activityViewModels()
    private val args: PostsListFragmentArgs by navArgs()
    lateinit var adapter: PostsPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsListBinding.inflate(inflater, container, false)
        adapter = PostsPagingAdapter(binding!!.progressBar){
            val action = PostsListFragmentDirections.actionPostsListFragmentToCommentsListFragment(it.id)
            findNavController().navigate(action)
        }
        binding?.postsRecyclerView?.adapter = adapter

        /**
         * update the userId in the [appViewModel] to trigger fetching of posts
         */
        appViewModel.updateUserId(args.userId)

        appViewModel.postsLiveData.observe(viewLifecycleOwner){ data ->
            viewLifecycleOwner.lifecycleScope.launch {
                data?.let { adapter.submitData(data) }
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation to AddPostFragment on FAB click
        binding?.addPostFab?.setOnClickListener {
            findNavController().navigate(R.id.action_postsListFragment_to_addPostFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = PostsListFragment()
    }
}