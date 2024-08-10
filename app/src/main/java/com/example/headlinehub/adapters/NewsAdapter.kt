package com.example.headlinehub.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.headlinehub.R
import com.example.headlinehub.models.Article
import java.util.ArrayList

class NewsAdapter(context: Context, val newsList:ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=View.inflate(parent.context,R.layout.home_fragment_recycler_view,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=newsList[position]
         holder.headlineTitle.text=currentItem.title
        holder.headlineSrc.text=currentItem.source.name
        //glide library will use to load image from url
        Glide.with(holder.itemView.context)
            .load(currentItem.urlToImage)
            .apply(
                RequestOptions()
                    .fitCenter()  // Or use .centerCrop() if you prefer cropping
                    .transform(RoundedCorners(20))  // Adjust the radius as needed
            )
            .transition(DrawableTransitionOptions.withCrossFade())  // Optional: Add a crossfade transition
            .into(holder.headlineImage)
//        holder.headlineImage.setImageResource(R.drawable.ic_launcher_background)
    }
    class ViewHolder (itemView : View, listener: AdapterView.OnItemClickListener?=null):RecyclerView.ViewHolder(itemView){
      val headlineImage=itemView.findViewById<ImageView>(R.id.headline_image)
        val headlineTitle=itemView.findViewById<TextView>(R.id.headline_title)
        val headlineSrc=itemView.findViewById<TextView>(R.id.headline_src)

    }
}