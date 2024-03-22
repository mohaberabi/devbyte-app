package com.example.devbyte.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.InvalidationTracker
import com.example.devbyte.R
import com.example.devbyte.databinding.FragmentDevByteBinding
import com.example.devbyte.domain.Video
import com.example.devbyte.viewmodels.DevByteVideModel

class DevByteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding: FragmentDevByteBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_dev_byte,
                container,
                false
            )


        val application = requireNotNull(activity).application
        val factory = DevByteVideModel.Factory(application)
        val viewmodel = ViewModelProvider(this, factory).get(DevByteVideModel::class.java)
        binding.videModel = viewmodel
        binding.lifecycleOwner = this
        val adapter = DevByteAdapter(VideCallBack {

        })

        viewmodel.playlist.observe(viewLifecycleOwner) {
            adapter.videos = it
        }
        binding.recView.adapter = adapter

        binding.recView.layoutManager = LinearLayoutManager(context)
        return binding.root

    }

}