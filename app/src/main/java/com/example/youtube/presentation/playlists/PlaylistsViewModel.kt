package com.example.youtube.presentation.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {
    private val _playlists = MutableLiveData<PlaylistsModel>()
    val playlists: LiveData<PlaylistsModel> get() = _playlists

    fun getPlaylists() {
        doOperation(
            operation = { repository.getPlaylists() },
            success = { _playlists.postValue(it) }
        )
    }
}