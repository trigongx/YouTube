package com.example.youtube.presentation.playlists


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.utils.Status
import com.example.youtube.databinding.FragmentPlaylistsBinding
import com.example.youtube.domain.repository.Repository

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding>() {
    private val viewModel = PlaylistsViewModel(Repository(RetrofitClient().createApiService()))
    override fun inflateViewBinding(): FragmentPlaylistsBinding =
        FragmentPlaylistsBinding.inflate(layoutInflater)

    private val adapter = PlaylistsAdapter()

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvPlaylists.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlaylists().observe(viewLifecycleOwner){resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.let { adapter.addData(it.items) }
                    Toast.makeText(requireContext(), "success status", Toast.LENGTH_SHORT).show()
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





