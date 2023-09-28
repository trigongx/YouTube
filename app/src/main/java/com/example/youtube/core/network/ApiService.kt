package com.example.youtube.core.network

import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlaylistsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("part")
        part: String,
        @Query("channelId")
        channelId: String,
        @Query("key")
        apiKey: String,
        @Query("maxResults")
        maxResults: Int
    ): Call<PlaylistsModel>
}