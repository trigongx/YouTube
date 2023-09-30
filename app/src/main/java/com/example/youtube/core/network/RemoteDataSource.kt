package com.example.youtube.core.network

import com.example.youtube.BuildConfig
import com.example.youtube.core.base.BaseDataSource
import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.utils.Constants

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylists(): Resource<PlaylistsModel> {
        return getResult {
            apiService.getPlaylists(
                part = Constants.PART,
                channelId = Constants.CHANNEL_ID,
                apiKey = BuildConfig.API_KEY,
                maxResults = 20,
            )
        }
    }
}