package com.example.retrofit.src.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.retrofit.R
import com.example.retrofit.application.CommonFragment
import com.example.retrofit.databinding.FragmentHomeBinding
import com.example.retrofit.viewModel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : CommonFragment<FragmentHomeBinding>(R.layout.fragment_home){

    val homeViewModel : HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            homeViewModel.getMainList(requireContext())
        }
    }
}