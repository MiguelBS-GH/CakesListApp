package com.bram3r.cakeslistapp.cakesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bram3r.cakeslistapp.R
import com.bram3r.cakeslistapp.model.Cake
import com.bumptech.glide.Glide

class CakeAdapter(var cakes: List<Cake>) : RecyclerView.Adapter<CakeAdapter.ViewHolder>() {

    init {
        if (cakes.isEmpty())
            cakes = listOf(
                Cake("Miguel", "Descripcion de Miguel", ""),
                Cake("Alba", "Descripcion de Alba", "")
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cake_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cakes[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int = cakes.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.cakeNameTextView)
        private val image: ImageView = view.findViewById(R.id.cakeImageView)

        fun bind(cake: Cake) {
            name.text = cake.title
            Glide.with(image)
                .load(cake.image)
                .circleCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(image)
        }
    }


}