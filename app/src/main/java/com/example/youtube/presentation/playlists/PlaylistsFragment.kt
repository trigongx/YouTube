package com.example.youtube.presentation.playlists


import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.FragmentPlaylistsBinding
import com.example.youtube.utils.Constants
import com.example.youtube.utils.InternetConnectionService
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding, PlaylistsViewModel>() {
    override val viewModel: PlaylistsViewModel by viewModel()

    override fun inflateViewBinding(): FragmentPlaylistsBinding =
        FragmentPlaylistsBinding.inflate(layoutInflater)

    private val adapter = PlaylistsAdapter(this::onClickItem)

    /*private val internetConnectionService = InternetConnectionService(requireContext())*/
    // нельзя задавать requireContext переменной пустой переменной

    override fun checkConnection() {
        super.checkConnection()
        InternetConnectionService(requireContext()).observe(viewLifecycleOwner) { isConnect ->
            if (!isConnect) {
                binding.rvPlaylists.visibility = View.GONE
                binding.containerInclude.visibility = View.VISIBLE
            }
            binding.layoutNoConnection.btnCheckInternetTryAgain.setOnClickListener {
                if (isConnect) {
                    binding.rvPlaylists.visibility = View.VISIBLE
                    binding.containerInclude.visibility = View.GONE
                } else{
                    Toast.makeText(requireContext(), getString(R.string.no_connection_try_again), Toast.LENGTH_SHORT).show()
                }
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
            /*Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_SHORT).show()*/
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
    }

    override fun initData() {
        super.initData()
        viewModel.getPlaylists()
    }

    private fun onClickItem(playlistItem: PlaylistsModel.Item) {
        setFragmentResult(
            Constants.KEY_PLAYLIST_TO_VIDEOS,
            bundleOf(Constants.KEY_SET_VIDEO_TO_FRAGMENT_ITEMS to playlistItem)
        )
        findNavController().navigate(R.id.playlistItemsFragment)
    }

}