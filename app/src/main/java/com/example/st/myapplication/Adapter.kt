package com.example.st.myapplication

import android.view.*
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import kotlin.collections.ArrayList
import android.content.ContentValues.TAG
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.st.myapplication.entities.Photo
import com.bumptech.glide.load.engine.DiskCacheStrategy

class Adapter(private val context: Context) : RecyclerView.Adapter<Adapter.Holder>() {

    private var photoList: ArrayList<Photo> = ArrayList()
    lateinit var view: View

//    private var onItemClickListener: OnItemClickListener? = null

    fun setData(data: List<Photo>?) {
        photoList.clear()
        data?.let {
            photoList.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val photo = photoList[position]

        Log.d(TAG, "list     $position     $photo")

        holder.urlView?.text = photo.url
        holder.titleView?.text = photo.title
        holder.dateTaken?.text = photo.date_taken
        holder.widthView?.text = photo.width.toString()
        holder.heightView?.text = photo.height.toString()

        Glide.with(context)
                .load(photo.url)
                .apply(RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(holder.imageView!!)

//        holder.setOnItemClickListener(position, this.onItemClickListener)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
//        fun setOnItemClickListener(position: Int, onItemClickListener: OnItemClickListener?) {
//            view.setOnClickListener { onItemClickListener?.onItemClick(position) }
//        }

        val urlView = itemView?.findViewById<TextView>(R.id.url)
        val titleView = itemView?.findViewById<TextView>(R.id.title)
        val widthView = itemView?.findViewById<TextView>(R.id.width)
        val heightView = itemView?.findViewById<TextView>(R.id.height)
        val imageView = itemView?.findViewById<ImageView>(R.id.image)
        val dateTaken = itemView?.findViewById<TextView>(R.id.dateTaken)
    }
}