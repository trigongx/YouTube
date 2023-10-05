package com.example.youtube.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.youtube.R
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.domain.repository.Repository

class MainActivity : AppCompatActivity() {

    /*private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*    val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
    companion object{
        private val retrofitClient = RetrofitClient().createApiService()
        private val remoteDataSource = RemoteDataSource(retrofitClient)
        val repository = Repository(remoteDataSource)
    }
}