package com.example.youtube.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlaylistsModel
class Repository(private val remoteDataSource: RemoteDataSource) {
	fun getPlaylists(): LiveData<Resource<PlaylistsModel>> {
			return liveData {
				emit(Resource.loading())
				emit(remoteDataSource.getPlaylists())
			}
		/*val resourceData = MutableLiveData<Resource<PlaylistsModel>>()
		apiService.getPlaylists(
			part = PART,
			channelId = CHANNEL_ID,
			apiKey = API_KEY,
			maxResults = 20,
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
		return resourceData*/
	}
}