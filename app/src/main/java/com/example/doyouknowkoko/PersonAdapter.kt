package com.example.doyouknowkoko

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doyouknowkoko.PersonAdapter.personsViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.squareup.picasso.Picasso

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
class PersonAdapter(options: FirebaseRecyclerOptions<Outfit?>) :
    FirebaseRecyclerAdapter<Outfit, personsViewHolder>(options) {


    // Function to bind the view in Card view(here
    // "person.xml") with data in
    // model class(here "person.class")
    override fun onBindViewHolder(holder: personsViewHolder, position: Int, model: Outfit) {
        // Add first name from model class (here "person.class")to appropriate view in CardView (here "person.xml")
        holder.name.text = model.name
        holder.brand.text = model.brand
        holder.size.text = model.size
        holder.comment.text = model.comment
        holder.price.text = model.price
        Picasso.get().load(model.imageUrl).into(holder.imageView)
       //Glide.with().load(model.imageUrl).into(holder.imageView)
    }

    // Function to tell the class about the Card view (here"person.xml")in which the data will be shown
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): personsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return personsViewHolder(view)
    }

    // Sub Class to create references of the views in Cards view (here "person.xml")
    class personsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name_value)
        var brand: TextView = itemView.findViewById(R.id.tv_brand_value)
        var size: TextView = itemView.findViewById(R.id.tv_size_value)
        var comment: TextView = itemView.findViewById(R.id.tv_comment_value)
        var price: TextView = itemView.findViewById(R.id.tv_price_value)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

}


