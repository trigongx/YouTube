package com.example.youtube.presentation.playlists


import android.view.View
import android.widget.Toast
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.core.utils.Status
import com.example.youtube.databinding.FragmentPlaylistsBinding
import com.example.youtube.presentation.activity.MainActivity

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding,PlaylistsViewModel>() {
    private val viewModel = PlaylistsViewModel(MainActivity.repository)
    override fun inflateViewBinding(): FragmentPlaylistsBinding =
        FragmentPlaylistsBinding.inflate(layoutInflater)

    private val adapter = PlaylistsAdapter()

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvPlaylists.adapter = adapter
    }

    override fun initView() {
        super.initView()
        viewModel.getPlaylists().observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { adapter.addData(it.items) }
                    binding.pbLoading.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        }
    }
}