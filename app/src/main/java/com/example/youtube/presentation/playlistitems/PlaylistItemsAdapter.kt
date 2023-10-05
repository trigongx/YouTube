package com.example.youtube.presentation.playlistitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.databinding.ItemPlaylistBinding

class PlaylistItemsAdapter : RecyclerView.Adapter<PlaylistItemsAdapter.PlaylistItemsViewHolder>() {

    private var _list = mutableListOf<PlaylistsModel.Item>()
    private val list: List<PlaylistsModel.Item> get() = _list

    fun addData(playlistModelItem: List<PlaylistsModel.Item>) {
        _list.clear()
        _list.addAll(playlistModelItem)
        notifyItemRangeInserted(_list.size, playlistModelItem.size - _list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlaylistItemsViewHolder(
        ItemPlaylistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PlaylistItemsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlaylistItemsViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaylistsModel.Item) {
            if (!item.snippet.thumbnails.default.url.isEmpty() && !item.snippet.title.isEmpty()) {
                binding.tvPlaylistName.text = item.snippet.title
                binding.imgVideo.load(item.snippet.thumbnails.default.url)
            }
        }

    }
}