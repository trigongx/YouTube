package com.example.youtube.presentation.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.ItemPlaylistBinding

class PlaylistsAdapter:RecyclerView.Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {

    private var list = mutableListOf<PlaylistsModel.Item>()


    @SuppressLint("NotifyDataSetChanged")
    fun addData(playlistModelItem:List<PlaylistsModel.Item>?){
        list = playlistModelItem as MutableList<PlaylistsModel.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.toBind(list[position])
    }

    inner class PlaylistsViewHolder(private val binding:ItemPlaylistBinding):RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun toBind(item: PlaylistsModel.Item){
            binding.tvPlaylistName.text = item.snippet.title
            binding.tvPlaylistVideoCount.text = item.contentDetails.itemCount.toString() + " video series"
            binding.imgVideo.load(item.snippet.thumbnails.default.url)
        }
    }
}