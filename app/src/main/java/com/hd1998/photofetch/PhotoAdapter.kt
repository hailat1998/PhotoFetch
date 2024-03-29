package com.hd1998.photofetch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hd1998.photofetch.api.PhotoItem
import com.hd1998.photofetch.databinding.ListItemGalleryBinding
class PhotoHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root ) {
    fun bind(photoItem: PhotoItem, onItemClicked : (PhotoItem) -> Unit ) {
        binding.itemImageView.load(photoItem.urls.url_s)
        binding.root.setOnClickListener{onItemClicked(photoItem)}
    }
}
class PhotoAdapter(
    private val photos : List<PhotoItem>  , private val onItemClicked : (PhotoItem) -> Unit) :
    RecyclerView.Adapter<PhotoHolder>()
{ override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
): PhotoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =ListItemGalleryBinding.inflate(inflater , parent , false)
        return PhotoHolder(binding)
    }
    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val item= photos[position]
        holder.bind( item,  onItemClicked )
    }
    override fun getItemCount() = photos.size
}
