package com.example.youtube.presentation.playlists

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.core.utils.Resource
import com.example.youtube.core.utils.Status
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.FragmentPlaylistsBinding

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding,PlaylistsViewModel>(){
    private val adapter = PlaylistsAdapter()
    override val viewModel: PlaylistsViewModel
        get() = ViewModelProvider(this)[PlaylistsViewModel::class.java]

    override fun inflateViewBinding(): FragmentPlaylistsBinding = FragmentPlaylistsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvPlaylists.adapter = adapter
    }

    override fun initView() {
        super.initView()
        viewModel.getPlaylists().observe(this){resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.let { adapter.addData(it.items) }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "error status", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    Toast.makeText(requireContext(), "loading status", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}




