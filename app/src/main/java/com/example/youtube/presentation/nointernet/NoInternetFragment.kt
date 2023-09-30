package com.example.youtube.presentation.nointernet

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.databinding.FragmentNoInternentBinding


class NoInternetFragment : Fragment(){

    private lateinit var binding: FragmentNoInternentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoInternentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun checkInternet() {
        val queryToConnection = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectionInfo = queryToConnection.activeNetwork
        if (connectionInfo != null) findNavController().navigate(R.id.playlistsFragment) else Toast.makeText(
            requireContext(),
            getString(R.string.no_connection_try_again),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initListeners(){
        binding.btnCheckInternetTryAgain.setOnClickListener {
            checkInternet()
        }
    }
}