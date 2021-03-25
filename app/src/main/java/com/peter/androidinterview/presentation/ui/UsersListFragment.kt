package com.peter.androidinterview.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.peter.androidinterview.databinding.FragmentUsersListBinding
import com.peter.androidinterview.domain.models.User
import com.peter.androidinterview.presentation.adapters.UsersAdapter
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersListFragment : Fragment() {

    private val appViewModel: AppViewModel by activityViewModels()
    private var binding: FragmentUsersListBinding? = null
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(binding!!.progressBar){
            val action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding?.usersRecyclerView?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            appViewModel.usersLiveData.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance() = UsersListFragment()
    }
}