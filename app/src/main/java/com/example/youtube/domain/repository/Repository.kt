package com.example.youtube.domain.repository

import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.data.model.PlaylistsModel

class Repository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getPlaylists(): Result<PlaylistsModel> {
        return remoteDataSource.getPlaylists()
    }

    suspend fun getPlaylistItems(playlistId: String):Result<PlaylistsModel>{
        return remoteDataSource.getPlaylistItems(playlistId)
    }
}