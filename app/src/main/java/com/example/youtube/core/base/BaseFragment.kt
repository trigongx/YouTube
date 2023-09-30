package com.example.youtube.core.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.youtube.R

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: VB? = null

    //protected abstract val viewModel:VM
    protected val binding get() = _binding!!
    protected abstract fun inflateViewBinding(): VB

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkConnection()
        initRecyclerView()
        initListener()
    }
    private fun checkConnection() {
        val queryConnection = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectionInfo = queryConnection.activeNetwork
        if (connectionInfo != null) {
            initView()
        } else
            findNavController().navigate(R.id.noInternetFragment)
    }

    open fun initView() {}

    open fun initRecyclerView() {}

    open fun initListener() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}