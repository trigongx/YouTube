package com.example.youtube.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig.API_KEY
import com.example.youtube.core.network.ApiService
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlaylistsModel
import com.example.youtube.utils.Constants.CHANNEL_ID
import com.example.youtube.utils.Constants.PART
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService) {

	fun getPlaylists(): LiveData<Resource<PlaylistsModel>> {
		val resourceData = MutableLiveData<Resource<PlaylistsModel>>()
		apiService.getPlaylists(
			part = PART,
			channelId = CHANNEL_ID,
			apiKey = API_KEY,
			maxResults = 11,
		).enqueue(
			object : Callback<PlaylistsModel> {
				override fun onResponse(
					call: Call<PlaylistsModel>,
					response: Response<PlaylistsModel>,
				) {
					if (response.isSuccessful) {
						resourceData.value = Resource.success(response.body())
						Log.d("ololo", "onResponse: ${response.body()}")
					} else {
						resourceData.value = Resource.error(
							msg = response.message().toString(),
							data = null,
							code = 429
						)
					}
				}
				
				override fun onFailure(call: Call<PlaylistsModel>, t: Throwable) {
					resourceData.value = Resource.error(
						msg = t.message.toString(),
						data = null,
						code = 429
					)
				}
			}
		)
		return resourceData
	}
}