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
import androidx.recyclerview.widget.GridLayoutManager
import com.peter.androidinterview.R
import com.peter.androidinterview.databinding.FragmentAlbumsListBinding
import com.peter.androidinterview.presentation.adapters.AlbumsPagingAdapter
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsListFragment : Fragment() {

    private var binding: FragmentAlbumsListBinding? = null
    private val args: AlbumsListFragmentArgs by navArgs()
    val appViewModel: AppViewModel by activityViewModels()
    private lateinit var adapter: AlbumsPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumsListBinding.inflate(inflater, container, false)

        adapter = AlbumsPagingAdapter(binding!!.progressBar){
            val action = AlbumsListFragmentDirections.actionAlbumsListFragmentToPhotosListFragment(it.id)
            findNavController().navigate(action)
        }

        binding?.albumsRecyclerView?.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding?.albumsRecyclerView?.adapter = adapter

        appViewModel.albumsLiveData.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        /**
         * update the userId in the [appViewModel] to trigger fetching of albums
         */
        appViewModel.updateUserId(args.userId)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = AlbumsListFragment()
    }
}