package com.example.youtube.core.network

import com.example.youtube.BuildConfig
import com.example.youtube.core.base.BaseDataSource
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.utils.Constants

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylists(): Result<PlaylistsModel> {
        return getResult {
            apiService.getPlaylists(
                part = Constants.PART,
                channelId = Constants.CHANNEL_ID,
                apiKey = BuildConfig.API_KEY,
                maxResults = 16,
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Result<PlaylistsModel>{
        return getResult {
            apiService.getPlaylistItems(
                part = Constants.PART,
                apiKey = BuildConfig.API_KEY,
                playlistId = playlistId,
                maxResults = 16,
            )
        }
    }
}