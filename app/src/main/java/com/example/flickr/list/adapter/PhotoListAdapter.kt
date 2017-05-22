package com.example.flickr.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.data.images.ImageLoader
import com.example.domain.models.Photo
import com.example.flickr.R
import com.example.flickr.list.ListContract
import kotlinx.android.synthetic.main.item_image.view.*

class PhotoListAdapter(val view: ListContract.View,
                       val dataSet: ArrayList<Photo>,
                       val imageLoader: ImageLoader)
    : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListAdapter.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        return PhotoListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoListAdapter.ViewHolder, position: Int) {
        val photo = dataSet[position]
        holder.setOnClickListener(photo, view)
        imageLoader.load(holder.imageViewItem, photo.url)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewItem: ImageView = itemView.imageViewItem

        fun setOnClickListener(photo: Photo, view: ListContract.View) {
            itemView.setOnClickListener { view.goToEntryDetail(photo.id, photo.url) }
        }

    }

}