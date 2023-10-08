package com.example.youtube.presentation.videodetail

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.youtube.R
import com.example.youtube.core.base.BaseFragment
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.FragmentVideoDetailBinding
import com.example.youtube.utils.Constants.KEY_ITEMS_TO_VIDEOS_FRAGMENT
import com.example.youtube.utils.Constants.KEY_SET_VIDEO_TO_VIDEOS_FRAGMENT
import com.example.youtube.utils.InternetConnectionService
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideoDetailFragment : BaseFragment<FragmentVideoDetailBinding,VideoDetailViewModel>() {
    override val viewModel: VideoDetailViewModel by viewModel()

    override fun inflateViewBinding(): FragmentVideoDetailBinding = FragmentVideoDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResultListener()
        initLiveData()
    }

    override fun checkConnection() {
        super.checkConnection()
        InternetConnectionService(requireContext()).observe(viewLifecycleOwner) { isConnect ->
            if (!isConnect) {
                binding.root.visibility = View.GONE
                binding.containerInclude.visibility = View.VISIBLE
            }
            binding.layoutNoConnection.btnCheckInternetTryAgain.setOnClickListener {
                if (isConnect) {
                    binding.root.visibility = View.VISIBLE
                    binding.containerInclude.visibility = View.GONE
                } else{
                    Toast.makeText(requireContext(), getString(R.string.no_connection_try_again), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun initView(videoId:String){
        viewModel.getVideosDetails(videoId)
    }

    private fun initResultListener(){
        setFragmentResultListener(KEY_ITEMS_TO_VIDEOS_FRAGMENT){ _, bundle ->
            bundle.getSerializable(KEY_SET_VIDEO_TO_VIDEOS_FRAGMENT)?.let { model ->
                val _model = model as PlaylistsModel.Item
                initView(_model.contentDetails.videoId)
            }
        }
    }

    private fun initLiveData() {
        viewModel.videosDetails.observe(viewLifecycleOwner){item ->
            binding.tvNameVideo.text = item.items.first().snippet.title
            binding.tvDescVideo.text = item.items.first().snippet.description
            binding.ivVideoImg.load(item.items.first().snippet.thumbnails.standard.url)
        }
        viewModel.loading.observe(viewLifecycleOwner){isLoading->
            if (isLoading) binding.pbLoading.visibility = View.VISIBLE
            else binding.pbLoading.visibility = View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner){error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initListener() {
        super.initListener()
        binding.toolbar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnDownloadVideo.setOnClickListener {
            downloadVideo()
        }
    }

    private fun downloadVideo() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setView(R.layout.layout_btn_download).show()
    }


}