package com.nasa.picturesapp.ui.imageDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasa.picturesapp.R
import com.nasa.picturesapp.databinding.PageImageDetailsBinding
import com.nasa.picturesapp.models.ImageModel


class VPImageDetailsViewHolder(val binding: PageImageDetailsBinding) :
    RecyclerView.ViewHolder(binding.root)

class VPImageDetailsAdapter(
    private val images: List<ImageModel>,
) :
    RecyclerView.Adapter<VPImageDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VPImageDetailsViewHolder {
        val binding =
            PageImageDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VPImageDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VPImageDetailsViewHolder, position: Int) {
        Glide.with(holder.itemView).load(images[position].url).centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.binding.image)
        holder.binding.tvExplanation.text = images[position].explanation
    }

    override fun getItemCount(): Int = images.size
}