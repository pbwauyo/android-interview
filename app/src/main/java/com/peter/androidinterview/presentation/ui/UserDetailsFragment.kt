package com.peter.androidinterview.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.peter.androidinterview.databinding.FragmentUserDetailsBinding
import com.peter.androidinterview.domain.models.User

class UserDetailsFragment : Fragment() {
    private val args: UserDetailsFragmentArgs by navArgs()
    private lateinit var user: User
    private var binding: FragmentUserDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        user = args.user

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.name?.text = user.name
        binding?.email?.text = user.email
        val address = "${user.address.street}, ${user.address.city}, ${user.address.suite}"
        binding?.address?.text = address
        binding?.phone?.text = user.phone
        binding?.website?.text = user.website
        binding?.company?.text = user.company.name

        val postsAction = UserDetailsFragmentDirections.actionUserDetailsFragmentToPostsListFragment(user.id)
        val albumsAction = UserDetailsFragmentDirections.actionUserDetailsFragmentToAlbumsListFragment(user.id)

        binding?.viewPosts?.setOnClickListener {
            findNavController().navigate(postsAction)
        }

        binding?.viewAlbums?.setOnClickListener {
            findNavController().navigate(albumsAction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserDetailsFragment()
    }
}