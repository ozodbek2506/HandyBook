package com.example.handybook.MainScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handybook.R
import com.example.handybook.databinding.FragmentBookDetailsScreenBinding

class BookDetailsScreen : Fragment() {
lateinit var binding: FragmentBookDetailsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return binding.root
    }



}