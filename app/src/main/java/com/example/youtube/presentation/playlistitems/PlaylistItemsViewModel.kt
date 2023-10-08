package com.example.youtube.presentation.playlistitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.domain.repository.Repository

class PlaylistItemsViewModel(private val repository: Repository):BaseViewModel() {
    private val _playlistItems = MutableLiveData<PlaylistsModel>()
    val playlistItems: LiveData<PlaylistsModel> get() = _playlistItems

    fun getPlaylistItems(playlistId: String) = doOperation(
        operation = { repository.getPlaylistItems(playlistId) },
        success = { _playlistItems.postValue(it) }
    )
}