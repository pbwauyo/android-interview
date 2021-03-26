package com.peter.androidinterview.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.peter.androidinterview.R
import com.peter.androidinterview.databinding.FragmentPhotosListBinding
import com.peter.androidinterview.presentation.adapters.PhotosPagingAdapter
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosListFragment : Fragment() {

    private var binding: FragmentPhotosListBinding? = null
    private val args: PhotosListFragmentArgs by navArgs()
    val appViewModel: AppViewModel by viewModels()
    lateinit var adapter: PhotosPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosListBinding.inflate(inflater, container, false)
        adapter = PhotosPagingAdapter(binding!!.progressBar)
        binding?.photosRecyclerView?.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding?.photosRecyclerView?.adapter = adapter

        appViewModel.photosLiveData.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        /**
         * update the albumId in the [appViewModel] to trigger fetching of photos
         */
        appViewModel.updateAlbumId(args.albumId)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = PhotosListFragment()
    }
}