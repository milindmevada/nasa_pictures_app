package com.nasa.picturesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasa.picturesapp.R
import com.nasa.picturesapp.databinding.RowImageGridBinding
import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.toDisplayFormat

class RVImageListingViewHolder(val binding: RowImageGridBinding) :
    RecyclerView.ViewHolder(binding.root)

class RVImageListingAdapter(
    private val images: List<ImageModel>,
    private val onTap: (ImageModel) -> Unit
) :
    RecyclerView.Adapter<RVImageListingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVImageListingViewHolder {
        val binding =
            RowImageGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RVImageListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVImageListingViewHolder, position: Int) {
        Glide.with(holder.itemView).load(images[position].url).centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.binding.image)
        holder.binding.tvTitle.text = images[position].title
        holder.binding.tvDate.text = images[position].date.toDisplayFormat()
        holder.binding.root.setOnClickListener {
            onTap.invoke(images[position])
        }
    }

    override fun getItemCount(): Int = images.size
}