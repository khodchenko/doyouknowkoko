package com.example.doyouknowkoko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val elementList :List<Element>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView = itemView.findViewById(R.id.image_view)    //all data in elements
        val textView1:TextView =  itemView.findViewById(R.id.tv_name)
        val textView2:TextView =  itemView.findViewById(R.id.tv_brand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.elements, parent,false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentElement = elementList[position]
        holder.imageView.setImageResource(currentElement.imageResource)  //element data
        holder.textView1.text = currentElement.text1
        holder.textView2.text = currentElement.text2
    }

    override fun getItemCount(): Int {
        return  elementList.size
    }
}