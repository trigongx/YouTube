package com.example.youtube.presentation.playlistitems


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.FragmentPlaylistItemsBinding
import com.example.youtube.presentation.activity.MainActivity
import com.example.youtube.utils.Constants
import com.example.youtube.utils.InternetConnectionService


class PlaylistItemsFragment : BaseFragment<FragmentPlaylistItemsBinding,PlaylistItemsViewModel>() {

    override fun inflateViewBinding(): FragmentPlaylistItemsBinding = FragmentPlaylistItemsBinding.inflate(layoutInflater)
    override fun setViewModel(): PlaylistItemsViewModel = PlaylistItemsViewModel(MainActivity.repository)

    private val adapter = PlaylistItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResultListener()
        checkConnection()
        initLiveData()
    }

    private fun initView(playlistId: String) {
        viewModel.getPlaylistItems(playlistId)
    }

    private fun initLiveData() {
        viewModel.playlistItems.observe(viewLifecycleOwner) { list ->
            initRecycler(list.items)
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler(items: List<PlaylistsModel.Item>) {
        adapter.addData(items)
        binding.rvPlaylistItems.adapter = adapter
    }

    override fun checkConnection() {
        InternetConnectionService(requireContext()).observe(viewLifecycleOwner) { isConnect ->
            if (!isConnect) {
                binding.llContainerPlaylistItems.visibility = View.GONE
                binding.flInclude.visibility = View.VISIBLE
            }
            binding.layoutNoConnection.btnCheckInternetTryAgain.setOnClickListener {
                if (isConnect) {
                    binding.llContainerPlaylistItems.visibility = View.VISIBLE
                    binding.flInclude.visibility = View.GONE
                }
            }
        }
    }

    private fun initResultListener() {
        setFragmentResultListener(Constants.KEY_PLAYLIST_TO_VIDEOS) { _, bundle ->
            bundle.getSerializable(Constants.KEY_SET_VIDEO_TO_FRAGMENT_ITEMS)
                ?.let { item ->
                    val _item = item as PlaylistsModel.Item
                    initCoordinator(_item)
                    initView(_item.id)
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initCoordinator(item: PlaylistsModel.Item) {
        binding.tvTitle.text = item.snippet.title
        binding.tvDescription.text = item.snippet.description
        binding.tvVideosCount.text = item.contentDetails.itemCount.toString() + " video series"
        binding.layoutToolbarItems.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}