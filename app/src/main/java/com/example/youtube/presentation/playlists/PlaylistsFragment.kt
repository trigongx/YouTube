package com.example.youtube.presentation.playlists


import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.FragmentPlaylistsBinding
import com.example.youtube.presentation.activity.MainActivity
import com.example.youtube.utils.Constants
import com.example.youtube.utils.InternetConnectionService

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding, PlaylistsViewModel>() {

    override fun inflateViewBinding(): FragmentPlaylistsBinding =
        FragmentPlaylistsBinding.inflate(layoutInflater)
    override fun setViewModel(): PlaylistsViewModel = PlaylistsViewModel(MainActivity.repository)

    private val adapter = PlaylistsAdapter(this::onClickItem)

    /*private val internetConnectionService = InternetConnectionService(requireContext())*/
    // нельзя задавать requireContext переменной

    override fun checkConnection() {
        super.checkConnection()
        val handler = Handler()
        val delayMillis = 3000
        InternetConnectionService(requireContext()).observe(viewLifecycleOwner) { hasInternet ->
            if (!hasInternet) {
                handler.postDelayed({
                    findNavController().navigate(R.id.noInternetFragment)
                }, delayMillis.toLong())
            }
        }
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvPlaylists.adapter = adapter
    }

    override fun initView() {
        super.initView()
        viewModel.playlists.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_SHORT).show()
            adapter.addData(it.items)
            binding.pbLoading.visibility = View.GONE
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.pbLoading.visibility = View.VISIBLE
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
        /*viewModel.getPlaylists().observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        adapter.addData(it.items)
                    }
                    binding.pbLoading.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        }*/
    }

    override fun initData() {
        super.initData()
        viewModel.getPlaylists()
    }

    private fun onClickItem(playlistItem:PlaylistsModel.Item){
        setFragmentResult(
            Constants.KEY_PLAYLIST_TO_VIDEOS, bundleOf(Constants.KEY_SET_VIDEO_TO_FRAGMENT_ITEMS to playlistItem)
        )
        findNavController().navigate(R.id.playlistItemsFragment)
    }

}