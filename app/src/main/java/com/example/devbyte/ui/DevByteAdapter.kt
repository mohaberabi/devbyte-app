package com.example.devbyte.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.devbyte.R
import com.example.devbyte.databinding.DevbyteItemBinding
import com.example.devbyte.domain.Video

class DevByteAdapter(val onClick: VideCallBack) :
    RecyclerView.Adapter<DevByteAdapter.DevByteViewHolder>() {


    var videos: List<Video> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevByteViewHolder {


        val binding: DevbyteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.devbyte_item,
            parent,
            false,
        )
        return DevByteViewHolder(binding)
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(
        holder: DevByteViewHolder,
        position: Int
    ) {
        holder.binding.also {

            it.video = videos[position]
            it.videoCallback = onClick
        }
    }

    class DevByteViewHolder(val binding: DevbyteItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}

class VideCallBack(val block: (Video) -> Unit) {
    fun onClick(video: Video) = block(video)
}
