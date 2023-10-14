package com.example.youtube.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.youtube.data.model.PlaylistsModel

object PlaylistModelComparator : DiffUtil.ItemCallback<PlaylistsModel.Item>() {
    override fun areItemsTheSame(oldItem: PlaylistsModel.Item, newItem: PlaylistsModel.Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlaylistsModel.Item, newItem: PlaylistsModel.Item): Boolean {
        return oldItem == newItem
    }
}