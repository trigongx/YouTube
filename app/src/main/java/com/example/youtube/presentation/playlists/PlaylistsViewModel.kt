package com.example.youtube.presentation.playlists

import androidx.lifecycle.LiveData
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.domain.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<PlaylistsModel>>{
        return repository.getPlaylists()
    }

}