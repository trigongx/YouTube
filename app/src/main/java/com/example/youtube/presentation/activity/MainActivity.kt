package com.example.youtube.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youtube.R
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.domain.repository.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    companion object{
        private val retrofitClient = RetrofitClient().createApiService()
        private val remoteDataSource = RemoteDataSource(retrofitClient)
        val repository = Repository(remoteDataSource)
    }
}